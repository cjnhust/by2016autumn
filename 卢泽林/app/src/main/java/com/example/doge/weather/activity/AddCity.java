package com.example.doge.weather.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doge.weather.R;
import com.example.doge.weather.db.WeatherDB;
import com.example.doge.weather.model.City;
import com.example.doge.weather.util.HttpCallbackListener;
import com.example.doge.weather.util.HttpUtil;
import com.example.doge.weather.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Doge on 2016/10/7.
 */
public class AddCity extends AppCompatActivity {

    private static final String MyKey = "bf4583de3693405897528eeddb7ee4e3";

    private EditText editText;

    private ImageView imageView;
    private String response = null;
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.add_city);
        editText = (EditText)findViewById(R.id.getCity);
        imageView = (ImageView)findViewById(R.id.ok);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = editText.getText().toString();
                String url ="https://api.heweather.com/x3/weather?city="+cityName+"&key=" + MyKey;
                HttpUtil.sendHttpRequest(url, new HttpCallbackListener() {
                    @Override
                    public void onFinish(InputStream in) {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                            StringBuilder builder = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null) {
                                builder.append(line);
                                response = builder.toString();
                                City city = JsonUtil.getCityFromJson(response);
                                WeatherDB.getInstance(AddCity.this).saveCity(city);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
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
        });
    }

    public void getCityQuick(View view){
        Button button = (Button) view;
        String cityName = button.getText().toString();
        int black = Color.parseColor("#000000");
        int gray = Color.parseColor("#808080");
        String url ="https://api.heweather.com/x3/weather?city="+cityName+"&key=" + MyKey;
        if(button.getCurrentTextColor()==black) {
            button.setTextColor(gray);
            button.setDrawingCacheEnabled(true);
            //修改图片
            HttpUtil.sendHttpRequest(url, new HttpCallbackListener() {
                @Override
                public void onFinish(InputStream in) {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                        StringBuilder builder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                            response = builder.toString();
                            City city = JsonUtil.getCityFromJson(response);
                            WeatherDB.getInstance(AddCity.this).saveCity(city);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
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
