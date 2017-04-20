package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().homePage();
    if (app.сontact().all().size() == 0) {
      app.сontact().createContact(new ContactData()
              .withFirstName("Petr").withLastName("Ivanov")
              .withMobilePhone("78880007733").withEmail("test@yandex.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification(){

    Contacts before = app.сontact().all();
    ContactData modifitedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifitedContact.getId()).withFirstName("Ivan").withLastName("Petrov")
            .withAddress("St.Petersburg").withMobilePhone("78880007733").withAddress("test@yandex.ru");
    app.сontact().modify(contact);
    app.goTo().homePage();
    Contacts after = app.сontact().all();
    assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifitedContact).withAdded(contact)));
  }

}
