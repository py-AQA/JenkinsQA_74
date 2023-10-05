package school.redrover;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class FirstTest {

    @Test
    public void  testSearch() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.google.com/");

            WebElement textBox = driver.findElement(By.className("gLFyf"));
            textBox.sendKeys("Selenium");


             Thread.sleep(850);
             WebElement searchButton = driver.findElement(By.className("gNO89b"));
             searchButton.click();

            WebElement title = driver.findElement(By.className("yKMVIe"));
            String value = title.getText();
            Assert.assertEquals(value, "Selenium");
        } finally {
            driver.quit();
        }
    }
}
