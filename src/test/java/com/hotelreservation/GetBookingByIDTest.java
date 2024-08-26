package com.hotelreservation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class GetBookingByIDTest extends BaseTest{
    //Cagriyi olusturmaliyiz
    //Response kontrolleri

    @Test
    public void getBooking(){

        Response newBooking = createBooking();
        int reservationId = newBooking.jsonPath().getJsonObject("bookingid");


        //https://restful-booker.herokuapp.com/booking/id
        Response response = (Response) given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/" + reservationId);

        response
                .then()
                .statusCode(200);

        response.prettyPrint(); //Farkli bir loglama bicimi

        String firstName = response.jsonPath().getJsonObject("firstname");
        String lastName = response.jsonPath().getJsonObject("lastname");
        int totalPrice = response.jsonPath().getJsonObject("totalprice");

        assertEquals("Serhat",firstName);
        assertEquals("Guneri", lastName);
        assertEquals(3500,totalPrice);
    }
}
