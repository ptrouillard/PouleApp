package com.pedro.raspberry.poule.api;

public class DoorActionResult {
    private boolean result;
    private String message;

    public static DoorActionResult success() {
        return new DoorActionResult(true);
    }

    public static DoorActionResult error(String message) {
        return new DoorActionResult(false, message);
    }

    private DoorActionResult(boolean result) {
        this.result = result;
    }

    private DoorActionResult(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
