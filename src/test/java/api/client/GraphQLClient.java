package api.client;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GraphQLClient {

    private final RequestSpecification request;

    public GraphQLClient() {

        String apiUrl = ConfigReader.getValue("API_BASE_URL");
        String username = ConfigReader.getValue("API_USERNAME");
        String password = ConfigReader.getValue("API_PASSWORD");
        String companyId = ConfigReader.getValue("API_COMPANY_ID");

        if (apiUrl == null || username == null ||
                password == null || companyId == null) {
            throw new RuntimeException("Missing API_* config (check GitHub Secrets)");
        }

        Cookies cookies = login(apiUrl, username, password, companyId);

        Cookie sessionCookie = cookies.get("sid_b2b");

        if (sessionCookie == null) {
            throw new RuntimeException("Login failed: sid_b2b not found");
        }

        request = RestAssured.given()
                .baseUri(apiUrl)
                .cookie("sid_b2b", sessionCookie.getValue())
                .contentType("application/json");
    }


    private Cookies login(String apiUrl,
                          String username,
                          String password,
                          String companyId) {
        String loginBody = """
        {
          "query":"mutation Login($usernameOrEmail:String!,$password:String!,$companyId:String!){login(usernameOrEmail:$usernameOrEmail,password:$password,companyId:$companyId){user{id} errors{field message}}}",
          "variables":{
            "usernameOrEmail":"%s",
            "password":"%s",
            "companyId":"%s"
          }
        }
        """.formatted(username, password, companyId);
        Response res = RestAssured.given()
                .baseUri(apiUrl)
                .contentType("application/json")
                .body(loginBody)
                .post();
        res.then().statusCode(200);
        return res.getDetailedCookies();
    }

    public Response send(String body) {
        return request
                .body(body)
                .post();
    }
}
