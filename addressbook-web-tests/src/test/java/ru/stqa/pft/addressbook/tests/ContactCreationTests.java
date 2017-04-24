package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    Contacts before = app.сontact().all();
    File photo = new File("src/test/resources/2.png");
    ContactData contact = new ContactData()
            .withFirstName("Petr").withLastName("Ivanov").withAddress("St.Petersburg")
            .withMobilePhone("78880007733").withEmail("test@yandex.ru")
            .withPhoto(photo).withGroup("test1");
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
