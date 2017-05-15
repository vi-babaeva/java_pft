package pft.addressbook.tests;

import pft.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
                      .withFirstname("Ivan").withLastname("Ivanov")
                      .withAddress("Peterburg").withHomePhone("894237958")
                      .withMobilePhone("+73847").withWorkPhone("8934275")
                      .withGroup("test1").withEmail("test@yandex.ru"),
              true);
    }
  }

  @Test
  public void testContactPhones() {
    ContactData contact = app.contact().all().iterator().next();
    String contactInfoFromEditForm = app.contact().addressInfoFromEditForm(contact.getId());
    assertThat(multiLineStringToString(contact.getAddress()), equalTo(multiLineStringToString(contactInfoFromEditForm)));
  }

  private static String multiLineStringToString(String multiline) {
    return Arrays.stream(multiline.split("\n")).filter(s -> !s.equals("")).collect(Collectors.joining(";"));
  }

}
