package manager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void type(By locator, String text){
        WebElement element=wd.findElement(locator);
        element.click();
        element.clear();
        if(text!=null) {
            element.sendKeys(text);
        }
    }

    public void click(By locator){
        WebElement element=wd.findElement(locator);
        element.click();
    }
    public boolean isElementPresent(By locator){
        List<WebElement>list=wd.findElements(locator);
        return list.size()>0;
    }

    public boolean isAlertPresent(String message) {
        Alert alert = new WebDriverWait(wd,10)
                .until(ExpectedConditions.alertIsPresent());
        if (alert!=null&&alert.getText().contains(message)){
            //click ok-->
            //pause(5000);
            alert.accept();
            //click cancel--> alert.dismiss();
            //type into alert--> alert.sendKeys("hello");
            return true;
        }
        return false;
    }

    public void pause(int time){
        try {
            Thread.sleep(time)
            ;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
