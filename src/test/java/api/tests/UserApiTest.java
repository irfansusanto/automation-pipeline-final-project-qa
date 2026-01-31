package api.tests;

import api.base.BaseApiTest;
import api.utils.GraphQLRequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class UserApiTest extends BaseApiTest {

    @Test
    public void getProfile() {
        String query =
                "query { me { id name email role } }";
        String body = GraphQLRequestBuilder.build(
                query,
                Map.of()
        );
        Response res = graphQLClient.send(body);
        res.then().log().all().statusCode(200);
        String email =
                res.jsonPath().getString("data.me.email");
        Assert.assertNotNull(email);
    }
}
