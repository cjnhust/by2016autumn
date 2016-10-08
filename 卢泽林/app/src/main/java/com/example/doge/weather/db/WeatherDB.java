package com.example.doge.weather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.doge.weather.model.City;
import com.example.doge.weather.model.Daily_weather;
import com.example.doge.weather.model.Hourly_weather;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Doge on 2016/10/3.
 */
public class WeatherDB {

    /**
     * 数据库名
     */
    public static final String DB_NAME = "ShiXiTianQi";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static WeatherDB weatherDB;

    private SQLiteDatabase db;

    private WeatherDB(Context context){
        WeatherOpenHelper dbHelper = new WeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static WeatherDB getInstance(Context context){
        if(weatherDB == null){
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    /**
     * 将City实例储存到数据库
     * @param city
     */
    public void saveCity(City city){
        if(city !=null){
            Cursor cursor = db.query("forecast",null,"cityCode=?",new String[]{city.getCityCode()},null,null,null);
            if(cursor.moveToNext()){
                insert_data(city);
            }else{
                db.delete("forecast","cityCode=?",new String[]{city.getCityCode()});
                insert_data(city);
            }
            if(cursor!=null) {
                cursor.close();
            }
        }
    }

    /**
     * 从数据库读取全国所有的城市信息
     * @return
     */
    public ArrayList<City> LoadCity(){
        ArrayList<City> list = new ArrayList<City>();
        Cursor cursor = db.query("forecast",null,null,null,null,null,null);
        if(cursor.moveToNext()){
            do{
                City city = new City();
                city.setCityCode(cursor.getString(cursor.getColumnIndex("cityCode")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("cityName")));
                city.setLocTime(cursor.getString(cursor.getColumnIndex("locTime")));
                city.setTmp(cursor.getString(cursor.getColumnIndex("tmp")));
                city.setAQI(cursor.getString(cursor.getColumnIndex("AQI")));
                city.setQlty(cursor.getString(cursor.getColumnIndex("qlty")));
                city.setPm25(cursor.getString(cursor.getColumnIndex("pm25")));
                city.setSo2(cursor.getString(cursor.getColumnIndex("so2")));
                city.setNo2(cursor.getString(cursor.getColumnIndex("no2")));
                city.setCo(cursor.getString(cursor.getColumnIndex("co")));
                city.setO3(cursor.getString(cursor.getColumnIndex("o3")));
                city.setIcon(cursor.getString(cursor.getColumnIndex("icon")));
                city.setWeather(cursor.getString(cursor.getColumnIndex("weather")));
                city.setPcpn(cursor.getString(cursor.getColumnIndex("pcpn")));
                city.setHum(cursor.getString(cursor.getColumnIndex("hum")));
                city.setSpd(cursor.getString(cursor.getColumnIndex("spd")));
                city.setSc(cursor.getString(cursor.getColumnIndex("sc")));
                city.setDir(cursor.getString(cursor.getColumnIndex("dir")));
                city.setComf_brf(cursor.getString(cursor.getColumnIndex("comf_brf")));
                city.setComf_txt(cursor.getString(cursor.getColumnIndex("comf_txt")));
                city.setCw_brf(cursor.getString(cursor.getColumnIndex("cw_brf")));
                city.setCw_txt(cursor.getString(cursor.getColumnIndex("cw_txt")));
                city.setDrsg_brf(cursor.getString(cursor.getColumnIndex("drsg_brf")));
                city.setDrsg_txt(cursor.getString(cursor.getColumnIndex("drsg_txt")));
                city.setFlu_brf(cursor.getString(cursor.getColumnIndex("flu_brf")));
                city.setFlu_txt(cursor.getString(cursor.getColumnIndex("flu_txt")));
                city.setSport_brf(cursor.getString(cursor.getColumnIndex("sport_brf")));
                city.setSport_txt(cursor.getString(cursor.getColumnIndex("sport_txt")));
                city.setTrav_brf(cursor.getString(cursor.getColumnIndex("trav_brf")));
                city.setTrav_txt(cursor.getString(cursor.getColumnIndex("trav_txt")));
                city.setUv_brf(cursor.getString(cursor.getColumnIndex("uv_brf")));
                city.setUv_txt(cursor.getString(cursor.getColumnIndex("uv_txt")));
                ArrayList<Daily_weather> dayily_weather = new ArrayList<>();
                for(int i=1;i<=7;i++) {
                    dayily_weather.add(City.StringToDailyWeather(cursor.getString(cursor.getColumnIndex("dayily_weather_"+i))));
                }
                city.setDaily_weathers(dayily_weather);
                ArrayList<Hourly_weather> hourly_weathers = new ArrayList<>();
                String[] temperature,time;
                temperature=cursor.getString(cursor.getColumnIndex("hourly_temperature")).split("\\|");
                time=cursor.getString(cursor.getColumnIndex("hourly_time")).split("\\|");
                for(int i=0;i<temperature.length;i++){
                    Hourly_weather temp = new Hourly_weather();
                    temp.setDate(time[i]);
                    temp.setWeather(temperature[i]);
                    hourly_weathers.add(temp);
                }
                city.setHourly_weathers(hourly_weathers);
                list.add(city);
            }while ((cursor.moveToNext()));
        }
        if(cursor!=null){
            cursor.close();
        }
        return list;
    }


    private void insert_data(City city){
        ContentValues values = new ContentValues();
        values.put("cityCode",city.getCityCode());
        values.put("cityName",city.getCityName());
        values.put("locTime",city.getLocTime());
        values.put("tmp",city.getTmp());
        values.put("AQI",city.getAQI());
        values.put("qlty",city.getQlty());
        values.put("pm25",city.getPm25());
        values.put("so2",city.getSo2());
        values.put("no2",city.getNo2());
        values.put("co",city.getCo());
        values.put("o3",city.getO3());
        values.put("icon",city.getIcon());
        values.put("weather",city.getWeather());
        values.put("pcpn",city.getPcpn());
        values.put("hum",city.getHum());
        values.put("spd",city.getSpd());
        values.put("sc",city.getSc());
        values.put("dir",city.getDir());
        values.put("comf_brf",city.getComf_brf());
        values.put("comf_txt",city.getComf_txt());
        values.put("drsg_brf",city.getDrsg_brf());
        values.put("drsg_txt",city.getDrsg_txt());
        values.put("uv_brf",city.getUv_brf());
        values.put("uv_txt",city.getUv_txt());
        values.put("cw_brf",city.getCw_brf());
        values.put("cw_txt",city.getCw_txt());
        values.put("trav_brf",city.getTrav_brf());
        values.put("trav_txt",city.getTrav_txt());
        values.put("flu_brf",city.getFlu_brf());
        values.put("flu_txt",city.getFlu_txt());
        values.put("sport_brf",city.getSport_brf());
        values.put("sport_txt",city.getSport_txt());
        //七日温度、天气描述、图标代码
        values.put("dayily_weather_1",city.DayilyWeatherToString().get(0));
        values.put("dayily_weather_2",city.DayilyWeatherToString().get(1));
        values.put("dayily_weather_3",city.DayilyWeatherToString().get(2));
        values.put("dayily_weather_4",city.DayilyWeatherToString().get(3));
        values.put("dayily_weather_5",city.DayilyWeatherToString().get(4));
        values.put("dayily_weather_6",city.DayilyWeatherToString().get(5));
        values.put("dayily_weather_7",city.DayilyWeatherToString().get(6));
        //今日每三小时的温度
        values.put("hourly_temperature",city.HourlyWeatherToString());
        values.put("hourly_time",city.HourlyTimeToString());
        db.insert("forecast",null,values);
    }



}
