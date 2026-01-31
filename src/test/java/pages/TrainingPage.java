package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TrainingPage extends BasePage {

    public TrainingPage(WebDriver driver) {
        super(driver);
    }

    private By trainingMenu =
            By.xpath("//p[normalize-space()='Training']");
    private By searchTrainingInput =
            By.id("search-training-input");
    private By addTrainingBtn =
            By.id("add-training-button");
    private By nameField =
            By.id("title");
    private By descField =
            By.id("description");
    private By submitTrainingBtn =
            By.id("add-training-submit-button");
    private By firstDetailBtn =
            By.id("button-detail-training-0");
    private By addChapterBtn =
            By.id("add-chapter-button");
    private By chapterName =
            By.id("title");
    private By chapterDesc =
            By.id("description");
    private By submitChapterBtn =
            By.id("add-chapter-submit-button");
    private By firstChapterItem =
            By.xpath("(//div[contains(@id,'chapter-item')])[1]");
    private By addContentBtn =
            By.xpath("//button[contains(@id,'add-content-button')]");
    private By contentTypeTest =
            By.xpath("//span[normalize-space()='Test']/ancestor::label");
    private By contentTitle =
            By.id("input-title--Infinity");
    private By contentDesc =
            By.cssSelector("div[contenteditable='true']");
    private By durationField =
            By.id("input-test-duration--Infinity");
    private By submitContentBtn =
            By.id("submit-button--Infinity");
    private By assignedTab =
            By.id("tabs-training-detail--tab-1");
    private By assignBtn =
            By.id("assign-employee-button");
    private By firstEmployeeAddBtn =
            By.xpath("(//table//tbody//tr//button)[1]");
    private By deadlineDateInput =
            By.xpath("//div[@class='css-q5aiia']//div[2]//input[@type='date']");
    private By submitAssignBtn =
            By.xpath("//button[normalize-space()='Assign Employee']");
    private By successToast =
            By.xpath("//*[contains(text(),'Success')]");

    private void openMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(trainingMenu))
                .click();
    }

    public void openFirstTrainingDetail() {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(firstDetailBtn))
                .click();
        sleep(1000);
    }

    public void searchTraining(String trainingName){
        openMenu();
        WebElement searchBox =
                wait.until(ExpectedConditions.visibilityOfElementLocated(searchTrainingInput));
        searchBox.clear();
        searchBox.sendKeys(trainingName);
        searchBox.sendKeys(Keys.ENTER);
        sleep(1500);
    }

    public void addNewTraining(String name, String desc) {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(addTrainingBtn))
                .click();
        type(nameField, name);
        type(descField, desc);
        click(submitTrainingBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }

    public void openTrainingBySearch(String trainingName){
        wait.until(ExpectedConditions.elementToBeClickable(trainingMenu))
                .click();
        sleep(1000);
        WebElement search =
                wait.until(ExpectedConditions.visibilityOfElementLocated(searchTrainingInput));
        search.clear();
        search.sendKeys(trainingName);
        search.sendKeys(Keys.ENTER);
        sleep(1200);
        wait.until(ExpectedConditions.elementToBeClickable(firstDetailBtn))
                .click();
        sleep(1000);
    }

    public void addNewChapter(String name, String desc) {
        click(addChapterBtn);
        type(chapterName, name);
        type(chapterDesc, desc);
        click(submitChapterBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }

    public void addNewContent(String trainingName,
                              String title,
                              String desc){
        openTrainingBySearch(trainingName);
        wait.until(ExpectedConditions.elementToBeClickable(firstChapterItem))
                .click();
        sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(addContentBtn))
                .click();
        sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(contentTypeTest))
                .click();
        sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(contentTitle))
                .sendKeys(title);
        WebElement editor =
                wait.until(ExpectedConditions.visibilityOfElementLocated(contentDesc));
        editor.click();
        editor.sendKeys(desc);
        wait.until(ExpectedConditions.visibilityOfElementLocated(durationField))
                .sendKeys("1");
        click(submitContentBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }

    public void assignEmployeeToTraining(String deadline) {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(firstDetailBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(assignedTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(assignBtn)).click();
        WebElement addBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstEmployeeAddBtn)
        );
        Actions actions = new Actions(driver);
        actions
                .moveToElement(addBtn)
                .pause(300)
                .click()
                .perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//table)[2]//tbody//tr")
        ));
        wait.until(ExpectedConditions.visibilityOfElementLocated(deadlineDateInput));
        inputDeadline(deadline);
        wait.until(ExpectedConditions.elementToBeClickable(submitAssignBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
    }

    public void inputDeadline(String date) {
        WebElement deadline =
                wait.until(ExpectedConditions.elementToBeClickable(deadlineDateInput));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", deadline);
        deadline.click();
        deadline.sendKeys(Keys.CONTROL + "a");
        deadline.sendKeys(Keys.DELETE);
        deadline.sendKeys(date);
        deadline.sendKeys(Keys.TAB);
    }

    public boolean isSuccessToastDisplayed() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(successToast)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
