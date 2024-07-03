import automationdesign.AbstractComponents.abstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends abstractComponent {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

       //WebElement usernameField = driver.findElement(By.name("user-name"));
        @FindBy(name="user-name")
        WebElement  usernameField;
        @FindBy (name="password")
        WebElement passwordField;
        @FindBy (id="login-button")
        WebElement loginButton;



        public void enterUser(String username, String password){
            usernameField.sendKeys(username);
            passwordField.sendKeys(password);
            loginButton.click();
        }
        public void goTo(){
            driver.get("https://www.saucedemo.com/");
        }

}
