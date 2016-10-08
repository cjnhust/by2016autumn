package com.example.doge.weather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Doge on 2016/10/3.
 */
public class WeatherOpenHelper extends SQLiteOpenHelper{

    public static final String CREATE_TABLE = "CREATE TABLE forecast(" +
            "cityCode text primary key," +
            "cityName text," +
            "locTime text," +
            "tmp text," +
            "AQI text," +
            "qlty text," +
            "pm25 text," +
            "so2 text," +
            "no2 text," +
            "co text," +
            "o3 text," +
            "icon text," +
            "weather text,"+
            "pcpn text,"+
            "hum text,"+
            "spd text,"+
            "sc text,"+
            "dir text,"+
            //生活建议
            "comf_brf text,"+
            "comf_txt text,"+
            "cw_brf text,"+
            "cw_txt text,"+
            "drsg_brf text,"+
            "drsg_txt text,"+
            "flu_brf text,"+
            "flu_txt text,"+
            "sport_brf text,"+
            "sport_txt text,"+
            "trav_brf text,"+
            "trav_txt text,"+
            "uv_brf text,"+
            "uv_txt text,"+
            //七日温度、天气描述、图标代码
            "dayily_weather_1 text," +
            "dayily_weather_2 text," +
            "dayily_weather_3 text," +
            "dayily_weather_4 text," +
            "dayily_weather_5 text," +
            "dayily_weather_6 text," +
            "dayily_weather_7 text," +
            //今日每三小时的温度
            "hourly_temperature text,"+
            "hourly_time text)";


    private Context mContext ;

    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
