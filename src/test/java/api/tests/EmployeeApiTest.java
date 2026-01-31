package api.tests;

import api.base.BaseApiTest;
import api.utils.GraphQLRequestBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class EmployeeApiTest extends BaseApiTest {

    @Test
    public void getEmployeeList() {
        String query =
                "query{ " +
                        "employees{ " +
                        "id name email role " +
                        "} " +
                        "}";
        String body = GraphQLRequestBuilder.build(query, Map.of());
        Response res = graphQLClient.send(body);
        res.then().log().all().statusCode(200);
        Assert.assertNull(res.jsonPath().get("errors"));
        int size =
                res.jsonPath()
                        .getList("data.employees")
                        .size();
        Assert.assertTrue(size > 0);
    }
}
