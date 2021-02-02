package com.mycompany.app.luxoftTraining;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class HomePage {

    public static WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(className = "st0")
    private WebElement menuButton;

    @FindBy(css = "a.two-links")
    //@FindBy(xpath = "//div[@class='hidden-menu-header']/a[1]")
    private WebElement homePage;

    @FindBy(xpath = "//ul[@class='first-menu-addit']/li/a")
    private List<WebElement> firstMenuLinks;


    @FindBy(className = "close-menu")
    private WebElement closeMenuButton;


    @FindBy(className = "hidden-menu")
    private WebElement hamburgerMenuHeader;

    @FindBy(xpath = "//ul[@class='first-menu-addit']//a[text()='Каталог']")
    private WebElement catalogueMenuButton;

    @FindBy(xpath = "//ul[@class='first-menu-addit']//a[text()='Расписание']")
    private WebElement timeTableLink;

    @FindBy(xpath = "//ul[@class='first-menu-addit']//a[text()='Корпоративное обучение']")
    private WebElement corporateTrainingLinkLink;

    @FindBy(xpath = "//ul[@class='first-menu-addit']//a[text()='Оценка персонала']")
    private WebElement personnelAssesmentLink;

    @FindBy(xpath = "//ul[@class='first-menu-addit']//a[text()='Консалтинг']")
    private WebElement consulting;

    @FindBy(xpath = "//ul[@class='first-menu-addit']//a[text()='IT-гуру']")
    private WebElement itGuruLink;

    @FindBy(xpath = "//ul[@class='first-menu-addit']//a[text()='Бесплатные семинары']")
    private WebElement freeSeminarsLink;

    @FindBy(xpath = "//ul[@class='first-menu-addit']//a[text()='Сведения об образовательной организации']")
    private WebElement educationalOrgInfoLink;

    @FindBy(xpath = "//ul[@class='first-menu-addit']//a[text()='Блог ']")
    private WebElement blogLink;

    @FindBy(xpath = "//ul[@class='first-menu-addit']//a[text()='Вакансии']")
    private WebElement vacanciesLink;

    @FindBy(xpath = "//ul[@class='first-menu-addit']//a[text()='Контакты']")
    private WebElement contactsLink;


    @Step("Click Menu -> Login")
    public void enterToLoginForm() {
        menuButton.click();
        System.out.println(homePage.getText());
        homePage.click();
    }

    @Step("Click Menu button")
    public void clickMenu() {
        menuButton.click();

    }

    @Step("Check Menu default state")
    public void verifyMenuDefaultState() {
        Assertions.assertTrue(hamburgerMenuHeader.getAttribute("style").contains("display: none;"));
    }

    @Step("Verify that Main Menu is visible")
    public void verifyMenuIsVisible() {
        Assertions.assertTrue(hamburgerMenuHeader.getAttribute("style").isEmpty());
    }

    @Step("Get list of main menu items")
    public String[] getListOfMenuItems() {
        String[] firstMenuLinksArray = new String[firstMenuLinks.size()];
        int index = 0;
        for (WebElement element : firstMenuLinks) {
            firstMenuLinksArray[index] = element.getText();
            index++;
        }
        return firstMenuLinksArray;
    }

    @Step("Verify that links in Main Menu change their color on mouseover")
    public void verifyLinksColorChange() {
        Actions action = new Actions(driver);
        String elementColor;
        for (WebElement element : firstMenuLinks) {
            action.moveToElement(element).perform();
            elementColor = element.getCssValue("color");
            //Added few lines to debug
            //Test fails because of the bug in Luxoft page.
            //Last item of the list is always overlapped therefore does not change it's color
            System.out.println(elementColor);
            System.out.println(elementColor.equals("rgba(242, 111, 33, 1)"));
            System.out.println(element.getText());
            Assertions.assertTrue(element.getCssValue("color").equals("rgba(242, 111, 33, 1)"));
        }

    }

    @Step("Close Menu Button")
    public void closeMenu() {
        // Assertions.assertTrue();
        closeMenuButton.click();
        // Add verification that menu is not visible
    }

    @Step("Click Catalogue Button")
    public void clickCatalogueMenuLink() {
        catalogueMenuButton.click();
    }

    @Step("Click Расписание Button")
    public void clickTimeTableLink() {
        timeTableLink.click();
    }

    @Step("Click Корпоративное обучение Button")
    public void clickCorporateTrainingLink() {
        corporateTrainingLinkLink.click();
    }

    @Step("Click Оценка персонала Button")
    public void clickPersonnelAssesmentLink() {
        personnelAssesmentLink.click();
    }

    @Step("Click Консалтинг Button")
    public void clickConsultingLink() {
        consulting.click();
    }

    @Step("Click ITгуру Button")
    public void clickitGuruLink() {
        itGuruLink.click();
    }

    @Step("Click Бесплатные семинары Button")
    public void clickfreeSeminarsLink() {
        freeSeminarsLink.click();
    }

    @Step("Click Сведенияоб_образовательной_организации Button")
    public void clickeducationalOrgInfoLink() {
        educationalOrgInfoLink.click();
    }

    @Step("Click Блог Button")
    public void clickblogLink() {
        blogLink.click();
    }

    @Step("Click Вакансии Button")
    public void clickvacanciesLink() {
        vacanciesLink.click();
    }

    @Step("Click Контакты Button")
    public void clickContactsLink() {
        contactsLink.click();
    }


}



