package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteBooking extends BaseTest {
//    curl -X DELETE \
//    https://restful-booker.herokuapp.com/booking/1 \
//            -H 'Content-Type: application/json' \
//            -H 'Cookie: token=abc123'

    @Test
    public void deleteBooking(){
        //Token al
        Response tokenObject = createToken();
        String token = tokenObject.jsonPath().getJsonObject("token");
        //Kiralama olustur
        Response reservation = createBooking();
        int bookingID = (int)reservation.jsonPath().getJsonObject("bookingid");
        // Kiralamayi sil
        Response deleteReservation = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .when()
                .delete(" https://restful-booker.herokuapp.com/booking/" + bookingID);

        deleteReservation
                .then()
                .statusCode(201);

        deleteReservation.prettyPrint();


    }

}
