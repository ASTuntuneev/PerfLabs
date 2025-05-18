package org.perflab;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.out.println("Usage: java TestReporter <values.json> <tests.json> <report.json>");
            return;
        }

        File valuesFile = new File(args[0]);
        File testsFile = new File(args[1]);
        File outputFile = new File(args[2]);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode valuesRoot = mapper.readTree(valuesFile);
        Map<Integer, String> valuesMap = new HashMap<>();
        for (JsonNode item : valuesRoot.get("values")) {
            int id = item.get("id").asInt();
            String value = item.get("value").asText();
            valuesMap.put(id, value);
        }

        JsonNode testsRoot = mapper.readTree(testsFile);

        if (testsRoot.has("tests")) {
            for (JsonNode testNode : testsRoot.get("tests")) {
                applyValuesRecursive(testNode, valuesMap);
            }
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, testsRoot);
        System.out.println("Отчёт успешно создан: " + outputFile.getAbsolutePath());
    }

    private static void applyValuesRecursive(JsonNode node, Map<Integer, String> valuesMap) {
        if (node instanceof ObjectNode objectNode) {
            if (objectNode.has("id")) {
                int id = objectNode.get("id").asInt();
                if (valuesMap.containsKey(id)) {
                    objectNode.put("value", valuesMap.get(id));
                }
            }

            if (objectNode.has("values") && objectNode.get("values").isArray()) {
                for (JsonNode child : objectNode.get("values")) {
                    applyValuesRecursive(child, valuesMap);
                }
            }

            if (objectNode.has("tests") && objectNode.get("tests").isArray()) {
                for (JsonNode child : objectNode.get("tests")) {
                    applyValuesRecursive(child, valuesMap);
                }
            }
        }
    }
}