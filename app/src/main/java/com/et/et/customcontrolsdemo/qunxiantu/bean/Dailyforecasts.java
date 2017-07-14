/**
 * Copyright 2016 aTool.org
 */
package com.et.et.customcontrolsdemo.qunxiantu.bean;

import java.util.Date;

public class Dailyforecasts {

    private Date Date;
    private String EpochDate;
    private Date Sun_Rise;
    private String Sun_Epochrise;
    private Date Sun_Set;
    private String Sun_Epochset;
    private String PrecipitationProbability;

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public String getEpochDate() {
        return EpochDate;
    }

    public void setEpochDate(String epochDate) {
        EpochDate = epochDate;
    }

    public Date getSun_Rise() {
        return Sun_Rise;
    }

    public void setSun_Rise(Date sun_Rise) {
        Sun_Rise = sun_Rise;
    }

    public String getSun_Epochrise() {
        return Sun_Epochrise;
    }

    public void setSun_Epochrise(String sun_Epochrise) {
        Sun_Epochrise = sun_Epochrise;
    }

    public Date getSun_Set() {
        return Sun_Set;
    }

    public void setSun_Set(Date sun_Set) {
        Sun_Set = sun_Set;
    }

    public String getSun_Epochset() {
        return Sun_Epochset;
    }

    public void setSun_Epochset(String sun_Epochset) {
        Sun_Epochset = sun_Epochset;
    }

    public String getPrecipitationProbability() {
        return PrecipitationProbability;
    }

    public void setPrecipitationProbability(String precipitationProbability) {
        PrecipitationProbability = precipitationProbability;
    }

}