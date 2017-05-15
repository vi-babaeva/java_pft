package pft.addressbook.tests;

import pft.addressbook.model.ContactData;
import pft.addressbook.model.Contacts;
import pft.addressbook.model.GroupData;
import pft.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {
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
            .withFirstname("Ivan").withLastname("Ivanov")
            .withAddress("Saint P.").withHomePhone("2394875")
            .withMobilePhone("+7-9993").withWorkPhone("37864").withEmail("test@yandex.ru");
    app.contact().create(contact, true);
    Contacts contacts = app.contact().all();
    contact.withId(contacts.stream().mapToInt(ContactData::getId).max().getAsInt());
  }

  @Test(enabled = false)
  public void testAddContactToGroup() {
    app.contact().addToGroup(groupId, contact.getId(), GROUP);
    assertThat(app.contact().count(), equalTo(1));
    Contacts after = app.contact().all();
    assertThat(after.iterator().next(), equalTo(contact));
  }

}
