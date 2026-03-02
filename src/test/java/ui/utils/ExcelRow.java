package ui.utils;

import java.util.Map;

public class ExcelRow {
    private final Map<String, String> data;

    public ExcelRow(Map<String, String> data) {
        this.data = data;
    }

    public String getRequired(String key) {
        String v = data.get(key);
        if (v == null || v.trim().isEmpty()) {
            throw new IllegalArgumentException("Missing required column/value: " + key);
        }
        return v.trim();
    }

    public String get(String key) {
        String v = data.get(key);
        return v == null ? "" : v.trim();
    }
}