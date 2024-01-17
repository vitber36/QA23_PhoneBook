package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd) {
        super(wd);
    }

    public void openContactForm() {
        click(By.xpath("//*[@href='/add']"));
    }

    public void fillContactForm(Contact contact) {
        type(By.xpath("//*[@placeholder='Name']"),contact.getName());
        type(By.xpath("//*[@placeholder='Last Name']"),contact.getLastName());
        type(By.xpath("//*[@placeholder='Phone']"),contact.getPhone());
        type(By.xpath("//*[@placeholder='email']"),contact.getEmail());
        type(By.xpath("//*[@placeholder='Address']"),contact.getAddress());
        type(By.xpath("//*[@placeholder='description']"),contact.getDescription());

    }

    public void submit() {
       click(By.xpath("//button/b"));
    }
}
