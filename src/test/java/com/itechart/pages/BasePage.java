package com.itechart.pages;

import com.itechart.utils.PropertyReader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

@Log4j2
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected PropertyReader propertyReader = new PropertyReader("src/test/resources/configuration.properties");
    protected final By USERPROFILE_BUTTON_LOCATOR = By.xpath("//*[contains(@class, 'slds-global-actions__item')]//ancestor::button[contains(@class, 'branding-userProfile-button')]");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public boolean isPageOpened() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(USERPROFILE_BUTTON_LOCATOR));
        return driver.findElement(USERPROFILE_BUTTON_LOCATOR).isDisplayed();
    }

    public void validateInput(String label, String expectedInput) {
        String locator = "//div[contains(@class, 'active')]//span[text()='%s']/ancestor::force-record-layout-item//" +
        "*[@data-output-element-id='output-field']";
        WebElement input = driver.findElement(By.xpath(String.format(locator, label)));
        String actualInput = input.getText();
        log.debug("Validating Expected input: {} and actual input: {}", expectedInput, actualInput);
        Assert.assertTrue(input.getText().contains(expectedInput), String.format("%s input is not correct", label)
        );
    }
}
