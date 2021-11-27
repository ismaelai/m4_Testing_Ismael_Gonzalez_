package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class BuyProductTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        String dir = System.getProperty("user.dir"); // ruta del proyecto
        String driverUrl = "/drivers/chromedriver.exe";
        String url = dir + driverUrl;
        System.setProperty("webdriver.chrome.driver", url);
        driver = new ChromeDriver(); // Google Chrome
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void buyProduct() throws Exception {
        String URL="http://automationpractice.com/index.php";

        // Open URL and Maximize browser window
        driver.get(URL);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        //Click on Sign in
        driver.findElement(By.linkText("Sign in")).click();
        //Login
        driver.findElement(By.id("email")).sendKeys("test1249@test.com");
        driver.findElement(By.id("passwd")).sendKeys("PKR@PKR");
        driver.findElement(By.id("SubmitLogin")).click();
        //Click on Women
        driver.findElement(By.linkText("WOMEN")).click();

        WebElement SecondImg=driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[3]/div/div[1]/div/a[1]/img"));
        WebElement MoreBtn=driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[3]/div/div[2]/div[2]/a[2]/span"));
        Actions actions=new Actions(driver);
        actions.moveToElement(SecondImg).moveToElement(MoreBtn).click().perform();

        //Change quantity by 2
        driver.findElement(By.id("quantity_wanted")).clear();
        driver.findElement(By.id("quantity_wanted")).sendKeys("2");

        //Select size as L
        WebElement Sizedrpdwn=driver.findElement(By.xpath("//*[@id='group_1']"));
        Select oSelect=new Select(Sizedrpdwn);
        oSelect.selectByVisibleText("L");

        //Select Color
        driver.findElement(By.id("color_13")).click();

        //Click on add to cart
        driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")));


        //Click on proceed
        WebElement proceed = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a"));
        proceed.click();

        //Checkout page Proceed
        new WebDriverWait(driver, Duration.ofSeconds(2))
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span")));

        driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/form/p/button/span")).click();
        //Agree terms&Conditions
        driver.findElement(By.xpath("//*[@id=\"cgv\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/p/button/span")).click();

        //Click on Pay by Bank wire
        driver.findElement(By.xpath("//*[@id=\"HOOK_PAYMENT\"]/div[1]/div/p/a")).click();
        //Confirm the order
        driver.findElement(By.xpath("//*[@id=\"cart_navigation\"]/button/span")).click();

        //Get Text
        String ConfirmationText=driver.findElement(By.xpath("//*[@id=\"center_column\"]")).getText();

        // Verify that Product is ordered
        if(ConfirmationText.contains("complete")) {
            System.out.println("Order Completed: Test Case Passed");
        }
        else {
            System.out.println("Order Not Successfull: Test Case Failed");
        }

    }
}