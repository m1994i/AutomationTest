package automationdesign;

import automationdesign.AbstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends abstractComponent {
        WebDriver driver;

        @FindBy(css = ".back.btn_medium")
        WebElement continueShoppingBut;
        @FindBy(id = "checkout")
        WebElement checkoutButton;
        @FindBy(xpath = "//div[@class='inventory_item_name']")
        List<WebElement> cartProductElements;
        By cartProductsBy = By.xpath("//div[@class='inventory_item_name']");

        public CartPage(WebDriver driver){
            super(driver);
            this.driver=driver;
            PageFactory.initElements(driver, this);
        }

        public void continueShopping(){
            continueShoppingBut.click();
        }
        public void procedToCheckout(){
            checkoutButton.click();
        }
        public boolean CheckCartProducts(List<String> expectedProducts){
            waitForElementToAppear(cartProductsBy);
            List<String> cartProducts = cartProductElements.stream().map(WebElement::getText).collect(Collectors.toList());
            return cartProducts.containsAll(expectedProducts) && expectedProducts.containsAll(cartProducts);
        }

}
