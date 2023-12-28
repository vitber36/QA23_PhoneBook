package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }

    }

    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("vitber06@mail.ru","1978Vit@lik");
        app.getHelperUser().submitLogin();

        //Assert.assertEquals();
        //Assert.assertTrue();
        //Assert.assertNotEquals();
        //Assert.assertFalse();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }
    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("vitber06@mail.ru", "1978Vit@lik");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    }
