package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
    
    @Test
    public void testContactCreation() {
        newContact();
        fillContactForm(new ContactData("Ivan", "Ivanov", "St.Petersburg", "+78880007733", "test@yandex.ru"));
        returnHomePage();
    }

}
