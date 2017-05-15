package pft.addressbook.tests;

import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
                      .withFirstname("Ivan").withLastname("Ivanov")
                      .withAddress("Peterburg").withHomePhone("894237958")
                      .withMobilePhone("+73847").withWorkPhone("8934275")
                      .withGroup("test1").withEmail("test@yandex.ru"),
              true);
    }
  }

  @Test
  public void testContactDeletion() throws InterruptedException {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
