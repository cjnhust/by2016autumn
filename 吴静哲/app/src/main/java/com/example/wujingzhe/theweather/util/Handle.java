package com.example.wujingzhe.theweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wujingzhe on 2016/10/6.
 */
public class Handle {

    //解析服务器返回的JSON数据，得到详细信息
    public static String handleWeatherResponse(Context context,String response){
        String count=null;
        try {
            String count1=null;
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("results");
            if (jsonArray.length()>0){
                JSONObject jsonObject1=jsonArray.getJSONObject(0);
                String city=jsonObject1.optString("currentCity");
                JSONArray jsonArray1=jsonObject1.getJSONArray("weather_data");
                JSONArray jsonArray2=jsonObject1.getJSONArray("index");
                JSONObject jsonObject2=jsonArray1.getJSONObject(0);
                String dateDay=jsonObject2.optString("date");
                String weather=jsonObject2.optString("weather");
                String wind=jsonObject2.optString("wind");
                String temperature=jsonObject2.optString("temperature");
                count1=dateDay + "\n" +"  "+"天气："+"\n"+"     "+ weather + " " + wind + " " + temperature;

                for (int i=0;i<jsonArray2.length();i++){
                    JSONObject jsonObject3=jsonArray2.getJSONObject(i);
                    String a1=jsonObject3.optString("title");
                    String a2=jsonObject3.optString("zs");
                    String a3=jsonObject3.optString("tipt");
                    String a4=jsonObject3.optString("des");
                    count1=count1+"\n"+"  "+a1+"："+"\n"+"     "+a2+"\n"+"  "+a3+"："+"\n"+"     "+a4;
                }
                for (int i=1;i<jsonArray1.length();i++){
                    JSONObject jsonObject3=jsonArray1.getJSONObject(i);
                    String dateDay1=jsonObject3.optString("date");
                    String weather1=jsonObject3.optString("weather");
                    String wind1=jsonObject3.optString("wind");
                    String temperature1=jsonObject3.optString("temperature");
                    count1=count1+"\n"+"\n"+dateDay1+"\n"+"  "+"天气："+"\n"+"     "+weather1+" "+wind1+" "+temperature1;
                }
                count=count1;                                           //count是详细信息
                saveWeatherInfo(context,count,city);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return count;
    }

    //解析服务器返回的JSON数据，得到简略信息
    public static String handleWeatherResponse0(Context context,String response){
        String count0=null;
        try{
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("results");
            JSONObject jsonObject1=jsonArray.getJSONObject(0);
            JSONArray jsonArray1=jsonObject1.getJSONArray("weather_data");
            JSONObject jsonObject2=jsonArray1.getJSONObject(0);
            String city=jsonObject1.optString("currentCity");
            String dateDay=jsonObject2.optString("date");
            String weather=jsonObject2.optString("weather");
            String temperature=jsonObject2.optString("temperature");
            count0=city+"\n"+"\n"+dateDay+"\n"+weather+"\n"+temperature;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return count0;//count0是简略信息
    }

    //将解析后的JSON数据存储到SharePreferences文件中
    private static void saveWeatherInfo(Context context,String count,String cityName){
        SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("weather_detail_info",count);
        editor.putString("city_name",cityName);
        editor.commit();
    }

}
