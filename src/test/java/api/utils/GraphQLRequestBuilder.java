package api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class GraphQLRequestBuilder {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String build(String query, Map<String, Object> variables) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("query", query);
            body.put("variables", variables);
            return mapper.writeValueAsString(body);
        } catch (Exception e) {
            throw new RuntimeException("Failed to build GraphQL body", e);
        }
    }
}
