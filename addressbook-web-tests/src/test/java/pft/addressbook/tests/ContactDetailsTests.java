package pft.addressbook.tests;

import pft.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

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
  public void testContactPreview() {
    ContactData contact = app.contact().all().iterator().next();
    String contactinfoFromDetailsForm = app.contact().infoFromDetailsForm(contact.getId());
    assertThat(mergeContact(contact), equalTo(contactinfoFromDetailsForm));
  }

  private String mergeContact(ContactData contact) {
    ContactData editcontact = app.contact().phoneEditForm(contact.getId());
    return Stream.of(cleaned(contact.getFirstname() + "" + contact.getLastname()), multiLineStringToString(contact.getAddress()), editcontact.getHomePhone(),
            editcontact.getMobilePhone(), editcontact.getWorkPhone(), multiLineStringToString(contact.getAllEmails()))
            .filter((s) -> !s.equals("")).map(ContactDetailsTests::phoneCleaned).collect(Collectors.joining(";"));
  }

  public static String phoneCleaned(String phone) {
    return phone.replaceAll("[\\s-()]", "");
  }

  private static String multiLineStringToString(String multiline) {
    return Arrays.stream(multiline.split("\n")).filter(s -> !s.equals("")).map(ContactDetailsTests::cleaned).collect(Collectors.joining(";"));
  }

  private static String cleaned(String str) {
    return str.replaceAll("[-()\\s]", "");
  }
}
