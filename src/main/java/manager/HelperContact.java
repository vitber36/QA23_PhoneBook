package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

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

    public void saveContact() {
       click(By.xpath("//button/b"));
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement>list=wd.findElements(By.cssSelector("h2"));
        for(WebElement el:list){
            if(el.getText().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement>list=wd.findElements(By.cssSelector("h3"));
        for(WebElement el:list){
            if(el.getText().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public boolean isAddPageStillDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public void provideContacts() {
        if(countOfContacts()<3){
            for(int i=0;i<3;i++){
                addOneContact();
            }
        }
    }

    private void addOneContact() {
        int i=new Random().nextInt(1000)+1000;
        Contact contact=Contact.builder()
                .name("Garry"+i)
                .lastName("Potter")
                .address("Hogwards")
                .email("harry"+i+"@mail.ru")
                .phone("1234567890"+i)
                .description("Friend")
                .build();


        openContactForm();
        fillContactForm(contact);
        saveContact();
        pause(1500);
    }


    public int removeOneContact() {
        int before=countOfContacts();
        logger.info("Number of contacts before remove is--->"+before);
        removeContact();


        int after=countOfContacts();
        logger.info("Number of contacts after remove is--->"+after);
        return before-after;
    }

    private void removeContact() {
        click(By.cssSelector(".contact-item_card__2SOIM"));
        click(By.xpath("//button[text()='Remove']"));
        pause(1500);
    }

    private int countOfContacts() {

        return wd.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
    }

    public void removeAllContacts() {
        while (countOfContacts()!=0){
            removeContact();
        }
    }
}
