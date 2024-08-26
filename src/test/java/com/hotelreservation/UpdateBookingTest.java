package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class UpdateBookingTest extends BaseTest {

    @Test
    public void updateBookingTest(){
//        curl -X PUT \
//        https://restful-booker.herokuapp.com/booking/1 \
//        -H 'Content-Type: application/json' \
//        -H 'Accept: application/json' \
//        -H 'Cookie: token=abc123' \                           ilk olarak token olusturmamiz gerekiyor
//        -d '{
//        "firstname" : "James",
//                "lastname" : "Brown",
//                "totalprice" : 111,
//                "depositpaid" : true,
//                "bookingdates" : {
//            "checkin" : "2018-01-01",
//                    "checkout" : "2019-01-01"
//        },
//        "additionalneeds" : "Breakfast"
//    }'

        // Token olustur
        // Rezervasyon olustur
        // Request yap
        // Response kontrol et


        Response newToken = createToken();
        String token = newToken.jsonPath().getJsonObject("token");
        Response newBooking = createBooking();
        int bookingID = (int)newBooking.jsonPath().get("bookingid");
        System.out.println(token + "  " + bookingID);
        Response response = (Response)given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(bookingObject("Ferhat", "Taş"))
                .when()
                .put("https://restful-booker.herokuapp.com/booking/" + bookingID);

        response
                .then()
                .statusCode(200);

        response.prettyPrint();
        assertEquals("Ferhat", response.jsonPath().getJsonObject("firstname"));
        assertEquals("Taş", response.jsonPath().getJsonObject("lastname"));
        assertEquals("true", response.jsonPath().getJsonObject("depositpaid").toString());
    }

}
