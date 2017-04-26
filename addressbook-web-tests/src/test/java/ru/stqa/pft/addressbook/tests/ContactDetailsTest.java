package ru.stqa.pft.addressbook.tests;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDetailsTest extends TestBase {

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
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.сontact().all().iterator().next();
    ContactData contactInfoFromDetailsForm = app.сontact().infoFromDetailsForm(contact);
    assertThat(cleaned(contact.getAllDetails()), equalTo(mergeDetails(contactInfoFromDetailsForm)));
  }

  private String mergeDetails(ContactData contact) {
    return Arrays.asList(contact.getAllDetails()).stream().filter((s) -> !(s == null || s.equals("")))
            .map(ContactDetailsTest::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String data) {
    return data.replaceAll("\\s", "").replaceAll("[-()]", "").replaceAll("H:", "").replaceAll("M:", "")
            .replaceAll("W:", "");
  }
}