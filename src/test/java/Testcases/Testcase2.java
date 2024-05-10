package Testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Testcase2 {

	@Test
	public void testcase() throws IOException, InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(" https://demo.dealsdray.com/");
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//div[@class='css-sukebr']")).click();
		driver.findElement(By.xpath("(//button[@name='child'])[2]")).click();
		driver.findElement(By.xpath("//div[@class='css-1scr8os']")).click();
		driver.findElement(By.xpath("//*[@class='breadcrumb']/following::div[contains(@class,'MuiBox-root')][3]/button")).click();
		WebElement fileBtn=driver.findElement(By.xpath("//input[@type='file']"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", fileBtn );
		Runtime.getRuntime().exec("D:\\seleniumprojr\\Automation\\src\\test\\java\\TestData\\fileupload.exe"+" "+"D:\\seleniumprojr\\Automation\\src\\test\\java\\TestData\\demo-data.xlsx");
	
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button[text()='Import']")).click();
		driver.findElement(By.xpath("//button[text()='Validate Data']")).click();
		w.until(ExpectedConditions.alertIsPresent()).accept();
		driver.findElement(By.xpath("//button[text()='Submit']")).click();	
		w.until(ExpectedConditions.alertIsPresent()).accept();
		Thread.sleep(5000);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		File destinationFile = new File(".\\Screenshots\\Screenshot-" + System.currentTimeMillis() + ".png");
        try {
            FileUtils.copyFile(screenshotFile, destinationFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
		}
	
	
}

