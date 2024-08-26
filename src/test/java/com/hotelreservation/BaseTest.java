package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class BaseTest {


    protected String bookingObject(String firstName, String lastName){
        //Body olusturmamiz gerek
        // Cagriyi olusturmaliyiz
        // Response 'i kontrol etmeliyiz
        //        -H 'Content-Type: application/json' \
        //        {
        //            "firstname" : "Jim",
        //                "lastname" : "Brown",
        //                "totalprice" : 111,
        //                "depositpaid" : true,
        //                "bookingdates" : {
        //            "checkin" : "2018-01-01",
        //                    "checkout" : "2019-01-01"
        //        },
        //            "additionalneeds" : "Breakfast"
        //        }

        JSONObject body = new JSONObject();
        body.put("firstname", firstName);
        body.put("lastname", lastName);
        body.put("totalprice", 35000);
        body.put("depositpaid", true);

        //booking dates kendi basina bir json objesi bu nedenle yeni bir json objesi tanimlamamiz gerek
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2023-07-29");
        bookingDates.put("checkout", "2023-08-02");
        body.put("bookingdates", bookingDates);

        body.put("additionalneeds", "Hair Dryer, Breakfast");

        return body.toString();
    }

    protected String tokenObject(){
        //        curl -X POST \
        //        https://restful-booker.herokuapp.com/auth \
        //        -H 'Content-Type: application/json' \                            token olusturma
        //        -d '{
        //        "username" : "admin",
        //                "password" : "password123"
        //    }'
        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "password123");

        return body.toString();
    }

    protected Response createBooking(){

        Response response = (Response)given()
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Serhat", "Güneri"))
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint();
        response
                .then()
                .statusCode(200);

        return response;
    }

    protected Response createToken(){
        Response response = (Response)given()
                .when()
                .contentType(ContentType.JSON)
                .body(tokenObject())
                .post("https://restful-booker.herokuapp.com/auth");

        response.prettyPrint();
        response
                .then()
                .statusCode(200);

        return response;
    }
}
