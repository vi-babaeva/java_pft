package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {
    
    @Test
    public void testContactCreation() {
        app.newContact();
        app.fillContactForm(new ContactData("Ivan", "Ivanov", "St.Petersburg", "+78880007733", "test@yandex.ru"));
        app.returnHomePage();
    }

}
