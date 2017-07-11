package com.et.et.customcontrolsdemo.luopan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.et.et.customcontrolsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wangxiongfeng
 * @date 2017/7/10 0010 11:58
 */

public class LuoPanFragment extends Fragment {
    @BindView(R.id.luopan)
    LuoPanView luopan;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_luopan, container, false);

        unbinder = ButterKnife.bind(this, view);






        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
