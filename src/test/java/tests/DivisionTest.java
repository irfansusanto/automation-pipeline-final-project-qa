package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DivisionPage;

public class DivisionTest extends BaseTest {

    @Test
    public void addNewDivisionPositive() {
        DivisionPage divisionPage =
                new DivisionPage(driver);
        divisionPage.addNewDivision(
                "QA Automation",
                "Automation Division"
        );
        Assert.assertTrue(
                divisionPage.isSuccessToastDisplayed()
        );
    }
}
