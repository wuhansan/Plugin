package com.wl.dataturbo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.wl.pluginlib.PluginActivity;
import com.wl.pluginlib.PluginManager;
import com.wl.pluginlib.ProxyActivity;

import java.io.IOException;

public class MainActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PluginManager.getInstance().init(this);

        findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String filePath = Utils.copyAssetAndWrite(MainActivity.this, "aa.apk");
                    PluginManager.getInstance().loadApk(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ProxyActivity.class);
                intent.putExtra("className", "com.wl.nepluginapk.NePluginActivity");
                startActivity(intent);

                Log.e("MainActivity","startActivity");
            }
        });

    }
}
