package com.github.j7an.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static com.github.j7an.common.ApiConstants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserApiSteps {

    private RequestSpecification request;
    private Response response;

    @Given("the base URI is set")
    public void the_base_uri_is_set() {
        RestAssured.baseURI = BASE_URI;
        request = given().accept(ContentType.JSON); // Initialize request with Accept header
    }

    @When("a GET request is made to the users endpoint for page {int}")
    public void a_get_request_is_made_to_the_users_endpoint_for_page(Integer page) {
        response = request.queryParam(QUERY_PARAM_PAGE, page)
                .when()
                .get(ENDPOINT_USERS);
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response content type should be JSON")
    public void the_response_content_type_should_be_json() {
        response.then().contentType(ContentType.JSON);
    }

    @Then("the response body should contain page number {int}")
    public void the_response_body_should_contain_page_number(Integer page) {
        response.then().body("page", equalTo(page));
    }

    @Then("the response body should contain user data")
    public void the_response_body_should_contain_user_data() {
        response.then().body("data", not(empty()));
    }
}
