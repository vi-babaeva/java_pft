package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupsHelper().getGroupCount();
    if (! app.getGroupsHelper().isThereAGroup()) {
      app.getGroupsHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupsHelper().selectGroup(before - 1);
    app.getGroupsHelper().deleteSelectedGroups();
    app.getGroupsHelper().returnToGroupPage();
    int after = app.getGroupsHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);

  }

}
