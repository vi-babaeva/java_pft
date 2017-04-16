package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class DeleteContactTests extends TestBase {

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
    List<ContactData> before = app.сontact().list();
    int index = before.size() - 1;
    app.сontact().delete(index);
    app.goTo().homePage();
    List<ContactData> after = app.сontact().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
