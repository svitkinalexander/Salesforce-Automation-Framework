package com.itechart.pages.account;

import com.itechart.models.Account;
import com.itechart.pages.BasePage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class AccountDetailsPage extends BasePage {

    private final By ACCOUNT_TITLE = By.xpath("//div[@class='entityNameTitle slds-line-height--reset' and contains(text(), 'Account')]");
    private final By DETAILS_TAB = By.xpath("//a[@data-label='Details']");
    private static final By EDIT_DETAILS_BUTTON_LOCATOR = By.xpath("//button[@name='Edit']");
    private final By DELETE_BUTTON = By.xpath("//button[@name ='Delete']");
    private final By DELETE_MODAL_TITLE = By.xpath("//div[@class='modal-container slds-modal__container']//h2");
    private final By DELETE_MODAL_BUTTON = By.xpath("//div[@class='modal-container slds-modal__container']//button[@title= 'Delete']");

    public AccountDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCOUNT_TITLE));
        log.info("Account Details page is open");
        return getTitle().contains("Account");
    }

    public String getTitle() {
        wait.until(ExpectedConditions.presenceOfElementLocated(ACCOUNT_TITLE));
        return driver.findElement(ACCOUNT_TITLE).getText();
    }

    public AccountDetailsPage openDetails() {
        log.info("Opening Account Details");
        WebElement element = new WebDriverWait(driver, 5).until(ExpectedConditions
                .elementToBeClickable(DETAILS_TAB));
        driver.findElement(DETAILS_TAB).click();
        return this;
    }

    public AccountModalPage clickEditDetailsButton() {
        log.info("Clicking Account Edit button");
        driver.findElement(EDIT_DETAILS_BUTTON_LOCATOR).click();
        return new AccountModalPage(driver);
    }

    public AccountDetailsPage validate(Account account) {
        log.info("Validating Account Data");
        validateInput("Account Name", account.getAccountName());
        validateInput("Type", account.getType());
        validateInput("Description", account.getDescription());
        validateInput("Industry", account.getIndustry());
        validateInput("Website", account.getWebsite());
        validateInput("Phone", account.getPhone());
        validateInput("Employees", account.getEmployees());
        validateInput("Billing Address", account.getBillingAddress());
        validateInput("Shipping Address", account.getShippingAddress());
        validateInput("Account Owner", account.getAccountOwner());
        validateInput("Parent Account", account.getParentAccount());
        return this;
    }

    public AccountDetailsPage clickDeleteButton() {
        try {
            log.info("Clicking Account Delete button");
            driver.findElement(DELETE_BUTTON).click();
        } catch (StaleElementReferenceException e) {
            log.warn("Cannot find Delete button");
            log.warn(e.getLocalizedMessage());
            driver.findElement(DELETE_BUTTON).click();
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(DELETE_MODAL_TITLE));
        return this;
    }

    public boolean isModalOpened() {
        wait.until(ExpectedConditions.presenceOfElementLocated(DELETE_MODAL_TITLE));
        log.info("Account modal is open");
        return driver.findElement(DELETE_MODAL_TITLE).getText().contains("Delete");
    }

    public AccountListViewPage delete() {
        log.info("Deleting Account");
        if (!isModalOpened()) throw new RuntimeException("Delete modal is not opened");
        driver.findElement(DELETE_MODAL_BUTTON).click();
        return new AccountListViewPage(driver);
    }
}
