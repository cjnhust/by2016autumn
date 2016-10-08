package com.example.doge.weather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import com.example.doge.weather.db.WeatherDB;
import com.example.doge.weather.model.City;
import com.example.doge.weather.receiver.AutoUpdateReceiver;
import com.example.doge.weather.util.HttpCallbackListener;
import com.example.doge.weather.util.HttpUtil;
import com.example.doge.weather.util.JsonUtil;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Doge on 2016/10/7.
 */
public class AutoUpdateService extends Service {

    public static final String MyKey = "bf4583de3693405897528eeddb7ee4e3";

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

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour = 8 * 60 * 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, AutoUpdateReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }
    /**
     *更新天气信息
     */
    private void updateWeather(){
        ArrayList<City> cities=WeatherDB.getInstance(this).LoadCity();
        for(int i=0;i<cities.size();i++) {
            final City city = cities.get(i);
            String cityName= city.getCityName();
            String address = "https://api.heweather.com/x3/weather?city="+cityName+"&key=" + MyKey;
            HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {

                @Override
                public void onFinish(InputStream in)  {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    try {
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        City city1 = JsonUtil.getCityFromJson(response.toString());
                        if (city1!=null){
                            WeatherDB.getInstance(AutoUpdateService.this).saveCity(city1);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
