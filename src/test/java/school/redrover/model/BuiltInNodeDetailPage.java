package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

import java.util.List;

public class BuiltInNodeDetailPage extends BasePage {

    @FindBy(xpath = "//div[@id = 'executors']//table//tr/td[1]")
    private List<WebElement> listBuildExecutors;


    public BuiltInNodeDetailPage(WebDriver driver) {
        super(driver);
    }

    public int getSizeListBuildExecutors() {
        return listBuildExecutors.size();
    }

}