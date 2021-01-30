package com.mycompany.app.luxoftTraining;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@Epic("Epic: Tests to verify Main Menu items")
@Story("0002 - Hamburger Menu")
@ExtendWith(TestWatcher.class)
public class VerifyMainMenu {

    static ChromeDriver driver;
    static WebDriverWait waitVar;
    private static HomePage homePage;
    private static LoginForm loginForm;

    @BeforeAll
    static void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        //       options.setHeadless(true);
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver_win32/chromedriver.exe");
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        waitVar = new WebDriverWait(driver, 30);
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
