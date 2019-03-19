package com.example.abaka.homework;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class count extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        TabHost tabHost=getTabHost();
        tabHost.addTab(tabHost.newTabSpec("TAB1").setIndicator("记一笔").setContent(new Intent().setClass(this,First.class)));
        tabHost.addTab(tabHost.newTabSpec("TAB2").setIndicator("管理账单").setContent(new Intent().setClass(this,Second.class)));
        tabHost.addTab(tabHost.newTabSpec("TAB3").setIndicator("账单统计").setContent(new Intent().setClass(this,Third.class)));
    }
}
