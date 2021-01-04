package com.mycompany.app.luxoftTraining;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

@Epic("Epic: Verify Sign In Form")
@Story("US Number")
@ExtendWith(TestWatcher.class)
public class VerifySignInForm {
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
        //    waitVar = new WebDriverWait(driver, 7);
        driver.navigate().to("https://www.luxoft-training.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //  loginForm = new LoginForm(driver);
        //  waitVar.until(ExpectedConditions.presenceOfElementLocated(By.className("sign-in close-notice btn-primary")));
        //   driver.findElement(By.className("sign-in close-notice btn-primary")).click();
    }


    @Test
    @Description("Verify that error is displayed when user logged in with invalid credentials.")
    void loginFormTest() throws InterruptedException {
        homePage = new HomePage(driver);
        loginForm = new LoginForm(driver);
        homePage.enterToLoginForm();
        loginForm.enterCredentials();
        loginForm.ClickLoginButton();
        loginForm.VerifyErrorForInvalidCreds();
    }

    @ParameterizedTest
    @Description("Verify that left menu links are correct")
    @CsvFileSource(resources = "/menu_items.csv", numLinesToSkip = 1, delimiter = ',')
    void menuItemsTest(int id, String name) {
        String[] menuItems;
        homePage = new HomePage(driver);
        homePage.clickMenu();
        menuItems = homePage.getListOfMenuItems();
        Assertions.assertTrue(name.equalsIgnoreCase(menuItems[id]));
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

