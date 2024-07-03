import automationdesign.CartPage;
import automationdesign.CheckoutPage;
import automationdesign.CompletePage;
import automationdesign.YourCart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class automationTest {
    public static void main(String[] args) {
        String productName = "Sauce Labs Backpack";
        List<String> productName2= List.of("Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.enterUser("standard_user", "secret_sauce");
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        List<WebElement> products=productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        productCatalogue.getCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.continueShopping();
        for(String product : productName2){
            productCatalogue.addProductToCart(product);
        }
        productCatalogue.getCart();
        List<String> expectedProducts = List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt");
        boolean allProductsInCart = cartPage.CheckCartProducts(expectedProducts);
        if (allProductsInCart){
            System.out.println("Svi proizvodi su dodati u korpu");
        }
        else {
            System.out.println("Nisu svi proizvodi dodati u korpu");
        }
        if (allProductsInCart){
            cartPage.procedToCheckout();
        }
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.CheckoutInformation("Milan", "Ivanovic", "11550");
        YourCart yourCart = new YourCart(driver);
        boolean pricesAreCorrect = yourCart.verifyPrices();
        if (pricesAreCorrect){
            System.out.println("Cene iz korpe su tacne");
        }
        else {
            System.out.println("Cene iz korpe nisu tacne");
        }
        yourCart.CheckoutButton();
        CompletePage completePage = new CompletePage(driver);
        boolean isMessageDisplayed=completePage.isMessageDisplayed();
        if (isMessageDisplayed){
            System.out.println("Ocekivana poruka je ispisana");
        }
        else {
            System.out.println("Ocekivana poruka nije ispisana");
        }
        completePage.BackHomeButt();
        driver.quit();

    }
}