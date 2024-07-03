package automationdesign;

import automationdesign.AbstractComponents.abstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends abstractComponent {
    WebDriver driver;


    @FindBy(id = "continue")
    WebElement continueButton;
    @FindBy(id = "first-name")
    WebElement firstNameField;
    @FindBy(id = "last-name")
    WebElement lastNameField;
    @FindBy(id="postal-code")
    WebElement postalCodeField;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


        public void CheckoutInformation(String firstName, String lastName, String postalCode ){
          firstNameField.sendKeys(firstName);
          lastNameField.sendKeys(lastName);
          postalCodeField.sendKeys(postalCode);
          continueButton.click();
        }


}
