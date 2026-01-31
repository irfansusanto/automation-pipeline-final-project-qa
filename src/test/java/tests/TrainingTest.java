package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.TrainingPage;

public class TrainingTest extends BaseTest {

    private TrainingPage trainingPage;

    private static String trainingName;
    private String chapterName;

    @BeforeMethod
    public void setupTest() {
        trainingPage = new TrainingPage(driver);
        if (trainingName == null) {
            trainingName =
                    "Automation Training " + System.currentTimeMillis();
        }
        chapterName = "Chapter 1";
    }

    @Test(priority = 1)
    public void addTrainingPositive() {
        trainingPage.addNewTraining(
                trainingName,
                "QA Automation - Irfan"
        );
        Assert.assertTrue(
                trainingPage.isSuccessToastDisplayed(),
                "Gagal tambah training"
        );
    }

    @Test(priority = 2, dependsOnMethods = "addTrainingPositive")
    public void addChapterPositive() {
        trainingPage.searchTraining(trainingName);
        trainingPage.openFirstTrainingDetail();
        trainingPage.addNewChapter(
                chapterName,
                "Chapter 1"
        );
        Assert.assertTrue(
                trainingPage.isSuccessToastDisplayed(),
                "Gagal tambah chapter"
        );
    }

    @Test(priority = 3, dependsOnMethods = "addChapterPositive")
    public void addContentPositive() {
        trainingPage.searchTraining(trainingName);
        trainingPage.openFirstTrainingDetail();
        trainingPage.addNewContent(
                trainingName,
                "Automation Test",
                "Test Automation Content"
        );
        Assert.assertTrue(
                trainingPage.isSuccessToastDisplayed(),
                "Gagal tambah content"
        );
    }

    @Test(priority = 4, dependsOnMethods = "addContentPositive")
    public void assignEmployeePositive() {
        trainingPage.searchTraining(trainingName);
        trainingPage.openFirstTrainingDetail();
        trainingPage.assignEmployeeToTraining("31032026");
        Assert.assertTrue(
                trainingPage.isSuccessToastDisplayed(),
                "Gagal assign employee"
        );
    }
}
