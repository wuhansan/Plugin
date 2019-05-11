package com.wl.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * @Auther: wl
 * @Date: 2019/5/10 18:10
 * @Description:
 */
public class PluginApk {

    //插件apk的实体类
    public PackageInfo mPackageInfo;
    public Resources mResources;
    public AssetManager mAssetManager;
    public DexClassLoader mDexClassLoader;

    public PluginApk(PackageInfo packageInfo, Resources resources, DexClassLoader dexClassLoader) {
        this.mPackageInfo = packageInfo;
        this.mResources = resources;
        mAssetManager = resources.getAssets();
        this.mDexClassLoader = dexClassLoader;
    }
}
