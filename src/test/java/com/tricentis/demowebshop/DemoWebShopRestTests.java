package com.tricentis.demowebshop;

import com.tricentis.demowebshop.testdata.TestData;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.tricentis.demowebshop.Specs.installSpecifications;
import static com.tricentis.demowebshop.endpoints.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoWebShopRestTests {
    TestData data = new TestData();
    String id;

    @Test
    void successLoginTest() {
        installSpecifications(Specs.request("application/x-www-form-urlencoded; charset=UTF-8"), Specs.responseUniqueStatus(302));
        data.setCookie(given()
                .formParam("Email", "TestHW@mail.ru")
                .formParam("Password", "qwe1234")
                .when()
                .post(LOGIN_ENDPOINT)
                .then().extract().cookie("NOPCOMMERCE.AUTH"));

        assertFalse(data.getCookie().isEmpty());
    }

    @Test
    public void successRegistration() {
        installSpecifications(Specs.request("application/x-www-form-urlencoded"), Specs.responseUniqueStatus(302));
        String responseBody = given()
                .cookie("__RequestVerificationToken", data.getRequestVerificationToken())
                .formParams(data.getFormParamsSuccessRegistration())
                .when()
                .post("/register")
                .then()
                .extract().body()
                .asPrettyString();

        assertTrue(responseBody.contains("<a shape=\"rect\" href=\"/registerresult/1\">here</a>"));
    }

    @Test
    void addGiftCardInCartTest() {
        installSpecifications(Specs.request("application/x-www-form-urlencoded; charset=UTF-8"), Specs.responseUniqueStatus(200));
        given()
                .cookie("NOPCOMMERCE.AUTH", data.getCookie())
                .formParams(data.getFormGiftCardParamsMap())
                .when()
                .post(ADD_PRODUCT_ENDPOINT)
                .then()
                .body("success", is(true));
    }


    @Test
    void updateGiftCardDataTest() {
        String searchableItemId = "removefromcart";
        installSpecifications(Specs.request("application/x-www-form-urlencoded; charset=UTF-8"), Specs.responseUniqueStatus(200));
        id = given()
                .cookie("NOPCOMMERCE.AUTH", data.getCookie())
                .when()
                .get(CART_ENDPOINT)
                .then().extract().asPrettyString();

        id = id.substring(id.indexOf(searchableItemId),
                id.indexOf(">", id.indexOf(searchableItemId))).replaceAll("\\D+", "");

        given()
                .cookie("NOPCOMMERCE.AUTH", data.getCookie())
                .formParams(data.getFormGiftCardParamsMap())
                .formParam("addtocart_1.UpdatedShoppingCartItemId", id)
                .when()
                .post(ADD_PRODUCT_ENDPOINT)
                .then()
                .body("success", is(true))
                .body("updatetopcartsectionhtml", not(isEmptyString()));
    }

    @Test
    public void editProfile() {
        installSpecifications(Specs.request("application/x-www-form-urlencoded"), Specs.responseUniqueStatus(302));
        String body = given()
                .cookie("NOPCOMMERCE.AUTH", data.getCookie())
                .cookie("__RequestVerificationToken", data.getRequestVerificationToken())
                .formParams(data.getFormParamsEditInfo())
                .when()
                .post(CUSTOMER_INFO_ENDPOINT)
                .then()
                .extract().body().asPrettyString();
        assertTrue(body.contains("<a shape=\"rect\" href=\"/customer/info\">here</a>"));
    }
}
