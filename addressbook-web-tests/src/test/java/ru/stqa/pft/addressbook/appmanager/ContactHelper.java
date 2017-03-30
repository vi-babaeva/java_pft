package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by dequa on 30.03.17.
 */
public class ContactHelper {
  protected final GroupsHelper groupsHelper = new GroupsHelper();

  public void fillContactForm(ContactData contactData) {
      groupsHelper.wd.findElement(By.name("firstname")).click();
      groupsHelper.wd.findElement(By.name("firstname")).clear();
      groupsHelper.wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());
      groupsHelper.wd.findElement(By.name("lastname")).click();
      groupsHelper.wd.findElement(By.name("lastname")).clear();
      groupsHelper.wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
      groupsHelper.wd.findElement(By.name("address")).click();
      groupsHelper.wd.findElement(By.name("address")).clear();
      groupsHelper.wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
      groupsHelper.wd.findElement(By.name("mobile")).click();
      groupsHelper.wd.findElement(By.name("mobile")).clear();
      groupsHelper.wd.findElement(By.name("mobile")).sendKeys(contactData.getPhone());
      groupsHelper.wd.findElement(By.name("email")).click();
      groupsHelper.wd.findElement(By.name("email")).clear();
      groupsHelper.wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
      groupsHelper.wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void returnHomePage() {
      groupsHelper.wd.findElement(By.linkText("home page")).click();
  }

  public void newContact() {
      groupsHelper.wd.findElement(By.linkText("add new")).click();
  }
}
