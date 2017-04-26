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
    list.add(new Object[] {new ContactData().withFirstName("Petr").withLastName("Petrov")
            .withAddress("St.Petersburg").withMobilePhone("78880007733").withEmail("test1@yandex.ru")});
    list.add(new Object[] {new ContactData().withFirstName("Ivan").withLastName("Ivanov")
            .withAddress("Moscow").withMobilePhone("+79990005544").withEmail("test2@mail.ru")});
    list.add(new Object[] {new ContactData().withFirstName("Vasya").withLastName("Smirnov")
            .withAddress("Riga").withMobilePhone("+3908983223").withEmail("test3@gmail.com")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
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
