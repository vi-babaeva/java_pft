package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class DeleteContactTests extends TestBase {

  public  void ensurePreconditions() {
    app.goTo().homePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Petr", "Ivanov", null, "+78880007733", "test@yandex.ru", "test1"));
    }
  }

  @Test
  public void testDeleteContact() {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getContactHelper().findSelect();
    app.getContactHelper().selectContact(index);
    app.getContactHelper().selectDeleteButton();
    app.getContactHelper().closeAlert();
    app.getContactHelper().findMsg();
    app.goTo().homePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
