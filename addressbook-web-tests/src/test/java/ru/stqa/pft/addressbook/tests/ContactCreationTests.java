package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {"Petr", "Petrov", "St.Petersburg", "78880007733", "test1@yandex.ru"});
    list.add(new Object[] {"Ivan", "Ivanov", "Moscow", "79990009999", "test2@yandex.ru"});
    list.add(new Object[] {"Vasya", "Smirnov", "Moscow", "77770007777", "test3@yandex.ru"});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(String firstname, String lastname, String address, String mobilephone, String email) {
      ContactData contact = new ContactData().withFirstName(firstname)
              .withLastName(lastname).withAddress(address)
              .withMobilePhone(mobilephone).withEmail(email);
      Contacts before = app.сontact().all();
      app.сontact().create(contact);
      assertThat(app.сontact().count(), equalTo(before.size() + 1));
      Contacts after = app.сontact().all();
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/2.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
