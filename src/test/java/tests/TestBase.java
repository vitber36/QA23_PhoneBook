package tests;

import manager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    static ApplicationManager app=new ApplicationManager();

    @BeforeSuite
    public void setUp(){
        app.inIt();
    }
    @AfterSuite

    public void tearDown(){
        //app.stop();

    }
}
