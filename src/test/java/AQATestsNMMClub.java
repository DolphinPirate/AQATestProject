import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AQATestsNMMClub {

    private WebDriver driver;

    @BeforeMethod
    public void preCondition() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterTests() {
       // driver.quit();
    }

    // ====================================
    // 1. открываем google.ru
    //2. ищем "nnm-club"
    //3. в найденных результатах кликаем по элементу
    // содержащему текст "NNM-Club: Торрент-трекер"
    //4. проверка: проверяем что мы перешли
    // на страницу http://nnmclub.to/

    @Test
    public void firstTest() {
        driver.get("https://www.google.ru/");
        By searchInputForText = By.xpath("//input[@name='q']");
        By searchOurUrl = By.xpath("//*[contains(text(), 'https://nnmclub.to/')]");
        driver.findElement(searchInputForText).sendKeys("nnm-club");
        driver.findElement(searchInputForText).sendKeys(Keys.ENTER);
        driver.findElement(searchOurUrl).click();
        List mainTab = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(mainTab.get(1).toString());
        Assert.assertTrue(driver.getCurrentUrl().contains("nnmclub.to"));
    }

    // ====================================
    //Предусловия:
    //Создать руками пользователя для http://nnmclub.to.
    //1. Перейти на http://nnmclub.to
    //2. Зайти под созданным пользователем
    //3. Проверка: убедится что вход успешен

    @Test
    public void loginTest() {
        driver.get("http://nnmclub.to");
        driver.findElement(By.xpath("//a[text()='Вход']")).click();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ArtemOdessaAQA");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("zzd-xU2-6Fr-Jrv");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.ENTER);
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
    public void searchOnTrekerTest() throws ParseException {

        driver.get("http://nnmclub.to");

        // LOGIN
        driver.findElement(By.xpath("//a[text()='Вход']")).click();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ArtemOdessaAQA");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("zzd-xU2-6Fr-Jrv");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.ENTER);

        // SEARCH
        By inputSearch = By.xpath("//table[@class='menubot']//input[@name='nm']");
        By buttonSearch2 = By.xpath("//input[@class='liteoption']");
        By dropList = By.xpath("//select[@name='tm']");
        By LastMounth3 = By.xpath("//select[@name='tm']//option[@value='90']");
        driver.findElement(inputSearch).sendKeys("Java");
        driver.findElement(inputSearch).sendKeys(Keys.ENTER);
        driver.findElement(dropList).click();
        driver.findElement(LastMounth3).click();
        driver.findElement(buttonSearch2).click();

        // Имя торрента
        By nameOfTorrent = By.xpath("//a[@class='genmed topictitle']");
        List<WebElement> textOfName = driver.findElements(nameOfTorrent);
        for(WebElement element : textOfName){
            element.getText();
            //System.out.println(s);
            Assert.assertTrue(element.getText().contains("Java"),"No have word 'JAVA'!");
        }

        // Время торрента
        By dataOfTorrent = By.xpath("//td[@title='Добавлено']");
        List<WebElement> textOfData = driver.findElements(dataOfTorrent);

        Calendar currentDate = Calendar.getInstance();
        Calendar pastDate = Calendar.getInstance();
        pastDate.add(Calendar.MONTH, -3);
//        System.out.println(currentDate+"\n");
//        System.out.println(pastDate+"\n");
        SimpleDateFormat dateTorrents = new SimpleDateFormat("dd-MM-yyyy\nHH:mm");

        for (WebElement element : textOfData) {
            Date date = dateTorrents.parse(element.getText());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            Assert.assertTrue(cal.after(pastDate) && cal.before(currentDate),"Doesnt match criteria!");
            //System.out.println(date);
        }
    }

    //Category test
    //1. Залониться на http://nnmclub.to
    //2. Перейти в раздел tracker
    //3. Необходимо осуществить проверку функционала для выбора категорий:
    //3.1 Написать универсальный метод для выбора категории по тексту
    //3.2 Написать универсальный метод для проверки отсортированных результатов
    //(учесть наличие нескольких страниц, предусмотреть ограничение количества проверяемых страниц)
    //4. Написать тест для проверки фильтрации по категориям
    //5. Опционально: сделать параметризацию теста средствами testng для проверки нескольких категорий

    void choiceCategory(String name){
        By categoryList = By.xpath("//select[@id='fs']//option");
        driver.findElement(categoryList).click();
        List<WebElement> listCategory = driver.findElements(categoryList);
        for (WebElement element : listCategory){
            element.getText().contains(name);
        }
    }

    void fileChoiceCategory(String name){

        By listFilesCategory = By.xpath("//*[@title='Форум']//following::a[@class='gen']");

        List<WebElement> torrentFilsListCategory = driver.findElements(listFilesCategory);

        By pageTorrents = By.xpath("//span[@class='nav']//b[2]");
        String page = driver.findElement(pageTorrents).getText();
        int intPages = Integer.parseInt(page);
        By pageNext = By.xpath("//a[text()= 'След.']");

        By linksPageTorrents = By.xpath("//span[@class='nav']//following-sibling::a");
        List<WebElement> torrentsPageOfFiles = driver.findElements(linksPageTorrents);

        for (WebElement element : torrentFilsListCategory) {
                if (intPages > 3  || intPages <=3){
                    for (int pageNow = 1 ; pageNow <=3 ; pageNow++){
                        driver.findElement(pageNext).click();
                    }
                }
                element.getText().contains(name);
                System.out.println(element.getSize());
            }
    }

    @Test
    public void  categoryTest(){

        driver.get("http://nnmclub.to");

        String name = "BBC";

        By LastAllTime = By.xpath("//select[@name='tm']//option[@value='-1']");
        By SearchOnTreker = By.xpath("//input[@name='submit']");

        // LOGIN
        driver.findElement(By.xpath("//a[text()='Вход']")).click();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("ArtemOdessaAQA");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("zzd-xU2-6Fr-Jrv");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//a[text()='Трекер']")).click();
        driver.findElement(LastAllTime).click();
        choiceCategory(name);
        driver.findElement(SearchOnTreker).click();
        fileChoiceCategory(name);

    }
}