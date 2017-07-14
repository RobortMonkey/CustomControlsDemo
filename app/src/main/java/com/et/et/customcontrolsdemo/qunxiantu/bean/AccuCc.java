/**
 * Copyright 2016 aTool.org
 */
package com.et.et.customcontrolsdemo.qunxiantu.bean;

import java.util.Date;

public class AccuCc {

    private String EpochTime;
    private Date LocalObservationDateTime;
    private String Pressure;
    private String RealFeelTemperature;
    private String RelativeHumidity;
    private String UVIndex;
    private String Visibility;
    private String WindDirectionDegrees;
    private String WindSpeed;

    public String getEpochTime() {
        return EpochTime;
    }

    public void setEpochTime(String epochTime) {
        EpochTime = epochTime;
    }

    public Date getLocalObservationDateTime() {
        return LocalObservationDateTime;
    }

    public void setLocalObservationDateTime(Date localObservationDateTime) {
        LocalObservationDateTime = localObservationDateTime;
    }

    public String getPressure() {
        return Pressure;
    }

    public void setPressure(String pressure) {
        Pressure = pressure;
    }

    public String getRealFeelTemperature() {
        return RealFeelTemperature;
    }

    public void setRealFeelTemperature(String realFeelTemperature) {
        RealFeelTemperature = realFeelTemperature;
    }

    public String getRelativeHumidity() {
        return RelativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        RelativeHumidity = relativeHumidity;
    }

    public String getUVIndex() {
        return UVIndex;
    }

    public void setUVIndex(String uVIndex) {
        UVIndex = uVIndex;
    }

    public String getVisibility() {
        return Visibility;
    }

    public void setVisibility(String visibility) {
        Visibility = visibility;
    }

    public String getWindDirectionDegrees() {
        return WindDirectionDegrees;
    }

    public void setWindDirectionDegrees(String windDirectionDegrees) {
        WindDirectionDegrees = windDirectionDegrees;
    }

    public String getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        WindSpeed = windSpeed;
    }


    @Override
    public String toString() {
        return "AccuCc{" +
                "EpochTime='" + EpochTime + '\'' +
                ", LocalObservationDateTime=" + LocalObservationDateTime +
                ", Pressure='" + Pressure + '\'' +
                ", RealFeelTemperature='" + RealFeelTemperature + '\'' +
                ", RelativeHumidity='" + RelativeHumidity + '\'' +
                ", UVIndex='" + UVIndex + '\'' +
                ", Visibility='" + Visibility + '\'' +
                ", WindDirectionDegrees='" + WindDirectionDegrees + '\'' +
                ", WindSpeed='" + WindSpeed + '\'' +
                '}';
    }
}