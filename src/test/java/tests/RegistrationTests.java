package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }

    @Test()
    public void registrationSuccess() {
        Random random = new Random();
        int i = random.nextInt(1000);

        User user = new User().withEmail("vitaly" + i + "@gmail.com").withPassword("1978Vit@lik");

        logger.info("Registration test with data-->email:'vitaly' + i + '@gmail.com' & password '1978Vit@lik'");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isRegistered());
        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Assert message 'No Contacts here! Add new by clicking on Add in NavBar!'");
    }
        @Test(description = "Bug report #23456 Fixed")
        public void registrationWrongEmail(){
            logger.info("Registration test with data-->email:'vitalygmail.com' & password '1978Vit@lik'");

            User user=new User().withEmail("vitalygmail.com").withPassword("1978Vit@lik");

            app.getHelperUser().openLoginRegistrationForm();
            app.getHelperUser().fillLoginRegistrationForm(user);
            app.getHelperUser().submitRegistration();

            Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

            logger.info("Assert message contains 'Wrong email or password format'");


        }

    @Test(description = "Bug report #23456 Fixed")
    public void registrationWrongPassword(){
        logger.info("Registration test with data-->email:'vitaly@gmail.com' & password '1978V'");

        User user=new User().withEmail("vitaly@gmail.com").withPassword("1978V");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

        logger.info("Assert message contains 'Wrong email or password format'");


    }

    @Test(description = "Bug report #23456 Fixed")
    public void registrationExistsUser(){
        logger.info("Registration test with exist data-->email:'vitaly@gmail.com' & password '1978Vit@lik'");

        User user=new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));

        logger.info("Assert message 'User already exist'");
    }



}
