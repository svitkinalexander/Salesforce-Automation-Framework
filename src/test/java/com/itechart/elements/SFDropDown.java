package com.itechart.elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class SFDropDown {
    WebDriver driver;
    String label;
    String locator = "//*[contains(@class, 'modal-body')]//span[text()='%s']" +
            "/ancestor::div[contains(@class,'uiInput')]//a";
    String optionLocator = "//*[contains(@class, 'uiMenuList') and contains(@class, 'visible')]//a[@title='%s']";

    public SFDropDown(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
        log.debug(String.format("Selecting option '%s' in drop-down %s\n", option, label));
        driver.findElement(By.xpath(String.format(locator, label))).click();
        WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(String.format(optionLocator, option))));
        driver.findElement(By.xpath(String.format(optionLocator, option))).click();
    }

    public void clear() {
        log.debug(String.format("Clearing option in drop-down %s\n", label));
        driver.findElement(By.xpath(String.format(locator, label))).click();
        WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(String.format(optionLocator, "--None--"))));
        driver.findElement(By.xpath(String.format(optionLocator, "--None--"))).click();
    }
}
