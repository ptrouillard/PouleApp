package com.pedro.raspberry.poule.ui.door;

public enum DoorConstants {
    Open(12000), Close(11500), Step(1000);

    private long time;

    DoorConstants(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
