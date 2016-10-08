package com.example.doge.weather.model;

import android.support.annotation.ArrayRes;

import java.io.Serializable;
import java.io.SerializablePermission;
import java.util.ArrayList;

/**
 * Created by Doge on 2016/10/5.
 */
public class City implements Serializable {

    private String cityCode;//城市代码

    private String cityName;//城市名称

    private String locTime;//当地时间

    private String AQI;

    private String qlty;

    private String co;

    private String no2;

    private String o3;

    private String so2;

    private String pm25;

    private String tmp;//当前温度

    private String icon;//当前天气图标

    private String weather;//当前天气

    private String pcpn;//降雨量

    private String hum;//湿度

    private String spd;//风速

    private String sc;//风力等级

    private String dir;//风向

    private String comf_brf;

    private String comf_txt;

    private String cw_brf;

    private String cw_txt;

    private String drsg_brf;

    private String drsg_txt;

    private String flu_brf;

    private String flu_txt;

    private String sport_brf;

    private String sport_txt;

    private String trav_brf;

    private String trav_txt;

    private String uv_brf;

    private String uv_txt;

    private ArrayList<Daily_weather> daily_weathers;//七日天气预报

    private ArrayList<Hourly_weather> hourly_weathers;//今日小时温度（只有预报）

    public City() {
    }

    public City(String tmp,String cityCode, String cityName, String locTime, String AQI, String qlty, String co, String no2, String o3, String so2, String pm25, String icon, String weather, String pcpn, String hum, String spd, String sc, String dir, String comf_brf, String comf_txt, String cw_brf, String cw_txt, String drsg_brf, String drsg_txt, String flu_brf, String flu_txt, String sport_brf, String sport_txt, String trav_brf, String trav_txt, String uv_brf, String uv_txt, ArrayList<Daily_weather> daily_weathers, ArrayList<Hourly_weather> hourly_weathers) {
        this.tmp=tmp;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.locTime = locTime;
        this.AQI = AQI;
        this.qlty = qlty;
        this.co = co;
        this.no2 = no2;
        this.o3 = o3;
        this.so2 = so2;
        this.pm25 = pm25;
        this.icon = icon;
        this.weather = weather;
        this.pcpn = pcpn;
        this.hum = hum;
        this.spd = spd;
        this.sc = sc;
        this.dir = dir;
        this.comf_brf = comf_brf;
        this.comf_txt = comf_txt;
        this.cw_brf = cw_brf;
        this.cw_txt = cw_txt;
        this.drsg_brf = drsg_brf;
        this.drsg_txt = drsg_txt;
        this.flu_brf = flu_brf;
        this.flu_txt = flu_txt;
        this.sport_brf = sport_brf;
        this.sport_txt = sport_txt;
        this.trav_brf = trav_brf;
        this.trav_txt = trav_txt;
        this.uv_brf = uv_brf;
        this.uv_txt = uv_txt;
        this.daily_weathers = daily_weathers;
        this.hourly_weathers = hourly_weathers;
    }

    /**
     * 将一个Daily_weather类加压到一个字符串中，便于储存
     * @return
     */
    public ArrayList<String> DayilyWeatherToString(){
        ArrayList<String> strReturn = new ArrayList<>();
        for(int i=0;i<daily_weathers.size();i++){
            StringBuilder builder = new StringBuilder();
            Daily_weather temp = daily_weathers.get(i);
            builder.append(temp.getDate());
            builder.append("|");
            builder.append(temp.getIcon_d());
            builder.append("|");
            builder.append(temp.getIcon_n());
            builder.append("|");
            builder.append(temp.getWeather_d());
            builder.append("|");
            builder.append(temp.getWeather_n());
            builder.append("|");
            builder.append(temp.getWind_dir());
            builder.append("|");
            builder.append(temp.getWind_sc());
            builder.append("|");
            builder.append(temp.getMax());
            builder.append("|");
            builder.append(temp.getMin());
            String weather = builder.toString();
            strReturn.add(weather);
        }
        return strReturn;
    }

    /**
     * 解析字符串成Dailyweather类
     * @param data
     * @return
     */
    public static Daily_weather StringToDailyWeather(String data){
        String[] array = data.split("\\|");
        Daily_weather temp = new Daily_weather();
        temp.setDate(array[0]);
        temp.setIcon_d(array[1]);
        temp.setIcon_n(array[2]);
        temp.setWeather_d(array[3]);
        temp.setWeather_n(array[4]);
        temp.setWind_dir(array[5]);
        temp.setWind_sc(array[6]);
        temp.setMax(array[7]);
        temp.setMin(array[8]);
        return temp;
    }

