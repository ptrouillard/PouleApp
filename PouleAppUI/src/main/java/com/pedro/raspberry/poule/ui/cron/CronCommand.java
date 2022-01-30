package com.pedro.raspberry.poule.ui.cron;

import java.util.Date;

public class CronCommand {

    private String openHour;
    private String openMinutes;
    private String closeHour;
    private String closeMinutes;

    private boolean schedulerStarted;
    private boolean schedulerShutdown;
    private boolean schedulerPaused;
    private String nextClosing;
    private String nextOpening;

    public boolean isSchedulerStarted() {
        return schedulerStarted;
    }

    public void setSchedulerStarted(boolean schedulerStarted) {
        this.schedulerStarted = schedulerStarted;
    }

    public void setSchedulerShutdown(boolean shutdown) {
        this.schedulerShutdown = shutdown;
    }

    public void setSchedulerPaused(boolean paused) {
        this.schedulerPaused = paused;
    }

    public boolean isSchedulerShutdown() {
        return schedulerShutdown;
    }

    public boolean isSchedulerPaused() {
        return schedulerPaused;
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

    public void setNextClosing(String nextClosing) {
        this.nextClosing = nextClosing;
    }

    public String getNextClosing() {
        return nextClosing;
    }

    public void setNextOpening(String nextOpening) {
        this.nextOpening = nextOpening;
    }

    public String getNextOpening() {
        return nextOpening;
    }
}
