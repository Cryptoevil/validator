package io.exigence.businesscomponents.slack.validator.flow;

import java.util.HashMap;
import java.util.Map;

public class Properties {

    private static Map<String, String> properties = new HashMap<>();

    public static void putString(String key, String value) {
        properties.put(key, value);
    }

    public static String getString(String key) {
        return properties.get(key);
    }
}