    /**
     * 将今日的hourTime数据加压到一个字符串中，便于储存
     * @return
     */
    public String HourlyTimeToString(){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<hourly_weathers.size();i++){
            Hourly_weather temp = hourly_weathers.get(i);
            if(i!=0){
                builder.append("|");
            }
            builder.append(temp.getDate());
        }
        return builder.toString();
    }

    /**
     * 解析含有HourlyTime或HourlyWeather的字符串
     * @param data
     * @return
     */
    public static ArrayList<String> StringToHourly(String data){
        ArrayList<String> arrayList = new ArrayList<>();
        String[] array = data.split("\\|");
        for(int i=0;i<array.length;i++){
            arrayList.add(array[i]);
        }
        return arrayList;
    }

    /**
     * 将今日的hourWeather数据加压到一个字符串中，便于储存
     * @return
     */
    public String HourlyWeatherToString(){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<hourly_weathers.size();i++){
            Hourly_weather temp = hourly_weathers.get(i);
            if(i!=0){
                builder.append("|");
            }
            builder.append(temp.getWeather());
        }
        return builder.toString();
    }

    public void setTmp(String tmp){ this.tmp=tmp;}

    public String getTmp(){ return tmp;}

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setLocTime(String locTime) {
        this.locTime = locTime;
    }

    public void setAQI(String AQI) {
        this.AQI = AQI;
    }

    public void setQlty(String qlty) {
        this.qlty = qlty;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setComf_brf(String comf_brf) {
        this.comf_brf = comf_brf;
    }

    public void setComf_txt(String comf_txt) {
        this.comf_txt = comf_txt;
    }

    public void setCw_brf(String cw_brf) {
        this.cw_brf = cw_brf;
    }

    public void setCw_txt(String cw_txt) {
        this.cw_txt = cw_txt;
    }

    public void setDrsg_brf(String drsg_brf) {
        this.drsg_brf = drsg_brf;
    }

    public void setDrsg_txt(String drsg_txt) {
        this.drsg_txt = drsg_txt;
    }

    public void setFlu_brf(String flu_brf) {
        this.flu_brf = flu_brf;
    }

    public void setFlu_txt(String flu_txt) {
        this.flu_txt = flu_txt;
    }

    public void setSport_brf(String sport_brf) {
        this.sport_brf = sport_brf;
    }

    public void setSport_txt(String sport_txt) {
        this.sport_txt = sport_txt;
    }

    public void setTrav_brf(String trav_brf) {
        this.trav_brf = trav_brf;
    }

    public void setTrav_txt(String trav_txt) {
        this.trav_txt = trav_txt;
    }

    public void setUv_brf(String uv_brf) {
        this.uv_brf = uv_brf;
    }

    public void setUv_txt(String uv_txt) {
        this.uv_txt = uv_txt;
    }

    public void setDaily_weathers(ArrayList<Daily_weather> daily_weathers) {
        this.daily_weathers = daily_weathers;
    }

    public void setHourly_weathers(ArrayList<Hourly_weather> hourly_weathers) {
        this.hourly_weathers = hourly_weathers;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getLocTime() {
        return locTime;
    }

    public String getAQI() {
        return AQI;
    }

    public String getQlty() {
        return qlty;
    }

    public String getCo() {
        return co;
    }

    public String getNo2() {
        return no2;
    }

    public String getO3() {
        return o3;
    }

    public String getSo2() {
        return so2;
    }

    public String getPm25() {
        return pm25;
    }

    public String getIcon() {
        return icon;
    }

    public String getWeather() {
        return weather;
    }

    public String getPcpn() {
        return pcpn;
    }

    public String getHum() {
        return hum;
    }

    public String getSpd() {
        return spd;
    }

    public String getSc() {
        return sc;
    }

    public String getDir() {
        return dir;
    }

    public String getComf_brf() {
        return comf_brf;
    }

    public String getComf_txt() {
        return comf_txt;
    }

    public String getCw_brf() {
        return cw_brf;
    }

    public String getCw_txt() {
        return cw_txt;
    }

    public String getDrsg_brf() {
        return drsg_brf;
    }

    public String getDrsg_txt() {
        return drsg_txt;
    }

    public String getFlu_brf() {
        return flu_brf;
    }

    public String getFlu_txt() {
        return flu_txt;
    }

    public String getSport_brf() {
        return sport_brf;
    }

    public String getSport_txt() {
        return sport_txt;
    }

    public String getTrav_brf() {
        return trav_brf;
    }

    public String getTrav_txt() {
        return trav_txt;
    }

    public String getUv_brf() {
        return uv_brf;
    }

    public String getUv_txt() {
        return uv_txt;
    }

    public ArrayList<Daily_weather> getDaily_weathers() {
        return daily_weathers;
    }

    public ArrayList<Hourly_weather> getHourly_weathers() {
        return hourly_weathers;
    }
}