package com.pedro.raspberry.poule.ui.config;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Config {

    @NotEmpty(message = "{validator.cannot.be.empty}")
    private String webcamUrl;

    @NotEmpty(message = "{validator.cannot.be.empty}")
    private String apiSupervisionUrl;

    @NotEmpty(message = "{validator.cannot.be.empty}")
    private String apiDoorUrl;

    @NotNull(message = "{validator.cannot.be.null}")
    @Min(value = 10, message = "{validator.close.time}")
    private Long closeTime;

    @NotNull(message = "{validator.cannot.be.null}")
    @Min(value = 10, message = "{validator.open.time}")
    private Long openTime;

    @NotNull(message = "{validator.cannot.be.null}")
    @Min(value = 10, message = "{validator.close.step.time}")
    private Long closeStepTime;

    @NotNull(message = "{validator.cannot.be.null}")
    @Min(value = 10, message = "{validator.open.step.time}")
    private Long openStepTime;
    @Min(value = 0, message = "{validator.hour}")
    @Max(value = 23, message = "{validator.hour}")
    private String openHour;
    @Min(value = 0, message = "{validator.minutes}")
    @Max(value = 59, message = "{validator.minutes}")
    private String openMinutes;
    @Min(value = 0, message = "{validator.hour}")
    @Max(value = 23, message = "{validator.hour}")
    private String closeHour;
    @Min(value = 0, message = "{validator.minutes}")
    @Max(value = 59, message = "{validator.minutes}")
    private String closeMinutes;

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

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    public Long getCloseStepTime() {
        return closeStepTime;
    }

    public void setCloseStepTime(Long closeStepTime) {
        this.closeStepTime = closeStepTime;
    }

    public Long getOpenStepTime() {
        return openStepTime;
    }

    public void setOpenStepTime(Long openStepTime) {
        this.openStepTime = openStepTime;
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getOpenMinutes() {
        return openMinutes;
    }

    public void setOpenMinutes(String openMinutes) {
        this.openMinutes = openMinutes;
    }

    public String getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(String closeHour) {
        this.closeHour = closeHour;
    }

    public String getCloseMinutes() {
        return closeMinutes;
    }

    public void setCloseMinutes(String closeMinutes) {
        this.closeMinutes = closeMinutes;
    }
}
