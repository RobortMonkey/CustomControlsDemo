package com.et.et.customcontrolsdemo;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.et.et.customcontrolsdemo.luopan.LocationService;

/**
 * @author wangxiongfeng
 * @date 2017/7/12 0012 17:37
 */

public class ApplicationApp extends Application {

    public LocationService locationService;
    private Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();


        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
    }


}
