package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper (WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation ) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());

    if (creation) {
      new org.openqa.selenium.support.ui.Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else  {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    selectEditButtonById(contact.getId());
    fillContactForm(contact, false);
    selectUpdateButton();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    findSelect();
    selectContactById(contact.getId());
    selectDeleteButton();
    closeAlert();
    findMsg();
    contactCache = null;
  }

  public void returnHomePage() {
    click(By.linkText("home page"));
  }

  public void newContact() {
    click(By.linkText("add new"));
  }

  public void selectDetailsById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
  }


  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void selectEditButtonById(int id) {
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
  }

  public void selectUpdateButton() {
    click(By.name("update"));
  }

  public void selectDeleteButton() {
    click(By.xpath(" //div[@id='content']/form[2]/div[2]/input"));
  }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact) {
    newContact();
    fillContactForm((contact), true);
    contactCache = null;
    returnHomePage();
  }

  public boolean isThereAContact() {
    return  isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void findMsg() {
    isElementPresent(By.cssSelector("div.msgbox"));
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache !=null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastName = element.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
      String firstName = element.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
      String allPhones = element.findElement(By.cssSelector("td:nth-of-type(6)")).getText();
      String allEmail = element.findElement(By.cssSelector("td:nth-of-type(5)")).getText();
      String allAddress = element.findElement(By.cssSelector("td:nth-of-type(5)")).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withAllEmail(allEmail));
    }
    return new Contacts(contactCache);
  }

  public void findSelect() {
    isElementPresent(By.name("selected[]"));
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

}
