package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Set<ContactData> before = app.сontact().all();
    ContactData contact = new ContactData()
            .withFirstName("Petr").withLastName("Ivanov").withAddress("St.Petersburg")
            .withPhone("+78880007733").withEmail("test@yandex.ru").withGroup("test1");
    app.сontact().createContact(contact);
    Set<ContactData> after = app.сontact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
