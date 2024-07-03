package automationdesign;

import automationdesign.AbstractComponents.abstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YourCart extends abstractComponent {
    WebDriver driver;

    @FindBy (css=".cart_button")
    WebElement cartButton;
    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> itemPrices;
    @FindBy(xpath = "//div[@class='summary_total_label']")
    WebElement total;
    @FindBy(xpath = "//div[@class='summary_tax_label']")
    WebElement taxPay;
    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    WebElement itemTotal;



    public YourCart(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public double getProductPricesTotal(){
        return itemPrices.stream()
                .mapToDouble(price -> Double.parseDouble(price.getText().replace("$", "")))
                .sum();
    }
    public double getDisplayedSubtotal(){
        return Double.parseDouble(itemTotal.getText().replace("Item total: $", ""));

    }
    public double getDisplayedTax(){
        return Double.parseDouble(taxPay.getText().replace("Tax: $",""));

    }
    public double getDisplayedTotal(){
        return Double.parseDouble(total.getText().replace("Total: $",""));

    }
    public boolean verifyPrices(){
     double productTotal = getProductPricesTotal();
     double displayedSubtotal = getDisplayedSubtotal();
     double displayedTax= getDisplayedTax();
     double displayedTotal = getDisplayedTotal();
        return productTotal == displayedSubtotal && displayedTax + displayedSubtotal == displayedTotal;
    }
    public void CheckoutButton(){
        cartButton.click();
    }
}
