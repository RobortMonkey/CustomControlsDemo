package com.et.et.customcontrolsdemo.utils;

import com.et.et.customcontrolsdemo.qunxiantu.bean.LocationInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author wangxiongfeng
 * @date 2017/7/14 0014 16:35
 */

public class NetClinet {
    private static final NetClinet netClinet = new NetClinet();
    private static int DEFAULT_TIMEOUT = 30;
    private static String DEFAULT_URL = "http://weatherapi.market.xiaomi.com/wtr-v2/";
    private static Retrofit retrofit;

    public static NetClinet getNetClientInstance() {

        if (retrofit == null) {
            retrofit = initClinet();
        }
        return netClinet;

    }

    private static Retrofit initClinet() {


        OkHttpClient.Builder client = getOKhttpClient();

        Retrofit build = new Retrofit.Builder()
                .client(client.build())
                .baseUrl(DEFAULT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return build;
    }

    private static OkHttpClient.Builder getOKhttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return client;
    }




    interface WeathServer {
        @GET("weather")
        Observable<LocationInfo> getWeathInfo(@Query("cityId") String cityID);
    }
    public static void getWeathinfo(Subscriber<LocationInfo> subscriber, String cityID) {

        retrofit.create(WeathServer.class)
                .getWeathInfo(cityID)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }




}
