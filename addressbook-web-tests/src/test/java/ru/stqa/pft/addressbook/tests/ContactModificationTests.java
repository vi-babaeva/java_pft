package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.сontact().create(new ContactData()
              .withFirstName("Petr").withLastName("Ivanov")
              .withEmail("email1").withEmail2("email2").withEmail3("email3")
              .withMobilePhone("+7823974").withHomePhone("888").withWorkPhone("3-74865"));
    }
  }

  @Test
  public void testContactModification(){

    Contacts before = app.db().contacts();
    ContactData modifitedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifitedContact.getId())
            .withFirstName("Petr").withLastName("Ivanov")
            .withEmail("email1").withEmail2("email2").withEmail3("email3")
            .withMobilePhone("+7823974").withHomePhone("888").withWorkPhone("3-74865");
    app.сontact().modify(contact);
    app.goTo().homePage();
    assertThat(app.сontact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifitedContact).withAdded(contact)));
  }

}
