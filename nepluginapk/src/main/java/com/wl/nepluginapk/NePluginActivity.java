package com.wl.nepluginapk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wl.pluginlib.LogUtils;
import com.wl.pluginlib.PluginActivity;

public class NePluginActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ne_plugin);

        LogUtils.e("NePluginActivity");
    }
}
