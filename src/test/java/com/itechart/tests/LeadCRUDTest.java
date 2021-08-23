package com.itechart.tests;

import com.itechart.models.Lead;
import com.itechart.pages.lead.LeadDetailsPage;
import com.itechart.pages.lead.LeadListViewPage;
import com.itechart.tests.configurations.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LeadCRUDTest extends BaseTest {

    @Test(retryAnalyzer = Retry.class, description = "Create Read Update Lead record")
    public void createReadUpdateDeleteLeadRecord() {
        Lead lead = new Lead("New", "Mr.", "Record", "Success",
                "Boss", "pp41@mailinator.com", "54321", "12345",
                "Hot", "test.com", "Google", "Banking",
                "10", "Partner", "Test Street", "Test City",
                "NY");
        LeadListViewPage leadListViewPage = new LeadListViewPage(driver);
        LeadDetailsPage detailsPage =
                leadListViewPage
                        .open()
                        .clickNewButton()
                        .enterData(lead)
                        .clickSaveButton();
        Assert.assertTrue(detailsPage.isPageOpened(), "Title is not correct");
        Lead lead2 = new Lead("New", "Mr.", "Record", "Success",
                "Boss", "pp41@mailinator.com", "54321", "12345",
                "Hot", "test.com", "Google", "Banking",
                "10", "Partner", "Test Street", "Test City",
                "NY");
        boolean isRecordDeleted =
                detailsPage
                        .openDetails()
                        .validate(lead)
                        .clickEditDetailsButton()
                        .clearData()
                        .enterData(lead2)
                        .clickSaveButton()
                        .openDetails()
                        .validate(lead2)
                        .clickDeleteButton()
                        .delete()
                        .isSuccessDeleteMessageDisplayed();
        Assert.assertTrue(isRecordDeleted, "Record deletion failed");
    }
}
