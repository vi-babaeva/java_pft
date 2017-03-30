package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final ContactHelper contactHelper = new ContactHelper();

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() {
    contactHelper.groupsHelper.wd = new FirefoxDriver();
    contactHelper.groupsHelper.wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    contactHelper.groupsHelper.wd.get("http://localhost/addressbook/group.php");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    contactHelper.groupsHelper.wd.findElement(By.name("user")).click();
    contactHelper.groupsHelper.wd.findElement(By.name("user")).clear();
    contactHelper.groupsHelper.wd.findElement(By.name("user")).sendKeys(username);
    contactHelper.groupsHelper.wd.findElement(By.name("pass")).click();
    contactHelper.groupsHelper.wd.findElement(By.name("pass")).clear();
    contactHelper.groupsHelper.wd.findElement(By.name("pass")).sendKeys(password);
    contactHelper.groupsHelper.wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  public void gotoGroupPage() {
    contactHelper.groupsHelper.wd.findElement(By.linkText("groups")).click();
  }

  public void stop() {
    contactHelper.groupsHelper.wd.quit();
  }

  public GroupsHelper getGroupsHelper() {
    return contactHelper.groupsHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
