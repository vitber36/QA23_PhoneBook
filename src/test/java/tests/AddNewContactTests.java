package tests;

import manager.DataProviderContacts;
import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Random;

public class AddNewContactTests extends TestBase {

    //int i=(int)(System.currentTimeMillis())/1000%3600;

    @BeforeClass(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik"));
        }

    }

    @Test(dataProvider ="contactCSV",dataProviderClass = DataProviderContacts.class)
    public void addContactSuccessAll(Contact contact) {

        logger.info("Add contact with test data---> "+contact.toString());

        int i = new Random().nextInt(1000);

//        Contact contact = Contact.builder()
//                .name("zz" + i)
//                .lastName("aa")
//                .phone("1555555555" + i)
//                .email("zz" + i + "@mail.ru")
//                .address("aa")
//                .description("all")
//                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen-"+i+".png");
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

        logger.info("assert the contact is add");
    }

    @Test(groups = {"smoke","regress","retest"})
    public void addContactSuccessRequiredFields() {

        logger.info("Add contact with test data---> name-'zz+i', last name-'aa', phone-'1555555555'," +
                "email-'zz+i+@mail.ru',address-'aa'");

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

        logger.info("assert the contact is add");
    }
    @Test
    public void addNewContactWrongName(){
        logger.info("Add contact with test data---> name-'', last name-'aa', phone-'1555555555'," +
                "email-'zz+i+@mail.ru',address-'aa',description-'empty name'");

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

        logger.info("assert message contains text 'Wrong name'");

    }

    @Test
    public void addNewContactWrongAddress(){
        logger.info("Add contact with test data---> name-'zz', last name-'aa', phone-'1555555555'," +
                "email-'zz+i+@mail.ru',address-'aa',description-'empty address'");

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

        logger.info("assert message contains text 'Wrong address'");
    }

    @Test
    public void addNewContactWrongLastName(){
        logger.info("Add contact with test data---> name-'zz', last name-'', phone-'1555555555'," +
                "email-'zz+i+@mail.ru',address-'',description-'empty last name'");

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

        logger.info("assert message contains text 'Wrong lastName'");
    }

    @Test(dataProvider = "contactWrongPhone",dataProviderClass = DataProviderContacts.class)
    public void addNewContactWrongPhone(Contact contact){
        logger.info("Add contact with test data---> "+contact.toString());

//        Contact contact = Contact.builder()
//                .name("zz")
//                .lastName("aa")
//                .phone("")
//                .email("zz@mail.ru")
//                .address("aa")
//                .description("empty phone")
//                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();

        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits!" +
                " And length min 10, max 15!"));

        logger.info("assert message contains text 'Phone not valid'");
    }

    @Test
    public void addNewContactWrongEmail(){
        logger.info("Add contact with test data---> name-'zz', last name-'aa', phone-'1555555555'," +
                "email-'zzmail.ru',address-'aa',description-'wrong email'");

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

        logger.info("assert message contains text 'Email not valid'");
    }

}
