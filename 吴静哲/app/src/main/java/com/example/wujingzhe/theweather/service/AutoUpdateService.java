package com.example.wujingzhe.theweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

/**
 * Created by wujingzhe on 2016/10/7.
 */
public class AutoUpdateService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateWeather();
            }
        }).start();
        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
        int sixHour=6*60*60*1000;//6小时的毫秒数
        long triggerAtTime= SystemClock.elapsedRealtime()+sixHour;
        Intent intent1=new Intent(this,AutoUpdateService.class);
        PendingIntent pi=PendingIntent.getService(this,0,intent1,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }

    //自动更新天气的方法
    private void updateWeather(){

    }

}
