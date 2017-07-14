/**
 * Copyright 2016 aTool.org
 */
package com.et.et.customcontrolsdemo.qunxiantu.bean;

import java.util.Date;
import java.util.List;

public class AccuF5 {

    private String EffectiveEpochDate;
    private Date EffectiveDate;
    private List<Dailyforecasts> DailyForecasts;

    public String getEffectiveEpochDate() {
        return EffectiveEpochDate;
    }

    public void setEffectiveEpochDate(String effectiveEpochDate) {
        EffectiveEpochDate = effectiveEpochDate;
    }

    public Date getEffectiveDate() {
        return EffectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        EffectiveDate = effectiveDate;
    }

    public List<Dailyforecasts> getDailyForecasts() {
        return DailyForecasts;
    }

    public void setDailyForecasts(List<Dailyforecasts> dailyForecasts) {
        DailyForecasts = dailyForecasts;
    }

    @Override
    public String toString() {
        return "AccuF5{" +
                "EffectiveEpochDate='" + EffectiveEpochDate + '\'' +
                ", EffectiveDate=" + EffectiveDate +
                ", DailyForecasts=" + DailyForecasts +
                '}';
    }
}