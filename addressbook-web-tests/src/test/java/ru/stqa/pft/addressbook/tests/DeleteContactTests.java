package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class DeleteContactTests extends TestBase {

  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().homePage();
    if (! app.сontact().isThereAContact()) {
      app.сontact().createContact(new ContactData()
              .withFirstName("Ivan").withAddress("St.Petersburg").withPhone("+78880007733")
              .withEmail("test@yandex.ru").withGroup("test1"));
    }
  }

  @Test
  public void testDeleteContact() {
    Set<ContactData> before = app.сontact().all();
    ContactData deletedContact = before.iterator().next();
    app.сontact().delete(deletedContact);
    app.goTo().homePage();
    Set<ContactData> after = app.сontact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
