package Testcases;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

public class Testcase1 {

	@Test
	public void testcase() throws InterruptedException {
        
        String[] urls = {"https://www.getcalley.com/", "https://www.getcalley.com/calley-call-from-browser/", "https://www.getcalley.com/calley-pro-features/", "https://www.getcalley.com/best-auto-dialer-app/", "https://www.getcalley.com/how-calley-auto-dialer-app-works/"};
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        // List of resolutions
        Dimension[] resolutions = {new Dimension(1920, 1080), new Dimension(1366, 768), new Dimension(1536, 864),
                                    new Dimension(414, 896), new Dimension(375, 667)};

        // Set up WebDriver for Chrome
        
        WebDriver chromeDriver = new ChromeDriver(chromeOptions);

        // Set up WebDriver for Firefox
        WebDriver firefoxDriver = new FirefoxDriver();

        // List of browsers
        WebDriver[] drivers = {chromeDriver, firefoxDriver};
        String[] browserNames = {"Chrome", "Firefox"};

        // Loop through each browser
        for (int i = 0; i < drivers.length; i++) {
            WebDriver driver = drivers[i];
            String browserName = browserNames[i];

            // Loop through each URL
            for (String url : urls) {
                // Loop through each resolution
                for (Dimension resolution : resolutions) {
                    // Navigate to the URL
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    driver.manage().window().maximize();
                    driver.get(url);

                    driver.manage().window().setSize(resolution);

                    // Capture screenshot
                    File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    String fileName = browserName + "/" + resolution.getWidth() + "x" + resolution.getHeight() + "/Screenshot-" + System.currentTimeMillis() + ".png";
                    File destinationFile = new File(fileName);
                    try {
                        FileUtils.copyFile(screenshotFile, destinationFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            // Close the WebDriver after capturing screenshots for all URLs and resolutions
            driver.quit();
        }
    }}