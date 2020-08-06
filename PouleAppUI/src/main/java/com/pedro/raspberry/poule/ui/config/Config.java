package com.pedro.raspberry.poule.ui.config;

public class Config {
    private String webcamUrl;
    private String apiSupervisionUrl;
    private String apiDoorUrl;

    public String getWebcamUrl() {
        return webcamUrl;
    }

    public void setWebcamUrl(String webcamUrl) {
        this.webcamUrl = webcamUrl;
    }

    public String getApiSupervisionUrl() {
        return apiSupervisionUrl;
    }

    public void setApiSupervisionUrl(String apiSupervisionUrl) {
        this.apiSupervisionUrl = apiSupervisionUrl;
    }

    public String getApiDoorUrl() {
        return apiDoorUrl;
    }

    public void setApiDoorUrl(String apiDoorUrl) {
        this.apiDoorUrl = apiDoorUrl;
    }
}
