package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

public class AuthTest {

    @BeforeAll
    static void setup() {
        open("http://localhost:9999");
    }




    @Test
    public void shouldSendSuccessfulyLoginWithStatusActive() {
        Configuration.holdBrowserOpen = true;
        DataGenerator.RegistrationDto registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        DataGenerator.setRequest(registeredUser);
        $("//*[@name='login']").setValue(registeredUser.getLogin());
        $("//*[@name='password").setValue(registeredUser.getPassword());
        $(".button__content").click();
        $("[id='root']").shouldBe(exactText("Личный кабинет"));




    }

    @Test
    public void shouldSendInvalidLogin() {
        Configuration.holdBrowserOpen = true;
        DataGenerator.RegistrationDto registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        DataGenerator.setRequest(registeredUser);
        var invalidLogin = DataGenerator.getRandomLogin();
        $x("//*[@name='login']").setValue(invalidLogin);
        $x("//*[@name='password").setValue(registeredUser.getPassword());
        $(".button__content").click();
        $("[data-test-id=\"error-notification\"]").shouldBe(exactText("Ошибка! Неверно Неверно указан логин или пароль"));




    }

    @Test
    public void shouldSendBlockedUser() {
        Configuration.holdBrowserOpen = true;
        DataGenerator.RegistrationDto registeredUser = DataGenerator.Registration.getRegisteredUser("blocked");
        DataGenerator.setRequest(registeredUser);
        $x("//*[@name='login']").setValue(registeredUser.getLogin());
        $x("//*[@name='password").setValue(registeredUser.getPassword());
        $(".button__content").click();
        $(withText("Пользователь заблокирован")).shouldBe(Condition.visible);




    }

    @Test
    public void shouldSendInvalidPassword() {
        Configuration.holdBrowserOpen = true;
        DataGenerator.RegistrationDto registeredUser = DataGenerator.Registration.getRegisteredUser("blocked");
        DataGenerator.setRequest(registeredUser);
        var invalidPassword = DataGenerator.getRandomPassword();
        $x("//*[@name='login']").setValue(invalidPassword);
        $x("//*[@name='password").setValue(registeredUser.getPassword());
        $(".button__content").click();
        $("[data-test-id=\"error-notification\"]").shouldHave(exactText("Ошибка! Неверно Неверно указан логин или пароль"));




    }



}
