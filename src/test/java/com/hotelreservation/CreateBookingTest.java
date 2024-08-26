package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class CreateBookingTest extends BaseTest {


    @Test
    public void createNewBooking(){

        Response response = createBooking();

        assertEquals("Serhat", response.jsonPath().getJsonObject("booking.firstname"));
        assertEquals("Guneri", response.jsonPath().getJsonObject("booking.lastname"));
        assertEquals(35000, (int)response.jsonPath().getJsonObject("booking.totalprice"));
        assertEquals(true, (boolean)response.jsonPath().getJsonObject("booking.depositpaid"));
    }

}
