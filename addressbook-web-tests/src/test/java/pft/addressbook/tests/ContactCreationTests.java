package pft.addressbook.tests;

import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() {
    app.goTo().HomePage();
    app.contact().selectContactAll();
    app.goTo().ContactPage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/1.png");
    ContactData contact = new ContactData().
            withFirstname("Ivan").withLastname("Ivanov").withPhoto(photo)
            .withAddress("St. P\nNevskii\n\n11").withHomePhone("9612345").withMobilePhone("+7893745")
            .withWorkPhone("832965").withEmail("test@yandex.ru");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
