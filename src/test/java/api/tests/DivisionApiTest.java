package api.tests;

import api.base.BaseApiTest;
import api.utils.GraphQLRequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class DivisionApiTest extends BaseApiTest {

    @Test
    public void getDivisionList() {
        String query =
                "query{ " +
                        "divisions{ " +
                        "id name code description " +
                        "} " +
                        "}";
        String body = GraphQLRequestBuilder.build(
                query,
                Map.of()
        );
        Response res = graphQLClient.send(body);
        res.then().log().all().statusCode(200);
        Assert.assertNull(res.jsonPath().get("errors"));
        int size =
                res.jsonPath().getList("data.divisions").size();
        Assert.assertTrue(size > 0);
    }
}
