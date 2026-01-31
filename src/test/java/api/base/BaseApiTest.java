package api.base;

import api.client.GraphQLClient;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    protected GraphQLClient graphQLClient;

    @BeforeClass
    public void setup() {
        graphQLClient = new GraphQLClient();
    }
}
