package com.pedro.raspberry.poule.ui.door;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Door {

    @Id
    private Long id;

    private Long closeTime;
    private Long openTime;
    private Long closeStepTime;
    private Long openStepTime;

    Door() {
    }

    public Door(long id, long closeTime, long openTime, long closeStepTime, long openStepTime) {
        this.id = id;
        this.closeTime = closeTime;
        this.openTime = openTime;
        this.closeStepTime = closeStepTime;
        this.openStepTime = openStepTime;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
