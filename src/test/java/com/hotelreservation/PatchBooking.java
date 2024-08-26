package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class PatchBooking  extends BaseTest{

    // Token al
    // Kiralama olustur
    // Kiralamada yalnizca para ve depositoyu guncelle
    // Assertion'lari kontrol


    @Test
    public void patchBooking(){

        JSONObject attributes = new JSONObject();
        attributes.put("totalprice", 28000);
        attributes.put("depositpaid", false);

        Response newToken = createToken();
        Response booking = createBooking();
        String token = newToken.jsonPath().get("token");
        int bookingID = (int)booking.jsonPath().getJsonObject("bookingid");
        Response updateBooking = (Response)given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(attributes.toString())
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/" + bookingID);

        updateBooking
                .then()
                .statusCode(200);
        updateBooking.prettyPrint();
        assertEquals("28000", updateBooking.jsonPath().getJsonObject("totalprice").toString());



    }
}
