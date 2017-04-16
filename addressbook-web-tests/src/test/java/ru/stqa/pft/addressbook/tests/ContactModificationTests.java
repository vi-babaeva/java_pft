package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public  void ensurePreconditions() {
    app.goTo().homePage();
    if (! app.сontact().isThereAContact()) {
      app.сontact().createContact(new ContactData()
              .withFirstName("Petr").withLastName("Ivanov")
              .withPhone("+78880007733").withEmail("test@yandex.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification(){

    List<ContactData> before = app.сontact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withFirstName("Ivan").withLastName("Petrov")
            .withAddress("St.Petersburg").withPhone("+78880007733").withAddress("test@yandex.ru");
    app.сontact().modify(index, contact);
    app.goTo().homePage();
    List<ContactData> after = app.сontact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
