package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class DeleteContactTests extends TestBase {

  @Test
  public void testDeleteContact() {
    app.getNavigationHelper().goToHomePage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", null, "St.Petersburg", "+78880007733", "test@yandex.ru", "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().selectDeleteButton();
    app.getContactHelper().closeAlert();
    app.getContactHelper().findMsg();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
