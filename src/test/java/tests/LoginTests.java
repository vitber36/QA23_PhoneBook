package tests;

import manager.DataProviderUser;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();

            logger.info("Before method finish logout");
        }
    }

    @Test(dataProvider="loginDate",dataProviderClass = DataProviderUser.class)
    public void loginSuccess(String email,String password){
        logger.info("Start test with name 'login success'");
        logger.info("Test data--> email: "+email+ "& password:" +password);

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email,password);
        app.getHelperUser().submitLogin();

        //Assert.assertEquals();
        //Assert.assertTrue();
        //Assert.assertNotEquals();
        //Assert.assertFalse();
        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Assert check is element button 'sing out' present");

    }


    @Test
    public void loginSuccess1(){
        User user=new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();

        Assert.assertTrue(app.getHelperUser().isLogged());

    }
    @Test(dataProvider = "loginModel",dataProviderClass=DataProviderUser.class)
    public void loginSuccessModel(User user) {
        logger.info("Test data--> "+user.toString());

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

        logger.info("Assert check is element button 'sing out' present");
    }


    @Test
    public void loginWrongEmail(){
        logger.info("Test data--> email:'vitber06mail.ru' & password: '1978Vit@lik'");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("vitber06mail.ru", "1978Vit@lik");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }
    @Test
    public void loginWrongPassword(){
        logger.info("Test data--> email:'vitber06@mail.ru' & password: '1978it@lik'");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("vitber06@mail.ru", "1978it@lik");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

        logger.info("Assert check is alert present with error text 'Wrong email or password'");

    }
    @Test
    public void loginUnregistrationUser(){
        logger.info("Test data--> email:'vvitber06@mail.ru' & password: '11978Vit@lik'");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("vvitber06@mail.ru", "11978Vit@lik");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));

        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }

    }
