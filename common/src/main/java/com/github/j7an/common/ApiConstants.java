package com.github.j7an.common;

public class ApiConstants {

    // Prevent instantiation
    private ApiConstants() {}

    // Example Base URI - replace with your actual API bae URI
    public static final String BASE_URI = "https://reqres.in";

    // Example Endpoints
    public static final String ENDPOINT_USERS = "/api/users";
    public static final String ENDPOINT_USER_BY_ID = "/api/users/{userID}";
    public static final String ENDPOINT_REGISTER = "/api/register";
    public static final String ENDPOINT_LOGIN = "/api/login";

    // Example Query Parameters
    public static final String QUERY_PARAM_PAGE = "page";

    // Example Header Names
    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    // Example Header Values
    public static final String HEADER_TYPE_JSON = "application/json";
}
