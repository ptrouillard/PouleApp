package com.pedro.raspberry.poule.adapter.supervision;

public class Insight {
    private String cpuTemperature;
    private String cpuVoltage;
    private String freeMemory;

    Insight() {
    }

    public Insight(String cpuTemperature, String cpuVoltage, String freeMemory) {
        this.cpuTemperature = cpuTemperature;
        this.cpuVoltage = cpuVoltage;
        this.freeMemory = freeMemory;
    }

    public static Insight error() {
        return new Insight("NC (error)", "NC (error)", "NC (error)");
    }

    public String getCpuTemperature() {
        return cpuTemperature;
    }

    public void setCpuTemperature(String cpuTemperature) {
        this.cpuTemperature = cpuTemperature;
    }

    public String getCpuVoltage() {
        return cpuVoltage;
    }

    public void setCpuVoltage(String cpuVoltage) {
        this.cpuVoltage = cpuVoltage;
    }

    public String getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(String freeMemory) {
        this.freeMemory = freeMemory;
    }
}
