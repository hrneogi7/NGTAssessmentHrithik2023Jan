package com.app.test;

import com.app.pages.CartPage;
import com.app.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.Properties;

public class TestRunner1 {
    HomePage homePage;
    CartPage cartPage;
    private static WebDriver driver;

    @BeforeMethod
    public void setUp() throws Exception {
        String rootFolder = System.getProperty("user.dir");

        Properties properties = new Properties();
        properties.load(new FileInputStream(rootFolder + "/src/main/resources/data.properties"));

        System.setProperty("webdriver.chrome.driver", rootFolder +
                "/src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);

        String url = properties.getProperty("url2");

        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    public void verifySearchFunction() throws InterruptedException {
        homePage.searchForProducts();
        homePage.verifySearchResults();
    }
    @Test
    public void verifyCartAddFunction() throws Exception {
        cartPage.addFirstProductToBag();
        cartPage.verifyAddToCart();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
