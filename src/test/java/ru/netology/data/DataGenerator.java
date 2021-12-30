package ru.netology.data;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class DataGenerator {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final Faker faker = new Faker(new Locale("en"));


    //static RegistrationDto info = DataGenerator.Registration.generateInfo("ru");

    private DataGenerator(){

    }

    public static void setRequest(RegistrationDto user) {
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new Gson().toJson(user)); // передаём в теле объект, который будет преобразован в JSON
        when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
        .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }



    public static String getRandomLogin() {
        String login = faker.name().username();

        return login;
    }

    public static String getRandomPassword() {
        String password = faker.internet().password();

        return password;
    }

    public static  class Registration {
        private Registration() {
        }

        public static RegistrationDto getUser(String status) {
            RegistrationDto user = new RegistrationDto(getRandomLogin(),getRandomPassword(), status);

            return user;
        }

        public static RegistrationDto getRegisteredUser(String status) {
            RegistrationDto registeredUser = getUser(status);
            setRequest(registeredUser);
            return registeredUser;
        }

        

    }

    @Value
    @AllArgsConstructor
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }

//    @UtilityClass
//    public static class Registration {
//        public static RegistrationDto generateInfo(String locale) {
//            Faker faker = new Faker(new Locale("ru"));
//            return new RegistrationDto(faker.name().firstName(), faker.lorem().characters(8,16), "active");
//        }
//
//        public static RegistrationDto generateInfoEng(String locale) {
//            Faker faker = new Faker(new Locale("eng"));
//            return new RegistrationDto(faker.name().firstName(), faker.lorem().characters(8,16), "active");
//        }
//
//        public static RegistrationDto generateInfoPasswordIsArabicLetters(String locale) {
//            Faker faker = new Faker(new Locale("ru"));
//            return new RegistrationDto(faker.name().firstName(), faker.name().firstName(), "active");
//        }
//
//
//
//    }
}
