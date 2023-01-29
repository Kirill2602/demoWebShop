package com.tricentis.demowebshop;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.config.RedirectConfig.redirectConfig;

public class Specs {
    public static RequestSpecification request(String contentType) {
        return with()
                .baseUri("https://demowebshop.tricentis.com/")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8");
//                .contentType(ContentType.JSON)
//                .contentType("application/x-www-form-urlencoded; charset=UTF-8");
    }

    public static ResponseSpecification responseUniqueStatus(int status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    public static void installSpecifications(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;

    }

    public static void restAssuredConfig() {
        RestAssured.config().redirect(redirectConfig().followRedirects(true).and().maxRedirects(1));
    }
}
