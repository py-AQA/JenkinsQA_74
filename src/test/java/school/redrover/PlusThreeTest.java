
package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlusThreeTest {

    public static final String USERNAME = "TestUser1";

    public static final String PASSWORD = "qwert12345";
    public static final String FULL_NAME = "Akiko";
    public static final String EMAIL = "Akiko@gmail.com";
    public static final String CURRENT_ADDRESS = "USA";
    public static final String PERMANENT_ADDRESS = "USA1";

    @Test
    public void testSearch() {
        WebDriver driver = new EdgeDriver();
        driver.get("https://demoqa.com/text-box");

        WebElement fullName = driver.findElement(By.id("userName"));
        fullName.sendKeys(FULL_NAME);


        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys(EMAIL);

        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys(CURRENT_ADDRESS);

        WebElement permanentAddress = driver.findElement(By.id("permanentAddress"));
        permanentAddress.sendKeys(PERMANENT_ADDRESS);

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();


        WebElement nameR = driver.findElement(By.id("name"));
        String value = nameR.getText();
        Assert.assertEquals("Name:" + FULL_NAME, value);

        WebElement emailR = driver.findElement(By.id("email"));
        String value1 = emailR.getText();
        Assert.assertEquals("Email:" + EMAIL, value1);

        WebElement currentAddreesR = driver.findElement(By.xpath("//*[@class=\"border col-md-12 col-sm-12\"]/p[3]"));
        String value2 = currentAddreesR.getText();
        Assert.assertEquals("Current Address :" + CURRENT_ADDRESS, value2);

        WebElement permanentAddressR = driver.findElement(By.xpath("//*[@class=\"border col-md-12 col-sm-12\"]/p[4]"));
        String value3 = permanentAddressR.getText();
        Assert.assertEquals("Permananet Address :" + PERMANENT_ADDRESS, value3);

        driver.quit();
    }

    @Test(description = "Создание/регистрация пользователя в банке")
    public void createUser() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");

        WebElement firstName = driver.findElement(By.id("customer.firstName"));
        firstName.sendKeys("Test");

        WebElement lastName = driver.findElement(By.id("customer.lastName"));
        lastName.sendKeys("Test");

        WebElement address = driver.findElement(By.id("customer.address.street"));
        address.sendKeys("SPB");

        WebElement city = driver.findElement(By.id("customer.address.city"));
        city.sendKeys("SPB");

        WebElement state = driver.findElement(By.id("customer.address.state"));
        state.sendKeys("LO");

        WebElement zipCode = driver.findElement(By.id("customer.address.zipCode"));
        zipCode.sendKeys("123-415");

        WebElement userName = driver.findElement(By.id("customer.username"));
        userName.sendKeys(USERNAME);

        WebElement password = driver.findElement(By.id("customer.password"));
        password.sendKeys(PASSWORD);

        WebElement repeatPass = driver.findElement(By.id("repeatedPassword"));
        repeatPass.sendKeys(PASSWORD);

        WebElement ssn = driver.findElement(By.id("customer.ssn"));
        ssn.sendKeys("123456");

        WebElement register = driver.findElement(By.cssSelector("[value='Register']"));
        register.submit();


        WebElement title = driver.findElement(By.xpath("//div[@id='rightPanel']/h1"));
        String resTitle = title.getText();
        Assert.assertEquals(resTitle, "Welcome " + USERNAME);

        WebElement result = driver.findElement(By.xpath("//div[@id='rightPanel']/p"));
        String resText = result.getText();
        Assert.assertEquals(resText, "Your account was created successfully. You are now logged in.");

        driver.quit();
    }
}
