package utils;

import dto.UserLombok;
import net.datafaker.Faker;

public class UserFactory {

    static Faker faker = new Faker();

//    public static void main(String[] args) {
//        String firsName = faker.name().firstName();
//        System.out.println(firsName);
//        String lastName = faker.name().lastName();
//        System.out.println(lastName);
//        String email = faker.internet().emailAddress();
//        System.out.println(email);
//    }

    public static UserLombok positiveUser() {
        UserLombok user = UserLombok.builder()
                .username(faker.internet().emailAddress())
                .password(PropertiesReader.getProperty
                        ("base.properties", "password_for_registration"))
                .build();
        return user;
    }
}
