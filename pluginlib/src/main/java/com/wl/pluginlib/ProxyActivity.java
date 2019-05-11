package com.wl.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

/**
 * @Auther: wl
 * @Date: 2019/5/10 18:54
 * @Description: 代理activity 管理插件activity生命周期
 */
public class ProxyActivity extends Activity {
    private String mClassName;
    private PluginApk mPluginApk;
    private IPlugin mIPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtils.e("onCreate");

        //拿到需要启动的Activity
        mClassName = getIntent().getStringExtra("className");

        LogUtils.e(mClassName);

        mPluginApk = PluginManager.getInstance().getPluginApk();


        launchPluginActivity();
    }

    private void launchPluginActivity() {
        LogUtils.e("launchPluginActivity");

        if (null == mPluginApk) {
            LogUtils.e("null=mPluginApk");
            throw new RuntimeException("load your apk file first ");
        } else {
            LogUtils.e("null!=mPluginApk");
            try {
                //加载该Activity的字节码对象
                Class<?> clazz = mPluginApk.mDexClassLoader.loadClass(mClassName);
                //创建该Activity的示例
                Object o = clazz.newInstance();
                //程序健壮性检查
                if (o instanceof IPlugin) {
                    mIPlugin = (IPlugin) o;
                    //将代理Activity的实例传递给三方Activity
                    mIPlugin.attach(this);
                    //创建bundle用来与三方apk传输数据
                    Bundle bundle = new Bundle();

                    bundle.putInt("FROM", IPlugin.FROM_EXTERNAL);

                    //调用三方Activity的onCreate，
                    mIPlugin.onCreate(bundle);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Resources getResources() {
        return null != mPluginApk ? mPluginApk.mResources : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return null != mPluginApk ? mPluginApk.mAssetManager : super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return null != mPluginApk ? mPluginApk.mDexClassLoader : super.getClassLoader();
    }
}
