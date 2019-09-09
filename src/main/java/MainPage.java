import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainPage {


    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Head of Site
    private By LinkAddRelease = By.xpath("//a[text()='Добавление релиза']");
    private By LinkTracker = By.xpath("//a[text()='Трекер']");
    private By LinkSearchTheForume = By.xpath("//a[text()='Поиск по форуму']");
    private By LinkBookmarks = By.xpath("//a[text()='Закладки']");
    private By LinkRSS = By.xpath("//a[text()='RSS']");
    private By LinkRSSNewTopics = By.xpath("//a[text()='RSS new topics']");
    private By LinkMyClub = By.xpath("//a[text()='Мой Клуб']");
    private By LinkPortal = By.xpath("//a[text()='Портал']");
    private By LinkFAQ = By.xpath("//a[text()='FAQ']");
    private By LinkRegulation = By.xpath("//a[text()='Правила']");
    private By LinkProfile = By.xpath("//a[text()='Профиль']");
    private By LinkNoNewMassage = By.xpath("//a[text()='Нет новых Л.С.']");
    private By LinkLogIn = By.xpath("//a[text()='Вход']");
    private By LinkLogOut = By.xpath("//a[contains(text(), 'Выход')]");

    // Down Part of Site
    private By LinkUserAgreement = By.xpath("//a[text()='Пользовательское соглашение']");
    private By LinkForOwners = By.xpath("//a[text()='Для правообладателей']");
    private By LinkAdvertisingOnTheSite = By.xpath("//a[text()='Реклама на сайте']");

}
