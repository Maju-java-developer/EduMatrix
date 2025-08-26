package util;
/*
 *@author Shahzeb.Iqbal
 *@since 8/10/2023
 *@type MvrJsonUtility
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MvrJsonUtility {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String objectToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T jsonToObject(String jsonStr, Class<T> valueType) throws JsonProcessingException {
        return objectMapper.readValue(jsonStr, valueType);
    }

    public static JsonNode stringToJson(String jsonStr) throws JsonProcessingException {
        return objectMapper.readTree(jsonStr);
    }

    public static String jsonToString(JsonNode jsonNode) throws JsonProcessingException {
        return objectMapper.writeValueAsString(jsonNode);
    }

    public static String prettyPrint(JsonNode jsonNode) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
    }

    public static String prettyPrintString(String jsonStr) throws JsonProcessingException {
        JsonNode jsonNode = MvrJsonUtility.stringToJson(jsonStr);
        return MvrJsonUtility.prettyPrint(jsonNode);
    }

    public static boolean validateJson(String jsonStr) {
        try {
            objectMapper.readTree(jsonStr);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    public static String convertToNestedJson(String parentNode, Map<String, Object> nodeObjects) throws JsonProcessingException {
        Map<String, Object> nestedMap = new HashMap<>();
        nestedMap.put(parentNode, nodeObjects);

        return objectMapper.writeValueAsString(nestedMap);
    }

    public static ObjectNode createObjectNode() {
        return objectMapper.createObjectNode();
    }

    public static String getJsonString(Object obj) {
        String json = "{}";
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
//            log.error(e.getMessage());
        }
        return json;
    }

    public static String getJsonString(String root, Object obj) {
        ObjectNode jsonObject = createObjectNode();
        jsonObject.putPOJO(root, obj);
        return getJsonString(jsonObject);
    }

    public static JsonNode findObjectNodeByName(String nodeName, String jsonString) throws IOException {
        JsonNode rootNode = objectMapper.readTree(jsonString);

        if (rootNode != null && rootNode.isObject()) {
            return rootNode.get(nodeName);
        }

        return null;
    }
}

