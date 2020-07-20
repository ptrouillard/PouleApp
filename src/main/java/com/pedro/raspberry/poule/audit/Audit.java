package com.pedro.raspberry.poule.audit;

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

    public Audit(Date date, String comment) {
        this.date = date;
        this.comment = comment;
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

    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
