package com.mycompany.app.luxoftTraining;


import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginForm {

    public static WebDriver driver;

    public LoginForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "lb01")
    private WebElement loginField;

    @FindBy(id = "lb02")
    private WebElement passwordField;

    @FindBy(id = "lb03-styler")
    private WebElement rememberMeCheckbox;

    @FindBy(name = "Login")
    private WebElement loginButton;

    @FindBy(className = "errortext")
    private WebElement errorMessage;

    @Step("Enter Credentials -> check Remember Me checkbox - Click Log In")
    public void enterCredentials() {
        loginField.sendKeys("Anton");
        passwordField.sendKeys("qwerty");
        rememberMeCheckbox.click();
    }

    @Step("Click Log In button.")
    public void ClickLoginButton() {
        loginButton.click();
    }

    @Step("Verify that error for Invalid Credentials is correct")
    public void VerifyErrorForInvalidCreds() {
        Assert.assertTrue(errorMessage.getText().contains("Неверный логин или пароль."));
    }


}

