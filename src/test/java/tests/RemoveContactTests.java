package tests;

import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("vitber06@mail.ru").withPassword("1978Vit@lik"));
        }
        //app.getHelperContact().provideContacts();//if list<3 ===> add 3 contacts
    }
    @Test
    public void removeFirstContact(){
        //Assert size list less by one
    }
    @Test public void removeAllContacts(){
        //"no contacts here"
    }
}