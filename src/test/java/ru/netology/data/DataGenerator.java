package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.util.Locale;

public class DataGenerator {

    @UtilityClass
    public static class Registration {
        public static RegistrationDto generateInfo(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new RegistrationDto(faker.name().firstName(), faker.lorem().characters(8,16), "active");
        }

    }
}
