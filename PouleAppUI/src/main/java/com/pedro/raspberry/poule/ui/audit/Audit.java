package com.pedro.raspberry.poule.ui.audit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by pierre on 05/07/2020.
 */
@Entity
public class Audit {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String comment;

    private Date date;

    private Long time;

    private String remoteAddr;

    private String oldValue;

    private String newValue;

    public Audit(Date date, String comment) {
        this.date = date;
        this.comment = comment;
    }

    public Audit(Date date, String comment, long time) {
        this.date = date;
        this.comment = comment;
        this.time = time;
    }

    public Audit(Date date, String comment, long time, String remoteAddr) {
        this.date = date;
        this.comment = comment;
        this.time = time;
        this.remoteAddr = remoteAddr;
    }

    public Audit(Date date, String comment, long time, String remoteAddr, String oldValue, String newValue) {
        this.date = date;
        this.comment = comment;
        this.time = time;
        this.remoteAddr = remoteAddr;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    protected Audit() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", remoteAddr='" + remoteAddr + '\'' +
                '}';
    }
}
