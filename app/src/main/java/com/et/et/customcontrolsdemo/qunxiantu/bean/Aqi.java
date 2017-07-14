/**
 * Copyright 2016 aTool.org
 */
package com.et.et.customcontrolsdemo.qunxiantu.bean;

public class Aqi {

    private String city;
    private String city_id;
    private String pub_time;
    private String aqi;
    private String pm25;
    private String pm10;
    private String so2;
    private String no2;
    private String src;
    private String spot;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getPub_time() {
        return pub_time;
    }

    public void setPub_time(String pub_time) {
        this.pub_time = pub_time;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    @Override
    public String toString() {
        return "Aqi{" +
                "city='" + city + '\'' +
                ", city_id='" + city_id + '\'' +
                ", pub_time='" + pub_time + '\'' +
                ", aqi='" + aqi + '\'' +
                ", pm25='" + pm25 + '\'' +
                ", pm10='" + pm10 + '\'' +
                ", so2='" + so2 + '\'' +
                ", no2='" + no2 + '\'' +
                ", src='" + src + '\'' +
                ", spot='" + spot + '\'' +
                '}';
    }
}