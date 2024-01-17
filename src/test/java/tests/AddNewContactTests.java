package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik"));
        }

    }
    @Test
    public void addContactSuccessAll(){
        int i=new Random().nextInt(1000);

        Contact contact=Contact.builder()
                .name("zz")
                .lastName("aa")
                .phone("1555555555")
                .email("zz"+i+"@mail.ru")
                .address("aa")
                .description("aa")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();

        //Assert.assertFalse(app.getHelperContact().isElementPresent(By.xpath("//*[@placeholder='Name']")));

    }
    @Test
    public void addContactSuccessRequiredFields(){
        int i=new Random().nextInt(1000);

        Contact contact=Contact.builder()
                .name("zzz")
                .lastName("zz")
                .phone("6666666666")
                .email("zzz"+i+"@mail.ru")
                .address("zzz")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();

        //Assert.assertFalse(app.getHelperContact().isElementPresent(By.xpath("//button/b")));

    }
    @AfterMethod
    public void postCondition(){
        app.getHelperContact().click(By.xpath("//*[text()='Sign Out']"));
    }
}
