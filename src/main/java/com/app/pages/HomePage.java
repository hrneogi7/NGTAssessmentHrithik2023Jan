package com.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class HomePage {
    private static WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void searchForProducts() throws InterruptedException {
        WebElement searchBar=driver.findElement(By.xpath("//input"));
        searchBar.sendKeys("Tshirts");
        driver.findElement(By.xpath("//a[@class=\"desktop-submit\"]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public void verifySearchResults(){
        String expectedPageTitle = "Tshirts - Buy from the Latest Collection of Tshirt Online at Best Price | Myntra";
        String expectedHeaderTitle="T-Shirts For Men & Women";

        WebElement headerTitleWeb = driver.findElement(By.xpath("//h1[@class=\"title-title\"]"));
        String pageTitle = driver.getTitle();
        String headerTitle = headerTitleWeb.getText();

        Assert.assertEquals(pageTitle,expectedPageTitle,"Search results are not loaded properly");
        Assert.assertEquals(headerTitle,expectedHeaderTitle,"Search results are not loaded properly");
    }
}
