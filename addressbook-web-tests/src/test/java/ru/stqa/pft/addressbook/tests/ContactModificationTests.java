package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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

    Contacts before = app.сontact().all();
    ContactData modifitedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifitedContact.getId()).withFirstName("Ivan").withLastName("Petrov")
            .withAddress("St.Petersburg").withPhone("+78880007733").withAddress("test@yandex.ru");
    app.сontact().modify(contact);
    app.goTo().homePage();
    assertThat(app.сontact().count(), equalTo(before.size()));
    Contacts after = app.сontact().all();
    assertThat(after, equalTo(before.without(modifitedContact).withAdded(contact)));
  }

}
