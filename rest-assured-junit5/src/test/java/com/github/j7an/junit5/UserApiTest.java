package com.github.j7an.junit5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static com.github.j7an.common.ApiConstants.*;

public class UserApiTest {

    @BeforeAll
    static void setup() {
        // Set the base URI for all tests in this class
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    void shouldGetUsersForPageTwo() {
        given()
                .queryParam(QUERY_PARAM_PAGE, 2)
                .accept(ContentType.JSON) // Expect JSON response
                .when()
                .get(ENDPOINT_USERS)
                .then()
                .statusCode(200) // Assert status code is 200 OK
                .contentType(ContentType.JSON) // Assert response content type is JSON
                .body("page", equalTo(2)) // Assert 'page' field in the response body is 2
                .body("data", not(empty())); // Assert the 'data' array is not empty
    }
}
