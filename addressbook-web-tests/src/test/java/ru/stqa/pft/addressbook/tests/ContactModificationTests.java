package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", null, "St.Petersburg", "+78880007733", "test@yandex.ru", "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().selectEditButton();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "St.Petersburg", "+78880007733", "test@yandex.ru", null), false);
    app.getContactHelper().selectUpdateButton();
    app.getNavigationHelper().goToHomePage();

  }

}
