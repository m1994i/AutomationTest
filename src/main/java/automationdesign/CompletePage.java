package automationdesign;

import automationdesign.AbstractComponents.abstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompletePage extends abstractComponent {
    WebDriver driver;
    @FindBy(xpath = "//h2[@class='complete-header']")
    WebElement text;
    @FindBy(id = "back-to-products")
    WebElement backHomeButton;

    public CompletePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }
    public boolean isMessageDisplayed(){
        return text.isDisplayed() && text.getText().equals("Thank you for your order!");

    }
    public void BackHomeButt(){
        backHomeButton.click();
    }

}
