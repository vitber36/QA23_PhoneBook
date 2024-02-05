package manager;


import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    Logger logger= LoggerFactory.getLogger(ApplicationManager.class);

    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    //WebDriver wd;
    EventFiringWebDriver wd;

    HelperUser helperUser;
    HelperContact helperContact;


    public void inIt() {
        if(browser.equals(BrowserType.CHROME)) {
            //wd = new ChromeDriver();
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("All tests rus in Chrome Browser");
        } else if (browser.equals(BrowserType.FIREFOX)) {
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("All tests rus in FIREFOX Browser");
        } else if (browser.equals(BrowserType.EDGE)) {
            wd= new EventFiringWebDriver(new EdgeDriver());
            logger.info("All tests rus in EDGE Browser");

        }

        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://telranedu.web.app/");
        logger.info("The link --->" + wd.getCurrentUrl());
        helperUser = new HelperUser(wd);
        helperContact = new HelperContact(wd);
        wd.register(new ListenerWD());
    }

    public HelperContact getHelperContact() {
        return helperContact;
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public void stop(){
        wd.quit();

    }
}
