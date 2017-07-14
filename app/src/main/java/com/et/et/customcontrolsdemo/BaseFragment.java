package com.et.et.customcontrolsdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wangxiongfeng
 * @date 2017/6/7 0007 14:44
 */

public abstract class BaseFragment extends Fragment {
    protected Unbinder unbinder;
    protected Context context;
    protected Activity activity;
    private int tel;
    private String sms;

    /**
     * 权限请求code
     */
    public static final int REQUEST_CALL = 2001;
    public static final int REQUEST_SEND = 2002;
    public static final int REQUEST_INTENT = 2003;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        this.context = getContext();
        this.activity = getActivity();
        View view = LayoutInflater.from(context).inflate(getViewResource(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public abstract int getViewResource();

    public abstract void initView();

    private void sendSmS(int mobile, String sms_body) {
        tel = mobile;
        sms = sms_body;
        if (!(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)) {
            tel = mobile;
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, REQUEST_SEND);
        } else {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + mobile));
            intent.putExtra("sms_body", sms_body);
            startActivity(intent);
        }
    }

    private void callPhone(int mobile) {
        if (!(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)) {
            tel = mobile;
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));

            startActivity(intent);
        }
    }

    protected void requestIntent() {
        if (!(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.INTERNET}, REQUEST_CALL);
        } else {
            afterGetPermission(REQUEST_INTENT);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (permissions[0].equals(Manifest.permission.CALL_PHONE) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //操作
                afterGetPermission(REQUEST_CALL);
            } else {
                //用户不同意，向用户展示该权限作用
                if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
//                    showdialog("您需要去手机设置中选择拨打电话权限才能正常使用");
                    return;
                }
            }
        } else if (requestCode == REQUEST_SEND) {
            if (permissions[0].equals(Manifest.permission.SEND_SMS) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted

                afterGetPermission(REQUEST_SEND);
            } else {
                //用户不同意，向用户展示该权限作用
                if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.SEND_SMS)) {
//                    showdialog("您需要去手机设置中选择允许发送短信权限才能正常使用");
                    return;
                }
            }
        } else if (requestCode == REQUEST_INTENT) {
            afterGetPermission(REQUEST_INTENT);
        }
    }

    public void afterGetPermission(int permissionCode) {
        switch (permissionCode) {
            case REQUEST_CALL:
                Intent intent_call = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel));
                startActivity(intent_call);
                break;
            case REQUEST_SEND:
                Intent intent_send = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + tel));
                startActivity(intent_send);
                break;
            case REQUEST_INTENT:


                break;
        }
    }

}
