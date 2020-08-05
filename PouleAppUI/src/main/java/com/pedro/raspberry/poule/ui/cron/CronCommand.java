package com.pedro.raspberry.poule.ui.cron;

public class CronCommand {

    private String openExpression;
    private String closeExpression;
    private boolean schedulerStarted;
    private boolean schedulerShutdown;
    private boolean schedulerPaused;

    public String getOpenExpression() {
        return openExpression;
    }

    public void setOpenExpression(String openExpression) {
        this.openExpression = openExpression;
    }

    public String getCloseExpression() {
        return closeExpression;
    }

    public void setCloseExpression(String closeExpression) {
        this.closeExpression = closeExpression;
    }

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
}
