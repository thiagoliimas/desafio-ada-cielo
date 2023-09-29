package br.com.desafiocielo.desafio1.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static String extractMessageBody(String s) throws JsonProcessingException {

        String jsonString = s.substring(1, s.length() - 1);

        String[] keyValuePairs = jsonString.split(", ");

        ObjectMapper objectMapper = new ObjectMapper();
        com.fasterxml.jackson.databind.node.ObjectNode jsonNode = objectMapper.createObjectNode();

        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0];
                String value = keyValue[1];
                jsonNode.put(key, value);
            }
        }
        return jsonNode.toPrettyString();
    }
}
