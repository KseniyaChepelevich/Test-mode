package ru.netology;

import com.codeborne.selenide.Configuration;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.DataGeneratorInvalid;
import ru.netology.data.RegistrationDto;

import static io.restassured.RestAssured.given;

public class AuthTest {

    static RegistrationDto info = DataGenerator.Registration.generateInfo("ru");

    static RegistrationDto infoNew = DataGenerator.Registration.generateInfoEng("eng");

    static RegistrationDto infoPasswordArabicLetters = DataGenerator.Registration.generateInfoPasswordIsArabicLetters("ru");

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

//    @BeforeAll
//    static void setUpAll() {
//
//        // сам запрос
//        given() // "дано"
//                .spec(requestSpec) // указываем, какую спецификацию используем
//                .body(new RegistrationDto(info.getLogin(), info.getPassword(), "active")) // передаём в теле объект, который будет преобразован в JSON
//                .when() // "когда"
//                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
//                .then() // "тогда ожидаем"
//                .statusCode(200); // код 200 OK
//    }

    @Test
    public void shouldSendCompletedForm() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(info.getLogin(), info.getPassword(), "active")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK

    }


    @Test
    public void shouldSendCompletedFormIfLoginWithLatinLetters() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(infoNew.getLogin(), infoNew.getPassword(), infoNew.getStatus())) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200

    }

    @Test
    public void shouldSendCompletedFormIfLoginWithALeadingSpace() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(" " + info.getLogin(), info.getPassword(), info.getStatus())) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200

    }

    @Test
    public void shouldSendCompletedFormIfPasswordWithALeadingSpace() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(info.getLogin(), " " + info.getPassword(), info.getStatus())) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200

    }

    @Test
    public void shouldSendCompletedFormPassiveUser() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(info.getLogin(), info.getPassword(), "passive")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(500); // код 500

    }

    @Test
    public void shouldSendCompletedFormWithoutStatus() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(info.getLogin(), info.getPassword(), "")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(500); // код 500

    }

    @Test
    public void shouldSendIfPasswordFieldIsEmptyAndStatusPassive() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(info.getLogin(), "", "passive")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(500); // код 500

    }

    @Test
    public void shouldSendIfLoginFieldIsEmptyAndStatusPassive() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto("", info.getPassword(), "passive")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(500); // код 500

    }

    @Test
    public void shouldSendIfLoginFieldIsEmpty() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto("", info.getPassword(), "active")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(500); // код 500

    }


    @Test
    public void shouldSendIfPasswordFieldIsEmptyAndStatusActive() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(info.getLogin(), "", "active")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(500); // код 500

    }

    @Test
    public void shouldSendCompletedFormIfPasswordIsSmall() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(infoPasswordArabicLetters.getLogin(), infoPasswordArabicLetters.getPassword(), infoPasswordArabicLetters.getStatus())) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200

    }


}
