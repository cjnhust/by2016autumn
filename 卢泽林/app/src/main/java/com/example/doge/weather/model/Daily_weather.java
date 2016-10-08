package com.example.doge.weather.model;

/**
 * Created by Doge on 2016/10/5.
 */
public class Daily_weather {

    private String date;//日期

    private String icon_d;//白天图标

    private String icon_n;//夜晚图标

    private String weather_d;//白天天气

    private String weather_n;//夜晚天气

    private String wind_dir;//风向

    private String wind_sc;//风力级别

    private String max;//最大温度

    private String min;//最小温度

    public Daily_weather() {
    }

    public Daily_weather(String date, String icon_d, String icon_n, String weather_d, String weather_n, String wind_dir, String wind_sc, String max, String min) {
        this.date = date;
        this.icon_d = icon_d;
        this.icon_n = icon_n;
        this.weather_d = weather_d;
        this.weather_n = weather_n;
        this.wind_dir = wind_dir;
        this.wind_sc = wind_sc;
        this.max = max;
        this.min = min;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIcon_d(String icon_d) {
        this.icon_d = icon_d;
    }

    public void setIcon_n(String icon_n) {
        this.icon_n = icon_n;
    }

    public void setWeather_d(String weather_d) {
        this.weather_d = weather_d;
    }

    public void setWeather_n(String weather_n) {
        this.weather_n = weather_n;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public void setWind_sc(String wind_sc) {
        this.wind_sc = wind_sc;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getDate() {
        return date;
    }

    public String getIcon_d() {
        return icon_d;
    }

    public String getIcon_n() {
        return icon_n;
    }

    public String getWeather_d() {
        return weather_d;
    }

    public String getWeather_n() {
        return weather_n;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public String getWind_sc() {
        return wind_sc;
    }

    public String getMax() {
        return max;
    }

    public String getMin() {
        return min;
    }
}
