package com.et.et.customcontrolsdemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.et.et.customcontrolsdemo.luopan.LuoPanFragment;

/**
 * @author wangxiongfeng
 * @date 2017/7/10 0010 11:54
 */

public class ViewLayoutActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        String name = getIntent().getStringExtra("name");
        if (!TextUtils.isEmpty(name)) {
            changeToFragment(name);
        }

    }

    private void changeToFragment(String name) {
        Fragment fragment = null;
        switch (name) {
            case "luopan":
                fragment = new LuoPanFragment();
                break;

        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commitNow();
    }
}
