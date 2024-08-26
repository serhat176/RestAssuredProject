package com.hotelreservation;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllBookingTest {
    // Cagri olusturmaliyiz
    // Response kontrollerini yapmaliyiz
    // curl -i https://restful-booker.herokuapp.com/booking

    @Test
    public void getAllBookingTest(){  //Rest assure ile basit bir get cagrisi olusturduk

        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .log().all()
                .statusCode(200);
    }

}
