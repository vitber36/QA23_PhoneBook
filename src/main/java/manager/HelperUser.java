package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperUser extends HelperBase{

    //---------------login------------------

    public HelperUser(WebDriver wd) {
        super(wd);
    }

 public void openLoginRegistrationForm(){
     //WebElement loginTab=wd.findElement(By.xpath("//a[text()='LOGIN']"));
     ////css-->a[href="/login"]
     //loginTab.click();
     click(By.xpath("//a[text()='LOGIN']"));
 }


 public void fillLoginRegistrationForm(String email,String password){
       //WebElement emailInput=wd.findElement(By.xpath("//input[placeholder='Email']"));
       //emailInput.click();
       // emailInput.clear();
       // emailInput.sendKeys(email);
     type(By.xpath("//input[@placeholder='Email']"),email);

      //WebElement passwordInput=wd.findElement(By.xpath("//input[last()]"));
      //passwordInput.click();
      //passwordInput.clear();
      // passwordInput.sendKeys(password);
     type(By.xpath("//input[last()]"),password);
 }

    public void fillLoginRegistrationForm(User user){
        type(By.xpath("//input[@placeholder='Email']"), user.getEmail());
        type(By.xpath("//input[last()]"),user.getPassword());
    }
 public void submitLogin(){
        click(By.xpath("//button[text()='Login']"));
 }


    public boolean isLogged() {
        return isElementPresent(By.xpath("//*[text()='Sign Out']"));
    }

    public void logout() {
        click(By.xpath("//*[text()='Sign Out']"));
    }



    //--------------registration----------------------

    public void submitRegistration() {
        click(By.name("registration"));
    }

    public boolean isRegistered() {
        WebDriverWait wait=new WebDriverWait(wd,5);
        return wait.until(ExpectedConditions.textToBePresentInElement
                (wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")),
                        "No Contacts here!"));
        //return isElementPresent((By.xpath("//*[text()=' No Contacts here!']")));

    }

    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();

    }

}
