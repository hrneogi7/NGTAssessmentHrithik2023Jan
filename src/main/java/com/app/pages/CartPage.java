package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Properties;

//(//li[@class="product-base"])[1]
public class CartPage {
    private static WebDriver driver;
    public CartPage(WebDriver driver){
        this.driver = driver;
    }

//    public void login() throws Exception {
//        String rootFolder = System.getProperty("user.dir");
//        Properties properties = new Properties();
//        properties.load(new FileInputStream(rootFolder + "/src/main/resources/data.properties"));
//
//        String email = properties.getProperty("email");
//        String password = properties.getProperty("password");
//
//        WebElement emailElement = driver.findElement(By.id("mobileNumberPass"));
//        WebElement passwordElement = driver.findElement(By.xpath("//input[@type=\"password\"]"));
//        Thread.sleep(1000);
//        WebElement loginButton = driver.findElement(By.xpath("//button"));
//        Thread.sleep(1000);
//
//        emailElement.sendKeys(email);
//        passwordElement.sendKeys(password);
//        loginButton.click();
//    }
    public void addFirstProductToBag() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.searchForProducts();

        WebElement firstProduct = driver.findElement(By.xpath("(//li[@class=\"product-base\"])[1]"));
        firstProduct.click();
        Thread.sleep(1000);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.findElement(By.xpath("//button[.='L']")).click();
        driver.findElement(By.xpath("//div[.='ADD TO BAG']")).click();
    }
    public void verifyAddToCart(){
        driver.findElement(By.xpath("//header/div[2]/div[2]/a[2]/span[2]")).click();
        String expectedText = "1/1 ITEMS SELECTED";
        String actualItemText = driver.findElement(By.xpath("//div[@class='bulkActionStrip-message']")).getText();

        Assert.assertEquals(actualItemText,expectedText,"Items addition failed");
    }
}
