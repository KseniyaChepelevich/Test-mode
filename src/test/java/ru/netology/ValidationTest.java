package ru.netology;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGeneratorInvalid;

import static io.restassured.RestAssured.given;

public class ValidationTest {
    static RegistrationDto info = DataGeneratorInvalid.RegistrationInvalid.generateInfoInvalidLogin("ru");
    static RegistrationDto infoNew = DataGeneratorInvalid.RegistrationInvalid.generateInfoInvalidPassword("ru");

    static RegistrationDto infoSmallPassword = DataGeneratorInvalid.RegistrationInvalid.generateInfoSmallPassword("ru");


    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    public void shouldSendCompletedFormIfLoginWithNumbers() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(info.getLogin(), info.getPassword(), info.getStatus())) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200

    }

    @Test
    public void shouldSendCompletedFormIfPasswordPhoneNumbers() {

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
    public void shouldSendCompletedFormIfLoginWithNumbersIfStatusPassive() {

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
    public void shouldSendCompletedFormIfPasswordPhoneNumbersIfStatusPassive() {

        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto(infoNew.getLogin(), infoNew.getPassword(), "passive")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(500); // код 500

    }







}
