package com.example.administrator.weathertimeline10;

/**
 * Created by Administrator on 2016/10/3/003.
 */

public class Weather {
    private String city;
    private int imageID;
    private String tempture;
    public Weather(String city, int imageID,String tempture){
        this.city=city;
        this.imageID=imageID;
        this.tempture=tempture;
    }
    public String getName(){
        return city;
    }
    public int getImageID(){
        return imageID;
    }
    public String getTempture() {return  tempture;}
}
