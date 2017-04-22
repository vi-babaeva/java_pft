package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.сontact().all().size() == 0) {
      app.сontact().create(new ContactData()
              .withFirstName("Petr").withLastName("Ivanov")
              .withMobilePhone("78880007733").withEmail("test@yandex.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().homePage();
    ContactData contact = app.сontact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.сontact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat (contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat (contact.getAllEmail(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned (String phone) {
    return  phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  private String mergeEmails(ContactData contact) {
    return  Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
