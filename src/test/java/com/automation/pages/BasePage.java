package com.automation.pages;


import com.automation.step_defs.Hooks;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(Hooks.getDriver(), this);
    }
    public WebDriver driver = Hooks.getDriver();

    public void waitArrowToBeUnlocked(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        wait.until(ExpectedConditions.attributeContains(element, "class", "active"));
    }

    public void waitVideoToBeStarted(By by, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
        wait.until(ExpectedConditions.textToBe(by, "0:01"));
    }

    public void handleIFrame(WebElement iFrame){
        driver.switchTo().frame(iFrame);
    }

    public void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            try {
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
                y++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
