package com.mycompany.app.luxoftTraining;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


@Epic("Epic: Tests to verify Main Menu items")
@Story("0002 - Hamburger Menu")
@ExtendWith(TestWatcher.class)
public class VerifyMainMenu {

    static ChromeDriver driver;
    static WebDriverWait waitVar;
    private static HomePage homePage;
    private static LoginForm loginForm;

    @BeforeAll
    static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver_win32/chromedriver.exe");
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        waitVar = new WebDriverWait(driver, 10);
        driver.navigate().to("https://www.luxoft-training.ru/");
        waitVar.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='sign-in close-notice btn-primary']")));
        driver.findElement(By.xpath("//a[@class='sign-in close-notice btn-primary']")).click();
        driver.manage().window().maximize();
    }

    @Test
    @Description("Verify that Menu is not visible by default")
    public void menuDefaultStateCheck() {
        homePage = new HomePage(driver);
        homePage.verifyMenuDefaultState();
    }

    @Test
    @Description("Verify that Menu is displayed after clicking on Hamburger icon")
    public void menuVisibilityCheck() {
        homePage = new HomePage(driver);
        homePage.verifyMenuDefaultState();
        homePage.clickMenu();
        homePage.verifyMenuIsVisible();
    }

    @Test
    @Description("Verify that Menu is closed after pressing Close button")
    public void closeMenu() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.closeMenu();
        homePage.verifyMenuDefaultState();
    }

    @ParameterizedTest
    @Description("Verify that left menu links are correct")
    @CsvFileSource(resources = "/menu_items.csv", numLinesToSkip = 1, delimiter = ',')
    void menuItemsTestPresence(int id, String name) {
        String[] menuItems;
        homePage = new HomePage(driver);
        homePage.clickMenu();
        menuItems = homePage.getListOfMenuItems();
        Assertions.assertTrue(name.equalsIgnoreCase(menuItems[id]));
    }


    @Test
    @Description("Verify that user is redirected to correct page after pressing 'Каталог' link from Hamburger Menu")
    void verifyRedirectToCatalogue() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickCatalogueMenuLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/training/katalog_kursov/"));
    }

    @Test
    @Description("Verify that user is redirected to correct page after pressing 'Расписание' link from Hamburger Menu")
    void verifyRedirectToSchedule() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickTimeTableLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/timetable/"));
    }

    @Test
    @Description("Verify that user is redirected to correct page after pressing 'Расписание' link from Hamburger Menu")
    void verifyRedirectToTimeTable() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickTimeTableLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/timetable/"));
    }


    @Test
    @Description("Verify that user is redirected to correct page after pressing 'Корпоративное обучение' link from Hamburger Menu")
    void verifyRedirectToCorporateTrainingLink() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickCorporateTrainingLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/corporate/"));
    }

    @Test
    @Description("Verify that user is redirected to correct page after pressing 'Оценка персонала' link from Hamburger Menu")
    void verifyRedirectToPersonnelAssesmentLink() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickPersonnelAssesmentLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/testing/"));
    }


    @Test
    @Description("Verify that user is redirected to correct page after pressing 'Консалтинг' link from Hamburger Menu")
    void verifyRedirectToConsulting() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickConsultingLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/consulting/"));
    }

    @Test
    @Description("Verify that user is redirected to correct page after pressing 'IT-гуру' link from Hamburger Menu")
    void verifyRedirectToItGuruLink() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickitGuruLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/training/it-guru/"));
    }

    @Test
    @Description("Verify that user is redirected to correct page after pressing 'Бесплатные семинары' link from Hamburger Menu")
    void verifyRedirectToFreeSeminarsLink() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickfreeSeminarsLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/personal_test/learning/"));
    }

    @Test
    @Description("Verify that user is redirected to correct page after pressing 'Сведения об образовательной организации' link from Hamburger Menu")
    void verifyRedirectToEducationalOrgInfoLink() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickeducationalOrgInfoLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/educational_information/"));
    }

    @Test
    @Description("Verify that user is redirected to correct page after pressing 'Блог' link from Hamburger Menu")
    void verifyRedirectToBlogLink() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickblogLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/about/news/"));
    }

    @Test
    @Description("Verify that user is redirected to correct page after pressing 'Вакансии' link from Hamburger Menu")
    void verifyRedirectToVacanciesLink() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickvacanciesLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/vacancies/"));
    }

    @Test
    @Description("Verify that user is redirected to correct page after pressing 'Контакты' link from Hamburger Menu")
    void verifyRedirectToContactsLink() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.clickContactsLink();
        Assertions.assertTrue(driver.getCurrentUrl().contains("https://www.luxoft-training.ru/contacts/moscow/"));
    }

    @Test
    @Description("verify links change their color to orange on MouseOver")
    public void colorChange() {
        homePage = new HomePage(driver);
        homePage.clickMenu();
        homePage.verifyLinksColorChange();
    }


    @AfterEach
    @Description("Get back to the home page")
    void goToHomePage() {
        driver.navigate().to("https://www.luxoft-training.ru/");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
