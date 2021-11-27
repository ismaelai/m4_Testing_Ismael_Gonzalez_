package com.example.m4_Testing_Ismael;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


class EcomSignUp {

    // Navegador
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
      void signUp() throws Exception {
        String URL = "http://automationpractice.com/index.php";

        driver.get(URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));
        driver.manage().window().maximize();

        //Click on Sign in
        driver.findElement(By.linkText("Sign in")).click();

        //Enter email address
        driver.findElement(By.cssSelector("[name='email_create']")).sendKeys("ismamontiel@gmail.com");

        //Click on "Create an account"
        driver.findElement(By.xpath("//button[@name=\"SubmitCreate\"]")).click();

        //Select Title
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"id_gender1\"]")));

        driver.findElement(By.xpath("//input[@id=\"id_gender1\"]")).click();
        driver.findElement(By.name("customer_firstname")).sendKeys("Test User");
        driver.findElement(By.name("customer_lastname")).sendKeys("ismaelai");
        driver.findElement(By.id("passwd")).sendKeys("1234567");

        // Enter your address
        driver.findElement(By.id("firstname")).sendKeys("Test User");
        driver.findElement(By.id("lastname")).sendKeys("Gonzalez");
        driver.findElement(By.id("company")).sendKeys("Garrido");
        driver.findElement(By.id("address1")).sendKeys("calle de la amargura");
        driver.findElement(By.id("city")).sendKeys("wichita");

        // Select State
        WebElement statedropdown = driver.findElement(By.name("id_state"));
        Select oSelect = new Select(statedropdown);
        oSelect.selectByValue("4");

        driver.findElement(By.name("postcode")).sendKeys("51838");

        // Select Country
        WebElement countrydropDown = driver.findElement(By.name("id_country"));
        Select oSelectC = new Select(countrydropDown);
        oSelectC.selectByVisibleText("United States");

        //Enter Mobile Number
        driver.findElement(By.id("phone_mobile")).sendKeys("234567890");
        driver.findElement(By.xpath("//input[@name=\"alias\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"alias\"]")).sendKeys("Office");
        driver.findElement(By.name("submitAccount")).click();
        String userText = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).getText();

        // Validate that user has created
        if (userText.contains("ismaelai")) {
            System.out.println("User Verified,Test case Passed");
        } else {
            System.out.println("User Verification Failed,Test case Failed");
        }
    }
    }
