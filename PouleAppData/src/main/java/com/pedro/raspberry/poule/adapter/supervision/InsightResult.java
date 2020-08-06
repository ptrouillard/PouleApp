package com.pedro.raspberry.poule.adapter.supervision;

import java.util.HashMap;
import java.util.Map;

public class InsightResult {
    private boolean result;
    private String message;
    private Insight insight;

    public static InsightResult success(Insight insight) {
        return new InsightResult(true, insight);
    }

    public static InsightResult error(String message) {
        return new InsightResult(false, message);
    }

    protected InsightResult() {
    }

    private InsightResult(boolean result, Insight insight) {
        this.result = result;
        this.insight = insight;
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

    public Insight getInsight() {
        return insight;
    }

    public void setInsight(Insight insight) {
        this.insight = insight;
    }
}
