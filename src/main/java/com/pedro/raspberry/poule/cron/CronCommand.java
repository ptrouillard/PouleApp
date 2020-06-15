package com.pedro.raspberry.poule.cron;

public class CronCommand {

    private String openExpression;
    private String closeExpression;
    private boolean schedulerStarted;

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
}
