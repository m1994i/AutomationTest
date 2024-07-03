import automationdesign.AbstractComponents.abstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends abstractComponent {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

   // List<WebElement> swagLabs=driver.findElements(By.xpath("//div[@class='inventory_item_description']"));
        @FindBy(xpath="//div[@class='inventory_item_description']")
        List<WebElement> products;

        By productsBy = By.xpath("//div[@class='inventory_item_description']");
        By itemDescription=By.xpath("//div[@class='inventory_item_description']");

    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;
    }
    public WebElement getProductByName(String productName){
        WebElement product = getProductList().stream().filter(p->p.findElement(By.xpath(".//div[@class='inventory_item_name ']")).getText().equals(productName)).findFirst().orElse(null);
        return product;
        }

    public void addProductToCart(String productName){
            WebElement prod = getProductByName(productName);
            prod.findElement(By.cssSelector(".btn_inventory")).click();
            waitForElementToAppear(itemDescription);
    }
    public void getCart(){
        WebElement clickButtonCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        clickButtonCart.click();
    }




}
