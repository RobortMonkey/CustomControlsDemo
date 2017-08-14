package com.et.et.customcontrolsdemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<Bean> list = new ArrayList<>();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Bean luopan = new Bean();
        luopan.setClassName("luopan");
        luopan.setName("指南针控件");
        list.add(luopan);

        Bean quxian = new Bean();
        quxian.setClassName("quxian");
        quxian.setName("天气");
        list.add(quxian);

        Bean dongHua = new Bean();
        dongHua.setClassName("dongHua");
        dongHua.setName("动画");
        list.add(dongHua);


        ListViewAdapter adapter = new ListViewAdapter(this, list);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ViewLayoutActivity.class);
                intent.putExtra("name", list.get(i).getClassName());
                startActivity(intent);
            }
        });
        requestIntent();
    }

    protected void requestIntent() {
        if (!(ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 2002);
        }
    }

}
