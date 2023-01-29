package com.tricentis.demowebshop.testdata;

import com.github.javafaker.Faker;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TestData {
    Faker faker = new Faker();
    private String cookie = "671724B7808D8A41696F32378695C38B333FD1042EBCC9" +
            "40667A9D2EC4205E6A1FCBB225A00AAD11909C" +
            "0F10C704B4A63919CC0F907D58B29CC5BE98BBF2C1" +
            "0C03E4206DE20310FDC7740F40D3EDE99A1CE6EF1C117D" +
            "EBCF6AD22C37912835E46D7A97049D1C6EBB72796F5F13916B" +
            "7C9B8C65730BE31BD8A3B938002F0DD4228C8A004D3A9309912FC4B553D8A2FAAB;",
            requestVerificationToken = "LpSvPVv6ugGEWsva7Gt9qoaJWvfXh3hVDcICgsINq1wiRwD2Da2TixkRdMCNu" +
                    "_hnCcEgqMdRVxRqMjlvQarzJIs6gNrnxLDUUh5S3TQV9dc1",
            requestVerificationTokenForEditProfileInfo = "k0qlVR6PPbVprIArOJ1Kpjl52nAkT" +
                    "dzltPAEF0hPhQCMWtJRP3kCGFaoQQaXRlW1X9KUoX6uBt0HT32Ftmt7dzkdlUq702_" +
                    "mfqBEgbcNzITnYAXrabgoeHtow8diZvQ_0",
            requestVerificationTokenForRegistration = "UrLEqek8042iwwgiJEKz6qbTy4MXZRBPLYm6z3FRD7BJ_3HeLDddyu9o3xHj1xvEDI5SFX-Dd2BhWHYKTT3Uy6GjRz-xYE9qayWiI7_yd6I1",
            recipientName = faker.name().firstName(),
            senderName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            message = faker.lorem().characters(20),
            recipientEmail = faker.internet().emailAddress(),
            senderEmail = faker.internet().emailAddress(),
            password = faker.internet().password();

    int enteredQuantity = faker.number().numberBetween(1, 5);

    Map<String, Object> formGiftCardParamsMap = Map.of(
            "giftcard_1.RecipientName", getRecipientName(),
            "giftcard_1.RecipientEmail", getRecipientEmail(),
            "giftcard_1.SenderName", getSenderName(),
            "giftcard_1.SenderEmail", getSenderEmail(),
            "giftcard_1.Message", getMessage(),
            "addtocart_1.EnteredQuantity", getEnteredQuantity()
    ),
            formParamsEditInfo = Map.of("__RequestVerificationToken", getRequestVerificationTokenForEditProfileInfo(),
                    "Gender", getGender(),
                    "FirstName", getSenderName(),
                    "LastName", getLastName(),
                    "Email", "TestHW@mail.ru"),

    formParamsSuccessRegistration = Map.of(
            "__RequestVerificationToken", getRequestVerificationTokenForRegistration(),
            "Gender", getGender(),
            "FirstName", getSenderName(),
            "LastName", getLastName(),
            "Email", getSenderEmail(),
            "Password", getPassword(),
            "ConfirmPassword", getPassword()
    );


    public String getGender() {
        List<String> gender = List.of("M", "F");
        return gender.get(faker.number().numberBetween(1, 2));
    }

}
