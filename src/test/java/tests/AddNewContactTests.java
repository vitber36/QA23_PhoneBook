package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Random;

public class AddNewContactTests extends TestBase {

    //int i=(int)(System.currentTimeMillis())/1000%3600;

    @BeforeClass
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik"));
        }

    }

    @Test
    public void addContactSuccessAll() {
        int i = new Random().nextInt(1000);

        Contact contact = Contact.builder()
                .name("zz" + i)
                .lastName("aa")
                .phone("1555555555" + i)
                .email("zz" + i + "@mail.ru")
                .address("aa")
                .description("all")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen-"+i+".png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));


    }

    @Test
    public void addContactSuccessRequiredFields() {
        int i = new Random().nextInt(1000);

        Contact contact = Contact.builder()
                .name("zzzRequared" + i)
                .lastName("zz")
                .phone("6666666666" + i)
                .email("zzz" + i + "@mail.ru")
                .address("zzz")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

    }
    @Test
    public void addNewContactWrongName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("aa")
                .phone("1555555555")
                .email("zz@mail.ru")
                .address("aa")
                .description("empty name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongAddress(){
        Contact contact = Contact.builder()
                .name("zz")
                .lastName("aa")
                .phone("1555555555")
                .email("zz@mail.ru")
                .address("")
                .description("empty address")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongLastName(){
        Contact contact = Contact.builder()
                .name("zz")
                .lastName("")
                .phone("1555555555")
                .email("zz@mail.ru")
                .address("aa")
                .description("empty last name")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongPhone(){
        Contact contact = Contact.builder()
                .name("zz")
                .lastName("aa")
                .phone("")
                .email("zz@mail.ru")
                .address("aa")
                .description("empty phone")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }

    @Test
    public void addNewContactWrongEmail(){
        Contact contact = Contact.builder()
                .name("zz")
                .lastName("aa")
                .phone("1555555555")
                .email("zzmail.ru")
                .address("aa")
                .description("wrong email")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));

    }

}
