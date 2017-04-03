package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    
    @Test
    public void testContactCreation() {
        app.getContactHelper().newContact();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", null, "St.Petersburg", "+78880007733", "test@yandex.ru", "test1"), true);
        app.getContactHelper().returnHomePage();
    }
}
