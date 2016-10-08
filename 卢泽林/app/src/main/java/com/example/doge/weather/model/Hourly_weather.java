package com.example.doge.weather.model;

/**
 * Created by Doge on 2016/10/5.
 */
public class Hourly_weather {

    private String weather;//天气

    private String date;//时间

    public Hourly_weather(){

    }

    public Hourly_weather(String weather,String date){
        this.date=date;
        this.weather=weather;
    }

    public String getDate(){
        return date;
    }

    public String getWeather(){
        return weather;
    }

    public void setWeather(String weather){
        this.weather=weather;
    }

    public void setDate(String date){
        this.date=date;
    }
}
