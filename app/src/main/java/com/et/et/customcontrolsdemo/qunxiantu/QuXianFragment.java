package com.et.et.customcontrolsdemo.qunxiantu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.et.et.customcontrolsdemo.R;
import com.et.et.customcontrolsdemo.qunxiantu.bean.LocationInfo;
import com.et.et.customcontrolsdemo.utils.NetClinet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;

/**
 * @author wangxiongfeng
 * @date 2017/7/12 0012 17:12
 */

public class QuXianFragment extends Fragment {
    @BindView(R.id.weathInfo)
    TextView weathInfo;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.activity_quxian, container, false);

        NetClinet.getNetClientInstance()
                .getWeathinfo(new Subscriber<LocationInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LocationInfo locationInfo) {
                        String result = locationInfo.toString();
                        weathInfo.setText(result);
                    }
                }, "101010100");

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
