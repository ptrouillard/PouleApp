package com.pedro.raspberry.poule.api.supervision;

import java.util.HashMap;
import java.util.Map;

public class InsightResult {
    private boolean result;
    private String message;
    private Map<String, String> insights = new HashMap<>();

    public static InsightResult success() {
        return new InsightResult(true);
    }

    public static InsightResult error(String message) {
        return new InsightResult(false, message);
    }

    private InsightResult(boolean result) {
        this.result = result;
    }

    private InsightResult(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public InsightResult withInsight(String key, String value) {
        insights.put(key, value);
        return this;
    }
}
