package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = app.сontact().all();
    ContactData contact = new ContactData()
            .withFirstName("Petr").withLastName("Ivanov").withAddress("St.Petersburg")
            .withMobilePhone("78880007733").withEmail("test@yandex.ru").withGroup("test1");
    app.сontact().createContact(contact);
    Contacts after = app.сontact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
