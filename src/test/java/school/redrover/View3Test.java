package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class View3Test extends BaseTest {

    private void createFreeStyleProject(String newFreeStyleProjectName) {
        returnToJenkinsHomepage();
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.xpath("//input[@name = 'name']")).sendKeys(newFreeStyleProjectName);
        getDriver().findElement(By.xpath("//li[@class = 'hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.xpath("//button[@id = 'ok-button']")).click();
    }

    private void createListViewWithoutAssociatedJob(String newListViewName) {
        returnToJenkinsHomepage();
        getDriver().findElement(By.xpath("//a[@href = '/newView']")).click();
        getDriver().findElement(By.xpath("//input[@name = 'name']")).sendKeys(newListViewName);
        getDriver().findElement(By.xpath("//label[@for = 'hudson.model.ListView']")).click();
        getDriver().findElement(By.xpath("//button[@id = 'ok']")).click();
    }

    private void createListViewWithAssociatedJob(String newListViewName) {
        returnToJenkinsHomepage();
        getDriver().findElement(By.xpath("//a[@href = '/newView']")).click();
        getDriver().findElement(By.xpath("//input[@name = 'name']")).sendKeys(newListViewName);
        getDriver().findElement(By.xpath("//label[@for = 'hudson.model.ListView']")).click();
        getDriver().findElement(By.xpath("//button[@id = 'ok']")).click();
        getDriver().findElement(By.xpath("//div[@class = 'listview-jobs']/span/span[1]")).click();
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();
    }

    private void addNewDescriptionForTheView(String listViewName, String newDescriptionForTheView) {
        returnToJenkinsHomepage();
        getDriver().findElement(By.xpath("//a[@href = '/view/" + listViewName + "/']")).click();
        getDriver().findElement(By.xpath("//a[@id = 'description-link']")).click();
        getDriver().findElement(By.xpath("//textarea[@name = 'description']")).sendKeys(newDescriptionForTheView);
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();
    }

    private void returnToJenkinsHomepage() {
        getDriver().findElement(By.xpath("//a[@id = 'jenkins-home-link']")).click();
    }

    @Test
    public void testListViewRenaming() {
        final String newFreeStyleProjectName = "FreeStyleTestProject";
        final String newListViewName = "ListViewTest";
        final String renamedListViewName = "RenamedListViewTest";

        createFreeStyleProject(newFreeStyleProjectName);
        createListViewWithAssociatedJob(newListViewName);
        returnToJenkinsHomepage();

        getDriver().findElement(By.xpath("//a[@href = '/view/" + newListViewName + "/']")).click();
        getDriver().findElement(By.xpath("//a[@href = '/view/" + newListViewName + "/configure']")).click();
        getDriver().findElement(By.xpath("//input[@name = 'name']")).clear();
        getDriver().findElement(By.xpath("//input[@name = 'name']")).sendKeys(renamedListViewName);
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@class = 'tab active']/a")).getText(),
                renamedListViewName);
    }

    @Test
    public void testAddingDescriptionForTheView() {
        final String newFreeStyleProjectName = "FreeStyleTestProject";
        final String newListViewName = "ListViewTest";
        final String newDescriptionForTheView = "Test description for the List View";

        createFreeStyleProject(newFreeStyleProjectName);
        createListViewWithoutAssociatedJob(newListViewName);
        returnToJenkinsHomepage();

        getDriver().findElement(By.xpath("//a[@href = '/view/" + newListViewName + "/']")).click();
        getDriver().findElement(By.xpath("//a[@id = 'description-link']")).click();
        getDriver().findElement(By.xpath("//textarea[@name = 'description']")).sendKeys(newDescriptionForTheView);
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id = 'description']/div[1]")).getText(),
                newDescriptionForTheView);
    }

    @Test
    public void testEditingDescriptionForTheView() {
        final String newFreeStyleProjectName = "FreeStyleTestProject";
        final String newListViewName = "ListViewTest";
        final String newDescriptionForTheView = "Test description for the List View";
        final String editedDescriptionForTheView = "New Test description for the List View instead of the previous one";

        createFreeStyleProject(newFreeStyleProjectName);
        createListViewWithoutAssociatedJob(newListViewName);
        addNewDescriptionForTheView(newListViewName, newDescriptionForTheView);
        returnToJenkinsHomepage();

        getDriver().findElement(By.xpath("//a[@href = '/view/" + newListViewName + "/']")).click();
        getDriver().findElement(By.xpath("//a[@id = 'description-link']")).click();
        getDriver().findElement(By.xpath("//textarea[@name = 'description']")).clear();
        getDriver().findElement(By.xpath("//textarea[@name = 'description']")).sendKeys(editedDescriptionForTheView);
        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@id = 'description']/div[1]")).getText(),
                editedDescriptionForTheView);
    }
}