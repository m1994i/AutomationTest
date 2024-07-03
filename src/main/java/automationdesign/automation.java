package automationdesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class automation {
    public static void main(String[]args) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //List<WebElement> swagLabs=driver.findElements(By.xpath("//div[@class='inventory_item_description']"));
        //WebElement products = swagLabs.stream().filter(product-> product.findElement(By.xpath("//div[@class='inventory_item_name ']")).getText().equals("Sauce Labs Backpack")).findFirst().orElse(null);
        //products.findElement(By.cssSelector(".btn_inventory")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_item_description']")));
        addProductToCart(driver, "Sauce Labs Backpack");
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".back.btn_medium")).click();

        addProductToCart(driver, "Sauce Labs Bike Light");
        addProductToCart(driver, "Sauce Labs Bolt T-Shirt");

        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        List<String> cartProducts=driver.findElements(By.xpath("//div[@class='inventory_item_name']")).stream().map(WebElement::getText).collect(Collectors.toList());
        List<String> addedProducts=List.of("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt");
        if(cartProducts.containsAll(addedProducts)){
            System.out.println("Svi proizvodi su u korpi");
        }
        else{
            System.out.println("Nisu svi proizvodi dodati u korpu");
        }
        driver.findElement(By.cssSelector(".checkout_button")).click();
        List<WebElement> checkout=List.of(driver.findElement(By.id("first-name")), driver.findElement(By.id("last-name")), driver.findElement(By.id("postal-code")));
        List<String> checkoutInfo=List.of("Milan", "Ivanovic", "11550");

        for(int i =0; i<checkout.size(); i++){
            checkout.get(i).sendKeys(checkoutInfo.get(i));

        }//for petlja za unosenje podataka na chekcout stranici, koristi se lista elemenata da bi se pronasli ime, prezime i postanski broj a zatim se pravi lista sta se unosi
        driver.findElement(By.id("continue")).click();
        // Pronalazak cena pojedinačnih proizvoda
        List<WebElement> productsPrices=driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        double expectedTotal =productsPrices.stream().mapToDouble(price -> Double.parseDouble(price.getText().substring(1))).sum();
        // Pronalazak prikazane ukupne cene
        WebElement totalPrice = driver.findElement(By.cssSelector(".summary_total_label"));
        double displayedTotal=Double.parseDouble(totalPrice.getText().split("\\$")[1]);
        WebElement tax=driver.findElement(By.xpath("//div[@class='summary_tax_label']"));
        double taxPay=Double.parseDouble(tax.getText().split("\\$")[1]);
        // Provera ukupne cene
        if(expectedTotal + taxPay == displayedTotal){
            System.out.println("Ukupna cena je tacna");
        }
        else{
            System.out.println("Ukupna cena nije tacna");
        }
        driver.findElement(By.id("finish")).click();
        System.out.println(driver.findElement(By.xpath("//h2[@class='complete-header']")).getText());
        driver.quit();

    }
    private static void addProductToCart(WebDriver driver, String productName){
        List<WebElement>products=driver.findElements(By.xpath("//div[@class='inventory_item_description']"));
        WebElement product = products.stream().filter(p->p.findElement(By.xpath(".//div[@class='inventory_item_name ']")).getText().equals(productName)).findFirst().orElse(null);
        if(product!=null){
            product.findElement(By.cssSelector(".btn_inventory")).click();
        }



    }
}

