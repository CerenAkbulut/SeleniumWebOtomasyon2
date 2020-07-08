package org.example;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class LoginPage {
    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public LoginPage(WebDriver webDriver){
        this.webDriver=webDriver;
        this.webDriverWait=new WebDriverWait(webDriver,30,150);
    }

    public void login(String username , String password) throws InterruptedException {

        // Login //
        webDriver.get("https://www.trendyol.com/");
        webDriver.findElement(By.cssSelector("[class='fancybox-item fancybox-close']")).click();
        Assert.assertEquals("En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da", webDriver.getTitle());
        webDriver.findElement(By.id("accountBtn")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.id("email")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("loginSubmit")));

        // Search //

        WebElement searchData = webDriver.findElement(By.className("search-box"));
        searchData.sendKeys("Bilgisayar");
        searchData.sendKeys(Keys.ENTER);


        // Get Price First //
        WebElement priceElementOne = webDriver.findElement(By.className("prc-box-sllng"));
        String sPriceElementO =priceElementOne.getText();

        // Select an Item //
        webDriver.findElement(By.cssSelector("[class='p-card-img']")).click();

        // Add to Box //
        Thread.sleep(3000);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='pr-in-btn add-to-bs']"))).click();


        // Get Price Second //
        WebElement priceElementTwo = webDriver.findElement(By.className("prc-slg"));
        String sPriceElementT = priceElementTwo.getText();

        if(sPriceElementT.equals(sPriceElementO)){

            webDriver.findElement(By.id("myBasketListItem")).click();
            webDriver.findElement(By.cssSelector("[class='ty-numeric-counter-button']")).click();
        }
        webDriver.findElement(By.cssSelector("[class='i-trash']")).click();
        webDriver.findElement(By.cssSelector("[class='btn-item btn-remove']")).click();


        String message="Sepetinizde ürün bulunmamaktadır.";

        String isEmptyBox = webDriver.findElement(By.cssSelector("[class^='emptyBasketInfoBox'] div p span")).getText();

        Assert.assertTrue(message + "alınmadı",message.equals(isEmptyBox));

        System.out.println(message + "Mesajı Alındı.");

    }
}
