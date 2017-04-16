package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().homePage();
    if (app.сontact().all().size() == 0) {
      app.сontact().createContact(new ContactData()
              .withFirstName("Petr").withLastName("Ivanov")
              .withPhone("+78880007733").withEmail("test@yandex.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification(){

    Set<ContactData> before = app.сontact().all();
    ContactData modifitedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifitedContact.getId()).withFirstName("Ivan").withLastName("Petrov")
            .withAddress("St.Petersburg").withPhone("+78880007733").withAddress("test@yandex.ru");
    app.сontact().modify(contact);
    app.goTo().homePage();
    Set<ContactData> after = app.сontact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifitedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
