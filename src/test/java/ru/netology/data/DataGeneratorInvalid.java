package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.util.Locale;

public class DataGeneratorInvalid {
    @UtilityClass
    public static class RegistrationInvalid {
        public static RegistrationDto generateInfoInvalidLogin(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationDto(faker.phoneNumber().phoneNumber(), faker.lorem().characters(8,16), "active");
        }

        public static RegistrationDto generateInfoInvalidPassword(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationDto(faker.name().firstName(), faker.phoneNumber().phoneNumber(), "active");
        }

        public static RegistrationDto generateInfoSmallPassword(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationDto(faker.name().firstName(), faker.lorem().characters(1,3), "active");
        }

        public static RegistrationDto generateInfoInvalidPasswordIsArabicLetters(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationDto(faker.name().firstName(), faker.name().firstName(), "active");
        }

    }


}
