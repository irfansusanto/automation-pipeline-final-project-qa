package api.tests;

import api.base.BaseApiTest;
import api.utils.GraphQLRequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TrainingApiTest extends BaseApiTest {

    @Test
    public void getProgramListAsTraining() {
        String query =
                "query {" +
                        "  programs {" +
                        "    id " +
                        "    title " +
                        "  }" +
                        "}";
        String body = GraphQLRequestBuilder.build(query, Map.of());
        Response res = graphQLClient.send(body);
        res.then().log().body();
        res.then().statusCode(200);
        Assert.assertNull(res.jsonPath().get("errors"));
        Assert.assertTrue(
                res.jsonPath().getList("data.programs").size() > 0,
                "Program list kosong"
        );
    }
}
