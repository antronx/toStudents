package com.mycompany.app.luxoftTraining;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    @FindBy(xpath = "//div[@class='hidden-menu-header']/a[1]")
    private WebElement homePage;

    @FindBy(className = "first-menu-addit")
    private List<WebElement> firstMenuLinks;

    @Step ("Click Menu -> Login")
    public void enterToLoginForm() {
        menuButton.click();
        System.out.println(homePage.getText());
        homePage.click();
    }



}


