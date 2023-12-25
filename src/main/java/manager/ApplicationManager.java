package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationManager {

    WebDriver wd;

    public void inIt(){
       wd=new ChromeDriver();
       wd.navigate().to("https://telranedu.web.app/");
    }

    public void stop(){
        wd.quit();

    }
}
