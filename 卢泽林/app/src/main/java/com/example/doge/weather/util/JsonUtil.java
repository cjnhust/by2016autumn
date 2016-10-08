package com.example.doge.weather.util;

import android.graphics.Bitmap;

import com.example.doge.weather.model.City;
import com.example.doge.weather.model.Daily_weather;
import com.example.doge.weather.model.Hourly_weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Doge on 2016/10/4.
 */
public class JsonUtil {

    public static City getCityFromJson(String json) throws JSONException {
        City city = new City();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("HeWeather data service 3.0");
        JSONObject newInfo = jsonArray.getJSONObject(0);
        //basic
        JSONObject basic = newInfo.getJSONObject("basic");
        city.setCityCode(basic.getString("id"));
        city.setCityName(basic.getString("city"));
        JSONObject update = basic.getJSONObject("update");
        city.setLocTime(update.getString("loc"));
        //AQI
        JSONObject aqi = newInfo.getJSONObject("aqi");
        JSONObject Jcity = aqi.getJSONObject("city");
        city.setAQI(Jcity.getString("aqi"));
        city.setCo(Jcity.getString("co"));
        city.setNo2(Jcity.getString("no2"));
        city.setO3(Jcity.getString("o3"));
        city.setPm25(Jcity.getString("pm25"));
        city.setSo2(Jcity.getString("so2"));
        city.setQlty(Jcity.getString("qlty"));
        //now
        JSONObject now = newInfo.getJSONObject("now");
        JSONObject cond = now.getJSONObject("cond");
        city.setIcon(cond.getString("code"));
        city.setWeather(cond.getString("txt"));

        city.setTmp(now.getString("tmp"));
        city.setPcpn(now.getString("pcpn"));
        city.setHum(now.getString("hum"));
        //wind
        JSONObject wind = now.getJSONObject("wind");
        city.setSpd(wind.getString("spd"));
        city.setSc(wind.getString("sc"));
        city.setDir(wind.getString("dir"));
        //daily_forecast
        JSONArray daily_forecast = newInfo.getJSONArray("daily_forecast");

        ArrayList<Daily_weather> daily_weathers = new ArrayList<>();
        for(int i=0;i<daily_forecast.length();i++){
            JSONObject temp = daily_forecast.getJSONObject(i);
            Daily_weather daily_weather = new Daily_weather();
            daily_weather.setDate(temp.getString("date"));
            JSONObject dayily_cond = temp.getJSONObject("cond");
            daily_weather.setIcon_d(dayily_cond.getString("code_d"));
            daily_weather.setIcon_n(dayily_cond.getString("code_n"));
            daily_weather.setWeather_d(dayily_cond.getString("txt_d"));
            daily_weather.setWeather_n(dayily_cond.getString("txt_n"));
            JSONObject dayily_wind = temp.getJSONObject("wind");
            daily_weather.setWind_dir(dayily_wind.getString("dir"));
            daily_weather.setWind_sc(dayily_wind.getString("sc"));
            JSONObject dayily_tmp = temp.getJSONObject("tmp");
            daily_weather.setMax(dayily_tmp.getString("max"));
            daily_weather.setMin(dayily_tmp.getString("min"));
            daily_weathers.add(daily_weather);
        }
        city.setDaily_weathers(daily_weathers);
        //hourly_forecast
        JSONArray hourly_forecast = newInfo.getJSONArray("hourly_forecast");
        ArrayList<Hourly_weather> hourly_weathers = new ArrayList<>();
        for(int i=0;i<hourly_forecast.length();i++){
            JSONObject temp = hourly_forecast.getJSONObject(i);
            Hourly_weather hourly_weather = new Hourly_weather();
            hourly_weather.setWeather(temp.getString("tmp"));
            hourly_weather.setDate(temp.getString("date"));
            hourly_weathers.add(hourly_weather);
        }
        city.setHourly_weathers(hourly_weathers);
        //suggestion
        JSONObject suggestion = newInfo.getJSONObject("suggestion");
        JSONObject comf = suggestion.getJSONObject("comf");
        city.setComf_brf(comf.getString("brf"));
        city.setComf_txt(comf.getString("txt"));

        JSONObject cw = suggestion.getJSONObject("cw");
        city.setCw_brf(cw.getString("brf"));
        city.setCw_txt(cw.getString("txt"));

        JSONObject drsg = suggestion.getJSONObject("drsg");
        city.setDrsg_brf(drsg.getString("brf"));
        city.setDrsg_txt(drsg.getString("txt"));

        JSONObject flu = suggestion.getJSONObject("flu");
        city.setFlu_brf(flu.getString("brf"));
        city.setFlu_txt(flu.getString("txt"));

        JSONObject sport = suggestion.getJSONObject("sport");
        city.setSport_brf(sport.getString("brf"));
        city.setSport_txt(sport.getString("txt"));

        JSONObject trav = suggestion.getJSONObject("trav");
        city.setTrav_brf(trav.getString("brf"));
        city.setTrav_txt(trav.getString("txt"));

        JSONObject uv = suggestion.getJSONObject("uv");
        city.setUv_brf(uv.getString("brf"));
        city.setUv_txt(uv.getString("txt"));

        return city;
    }


}
