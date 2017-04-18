package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class DeleteContactTests extends TestBase {

  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().homePage();
    if (app.сontact().all().size() == 0) {
      app.сontact().createContact(new ContactData()
              .withFirstName("Ivan").withAddress("St.Petersburg").withPhone("+78880007733")
              .withEmail("test@yandex.ru").withGroup("test1"));
    }
  }

  @Test
  public void testDeleteContact() {
    Contacts before = app.сontact().all();
    ContactData deletedContact = before.iterator().next();
    app.сontact().delete(deletedContact);
    app.goTo().homePage();
    assertThat(app.сontact().count(), equalTo(before.size() - 1));
    Contacts after = app.сontact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
