package pft.addressbook.tests;

import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;
import pft.addressbook.model.GroupData;
import pft.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RemoveContactFromGroupTests extends TestBase {

  private final static String GROUP = "Contact Group Test";
  private int groupId;
  private ContactData contact;

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    app.group().create(new GroupData().withName(GROUP));
    Groups groups = app.group().all();
    groupId = groups.stream().mapToInt(GroupData::getId).max().getAsInt();
    app.goTo().HomePage();
    contact = new ContactData()
            .withFirstname("Ivan").withLastname("Ivanov").withAddress("Saint P.")
            .withHomePhone("398274698").withMobilePhone("+7289374").withWorkPhone("93847")
            .withEmail("test@yandex.ru");
    app.contact().create(contact, true);
    Contacts contacts = app.contact().all();
    contact.withId(contacts.stream().mapToInt(ContactData::getId).max().getAsInt());
    app.contact().addToGroup(groupId, contact.getId(), GROUP);
    app.goTo().HomePage();
  }

  @Test
  public void testRemoveContactFromGroup() {
    app.contact().selectContactGroupById(groupId);
    app.contact().removeFromGroup(contact.getId());
    Contacts after = app.contact().all();
    assertEquals(after.size(), 0);
  }
}
