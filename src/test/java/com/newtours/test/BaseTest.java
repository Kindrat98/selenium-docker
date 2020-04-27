package com.newtours.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {
    protected ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal();

    @BeforeTest
    public void setupDriver(ITestContext ctx){
        String host;
        DesiredCapabilities desiredCapabilities;

        if (System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            desiredCapabilities = DesiredCapabilities.firefox();
        }else {
            desiredCapabilities = DesiredCapabilities.chrome();
        }

        String testName = ctx.getCurrentXmlTest().getName();
        desiredCapabilities.setCapability("name", testName);
        if (System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }
        else {
            host = "localhost";
        }

        String completeURL = "http://" + host + ":4444/wd/hub";
        try {
            WebDriver driver = new RemoteWebDriver(new URL(completeURL), desiredCapabilities);
            driverThreadLocal.set(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

//        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
//        this.driver = new ChromeDriver();
    }

    @AfterTest
    public void quit() {
        driverThreadLocal.get().quit();
    }

}
