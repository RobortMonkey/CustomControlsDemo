/**
 */
package com.et.et.customcontrolsdemo.qunxiantu.bean;

import java.util.List;

public class LocationInfo {
    private Forecast forecast;
    private Realtime realtime;
    private Aqi aqi;
    private List<Index> index;
    private List<Alert> alert;
    private AccuCc accu_cc;
    private AccuF5 accu_f5;
    private Today today;
    private Yestoday yestoday;

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public Realtime getRealtime() {
        return realtime;
    }

    public void setRealtime(Realtime realtime) {
        this.realtime = realtime;
    }

    //	public List<String> getAlert() {
//		return alert;
//	}
//	public void setAlert(List<String> alert) {
//		this.alert = alert;
//	}
    public Aqi getAqi() {
        return aqi;
    }

    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    public List<Index> getIndex() {
        return index;
    }

    public void setIndex(List<Index> index) {
        this.index = index;
    }

    public AccuCc getAccu_cc() {
        return accu_cc;
    }

    public void setAccu_cc(AccuCc accu_cc) {
        this.accu_cc = accu_cc;
    }

    public AccuF5 getAccu_f5() {
        return accu_f5;
    }

    public void setAccu_f5(AccuF5 accu_f5) {
        this.accu_f5 = accu_f5;
    }

    public Today getToday() {
        return today;
    }

    public void setToday(Today today) {
        this.today = today;
    }

    public Yestoday getYestoday() {
        return yestoday;
    }

    public void setYestoday(Yestoday yestoday) {
        this.yestoday = yestoday;
    }


    @Override
    public String toString() {
        return "LocationInfo{" +
                "forecast=" + forecast.toString() +
                ", realtime=" + realtime.toString() +
                ", aqi=" + aqi.toString() +
                ", index=" + index.toString() +
                ", alert=" + alert.toString() +
                ", accu_cc=" + accu_cc.toString() +
                ", accu_f5=" + accu_f5.toString() +
                ", today=" + today.toString() +
                ", yestoday=" + yestoday.toString() +
                '}';
    }
}