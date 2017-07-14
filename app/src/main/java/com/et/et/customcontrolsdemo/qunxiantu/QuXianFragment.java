package com.et.et.customcontrolsdemo.qunxiantu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.et.et.customcontrolsdemo.R;

/**
 * @author wangxiongfeng
 * @date 2017/7/12 0012 17:12
 */

public class QuXianFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.activity_quxian, container, false);
        return view;
    }
}
