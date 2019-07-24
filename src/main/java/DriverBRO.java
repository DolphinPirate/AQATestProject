import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;
class DriverBRO{
   private static WebDriver driver;

    public static WebDriver startBrouserChrome(String url) {
        BruserChrome();
        driver.get(url);
        setPropertyWindow();
        setPropertyTimeOut();
        return driver;
    }

    public static void closeDriver() {
        driver.close();
    }
    public static WebDriver BruserChrome(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }
    public static WebDriver setPropertyWindow() {
        driver.manage().window().maximize();
        return driver;
    }
    public static WebDriver setPropertyTimeOut() {
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        return driver;
    }
}