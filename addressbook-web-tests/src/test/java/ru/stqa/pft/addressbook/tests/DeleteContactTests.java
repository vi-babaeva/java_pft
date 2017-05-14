package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class DeleteContactTests extends TestBase {

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
  public void testDeleteContact() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.сontact().delete(deletedContact);
    app.goTo().homePage();
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }
}
