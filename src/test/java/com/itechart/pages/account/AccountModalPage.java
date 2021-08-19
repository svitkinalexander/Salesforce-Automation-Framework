package com.itechart.pages.account;

import com.itechart.elements.SFDropDown;
import com.itechart.elements.SFInput;
import com.itechart.elements.SFLookUp;
import com.itechart.elements.TextArea;
import com.itechart.pages.BasePage;
import com.itechart.models.Account;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AccountModalPage extends BasePage {
    private final By SAVE_BUTTON_LOCATOR = By.cssSelector("[title='Save']");
    private final By CANCEL_BUTTON_LOCATOR = By.cssSelector("[title='Cancel']");
    private final By CROSS_BUTTON_LOCATOR = By.xpath("//button[@title='Close this window']");
    private final By SAVE_AND_NEW_BUTTON_LOCATOR = By.cssSelector("[title='Save & New']");
    private final By EMPTY_REQUIRED_FIELD_LOCATOR = By.xpath("//li[contains(text(),'These required fields must be completed')]");
    private final By MODAL_HEADER_LOCATOR = By.xpath("//div[@class='slds-modal__header']");

    public AccountModalPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(MODAL_HEADER_LOCATOR));
            log.info("Account Modal is open");
            return false;
        } catch (TimeoutException | NoSuchElementException e) {
            log.warn("Account Modal is not open");
            log.warn(e.getLocalizedMessage());
            return true;
        }
    }

    public AccountModalPage enterData(Account account) {
        log.info("Entering Account Data");
        new SFInput(driver, "Account Name").write(account.getAccountName());
        new SFDropDown(driver, "Type").select(account.getType());
        new SFInput(driver, "Website").write(account.getWebsite());
        new TextArea(driver, "Description").write(account.getDescription());
        new SFLookUp(driver, "Parent Account").selectOption(account.getParentAccount());
        new SFInput(driver, "Phone").write(account.getPhone());
        new SFDropDown(driver, "Industry").select(account.getIndustry());
        new SFInput(driver, "Employees").write(account.getEmployees());
        new TextArea(driver, "Billing Street").write(account.getBillingStreet());
        new SFInput(driver, "Billing City").write(account.getBillingCity());
        new SFInput(driver, "Billing State/Province").write(account.getBillingState());
        new SFInput(driver, "Billing Zip/Postal Code").write(account.getBillingPostalCode());
        new SFInput(driver, "Billing Country").write(account.getBillingCountry());
        new TextArea(driver, "Shipping Street").write(account.getShippingStreet());
        new SFInput(driver, "Shipping City").write(account.getShippingCity());
        new SFInput(driver, "Shipping State/Province").write(account.getShippingState());
        new SFInput(driver, "Shipping Zip/Postal Code").write(account.getShippingPostalCode());
        new SFInput(driver, "Shipping Country").write(account.getShippingCountry());
        return this;
    }

    public AccountModalPage clearData() {
        log.info("Clearing Account Data");
        new SFInput(driver, "Account Name").clear();
        new SFDropDown(driver, "Type").clear();
        new SFInput(driver, "Website").clear();
        new TextArea(driver, "Description").clear();
        new SFLookUp(driver, "Parent Account").clear();
        new SFInput(driver, "Phone").clear();
        new SFDropDown(driver, "Industry").clear();
        new SFInput(driver, "Employees").clear();
        new TextArea(driver, "Billing Street").clear();
        new SFInput(driver, "Billing City").clear();
        new SFInput(driver, "Billing State/Province").clear();
        new SFInput(driver, "Billing Zip/Postal Code").clear();
        new SFInput(driver, "Billing Country").clear();
        new TextArea(driver, "Shipping Street").clear();
        new SFInput(driver, "Shipping City").clear();
        new SFInput(driver, "Shipping State/Province").clear();
        new SFInput(driver, "Shipping Zip/Postal Code").clear();
        new SFInput(driver, "Shipping Country").clear();
        return this;
    }

    public AccountDetailsPage clickSaveButton() {
        log.info("Clicking Save button");
        driver.findElement(SAVE_BUTTON_LOCATOR).click();
        return new AccountDetailsPage(driver);
    }

    public AccountDetailsPage clickSaveAndNewButton() {
        log.info("Clicking Save And New button");
        driver.findElement(SAVE_AND_NEW_BUTTON_LOCATOR).click();
        return new AccountDetailsPage(driver);
    }

    public AccountListViewPage clickCancelButton() {
        log.info("Clicking Cancel button");
        driver.findElement(CANCEL_BUTTON_LOCATOR).click();
        return new AccountListViewPage(driver);
    }

    public AccountListViewPage clickCrossButton() {
        log.info("Clicking Cross button");
        driver.findElement(CROSS_BUTTON_LOCATOR).click();
        return new AccountListViewPage(driver);
    }

    public boolean isEmptyRequiredFieldsValidationError() {
        log.info("Checking whether all required fields are not blank");
        return driver.findElement(EMPTY_REQUIRED_FIELD_LOCATOR).isDisplayed();
    }
}
