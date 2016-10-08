package com.thinkland.juheapi;

import android.app.Application;
import com.thinkland.sdk.android.JuheSDKInitializer;


/**
 * Created by Administrator on 2016/10/2/002.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
       super.onCreate();
        JuheSDKInitializer.initialize(getApplicationContext());
    }//初始化聚合数据SDK
}
