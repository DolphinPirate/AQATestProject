import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import sun.misc.ThreadGroupUtils;

import java.util.concurrent.TimeUnit;

public class AQATestsNMMClub {

   private WebDriver driver;

   private String urlProject = "http://nnmclub.to/";
    private String titleProject = "NNM-Club";

   private String urlProject2 = "https://n.iss.one/";

   private String link;

    @BeforeTest
    public void preCondition() {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void afterTests(){
        //driver.quit();
    }

    // ====================================
    // 1. открываем google.ru
    //2. ищем "nnm-club"
    //3. в найденных результатах кликаем по элементу
    // содержащему текст "NNM-Club: Торрент-трекер"
    //4. проверка: проверяем что мы перешли
    // на страницу http://nnmclub.to/

    @Test
    public void firstTest(){

        By searchInputForText = By.xpath("//input[@name=\"q\"]");
        By searchOurUrl = By.xpath("//*[contains(text(), 'https://nnmclub.to/')]");

        driver.get("https://www.google.ru/");

        driver.findElement(searchInputForText).sendKeys("nnm-club");
        driver.findElement(searchInputForText).sendKeys(Keys.ENTER);
        driver.findElement(searchOurUrl).click();
        Assert.assertEquals(driver.getCurrentUrl(), urlProject);

    }

    // ====================================
    //Предусловия:
    //Создать руками пользователя для http://nnmclub.to.
    //1. Перейти на http://nnmclub.to
    //2. Зайти под созданным пользователем
    //3. Проверка: убедится что вход успешен

    @Test
    public void loginTest(){
        Thread.currentThread();
        driver.findElement(By.xpath("//a[text()='Вход']")).click();
        String mainTab = driver.getWindowHandle();
        driver.switchTo().window(mainTab);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ArtemOdessaAQA");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("zzd-xU2-6Fr-Jrv");
        driver.findElement(By.xpath("//input[@name='login']")).click();
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
        By exit = By.xpath("//a[contains(text(), 'Выход')]");
        Assert.assertTrue(driver.findElement(exit).isDisplayed());

    }

    // ====================================
    //Залониться на http://nnmclub.to
    //Осуществить поиск по трекеру (поле Поиск:):
    //искать фразу java
    //только торренты за последние 3 месяца
    //Проверка: осуществить проверку, что найденные торренты соответсвуют критериям поиска

    @Test
    public void searchOnTreker(){
        Thread.currentThread();
        driver.get("http://nnmclub.to/forum/tracker.php");
        By inputSearch = By.xpath("//table[@class='menubot']//input[@name='nm']");
        By inputSearch2 = By.cssSelector("input#nm.post");
        By buttonSearch = By.xpath("//input[@class='mbutton']");
        By buttonSearch2 = By.xpath("//input[@class=\"liteoption\"]");
        By dropList = By.xpath("//select[@name='tm']");
        By mounth3Last = By.xpath("//select[@name='tm']//option[@value='90']");
        driver.findElement(inputSearch).sendKeys("Java");
       // String mainTab = driver.getWindowHandle();
       // driver.switchTo().window(mainTab);
        driver.findElement(buttonSearch).click();
        driver.findElement(inputSearch2).sendKeys("Java");
        driver.findElement(dropList).click();
        driver.findElement(mounth3Last).click();

        driver.findElement(buttonSearch2).click();

        // driver.findElement(dropList).click();
        //        WebDriverWait wait = (new WebDriverWait(driver, 2));
        //        wait.until(ExpectedConditions.);
        // driver.findElement(mounth3Last).click();
    }
}