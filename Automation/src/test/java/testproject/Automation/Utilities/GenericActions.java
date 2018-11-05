package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class GenericActions {
    public static Logger logger = LogManager.getLogger(org.testng.TestRunner.class.getName());

    public static boolean retryAction(WebDriver driver, WebElement element){
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                element.click();
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
                ignoreStaleExceptionAndClick(driver,element);
            }
            attempts++;
        }
        return result;
    }

    public static void sleepAWhile(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        try {
            new WebDriverWait(driver, PropertiesReader.maxWaitTime).until(ExpectedConditions.elementToBeClickable(element));
            return;
        }
        catch (NoSuchElementException e){
            waitUntilElementIsFound(driver,element);
        }
        catch (StaleElementReferenceException e) {
            ignoreStaleExceptionAndClick(driver,element);
        }
         catch(Exception e){
            logger.info("Unable to locate " + element);
            e.printStackTrace();
        }
    }

    private static void waitUntilElementIsFound(WebDriver driver, WebElement element) {
        WebDriverWait wait =new WebDriverWait(driver, 30);
        wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(element));
        return;
    }

    public static void waitForButtonToAppear(WebDriver driver,WebElement element) throws InterruptedException {
        try{
            //new WebDriverWait(driver,PropertiesReader.maxWaitTime).until(ExpectedConditions.textToBePresentInElement(element,"button"));}
            new WebDriverWait(driver,PropertiesReader.maxWaitTime).until(ExpectedConditions.attributeContains(element,"type","submit"));}
        catch (Exception e){e.printStackTrace();}
    }

    private static void ignoreStaleExceptionAndClick(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void clickAndWait(WebDriver driver, WebElement element){
        try {
            new WebDriverWait(driver, PropertiesReader.maxWaitTime).until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
            element.click();
        }
        catch(StaleElementReferenceException e){
            ignoreStaleExceptionAndClick(driver,element);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static WebElement setRadioButton(String value, List<WebElement> list) {
        try {
            WebElement button = null;
            for (WebElement e : list) {
                if (e.getAttribute("Value").toString().equalsIgnoreCase(value)) {
                    button = e;
                }
            }
            return button;
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
            logger.info("Cannot click Radio Button " + value);
            return null;
        }
    }

    public static void selectDropdownValue(WebDriver driver, WebElement element, String value ){
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            ((JavascriptExecutor)driver).executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++){" +
                    "if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }", element, value);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void clearAndSendKeys(WebDriver driver, WebElement element, String theText) {
        element.clear();
        element.sendKeys(theText);
    }

    public static WebDriver navigationAction(WebDriver driver,String topMenu, String listMenu){
        try {
            if (!(topMenu == null || listMenu == null)) {
                WebElement topElement = driver.findElement(By.xpath("//a[contains(text(),'" + topMenu + "')]"));
                WebElement listElement = driver.findElement(By.xpath("//a[contains(text(),'"+listMenu+"')]"));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                Actions builder = new Actions(driver);
                builder.moveToElement(topElement).build().perform();
                new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'" + listMenu + "')]")));
                js.executeScript("arguments[0].click();", listElement);
                return driver;
            }
            else{
                logger.info("Incorrect navigation provided");
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static WebDriver validateErrorOnPage(WebDriver driver) {
        try {
            if (new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath(PropertiesReader.error))).isDisplayed()) {
                PropertiesReader.errormessage = driver.findElement(By.xpath(PropertiesReader.errorDetail)).getText();
                return driver;
            } else
                return driver;
        }catch(TimeoutException e){
            logger.info("No error on page");
            return driver;
        }
    }

}


