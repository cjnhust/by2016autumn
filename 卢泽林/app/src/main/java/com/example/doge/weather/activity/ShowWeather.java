package com.example.doge.weather.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doge.weather.R;
import com.example.doge.weather.model.City;
import com.example.doge.weather.model.Daily_weather;
import com.example.doge.weather.util.HttpCallbackListener;
import com.example.doge.weather.util.HttpUtil;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Doge on 2016/10/7.
 */
public class ShowWeather extends AppCompatActivity implements View.OnClickListener {

    public static final String MyKey = "bf4583de3693405897528eeddb7ee4e3";

    public static final int ShowText = 0;
    public static final int ShowImage1 = 1;
    public static final int ShowImage2 = 2;
    public static final int ShowImage3 = 3;


    private TextView temperature;
    private TextView weather;
    private TextView wind_dir;
    private TextView wind_sc;
    private TextView hum;
    private TextView qlty;
    private TextView AQI;
    private TextView day1_weather;
    private TextView day2_weather;
    private TextView day3_weather;
    private TextView day1_tmp;
    private TextView day2_tmp;
    private TextView day3_tmp;

    private ImageView icon_day1;
    private ImageView icon_day2;
    private ImageView icon_day3;

    private Button detail;

    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case ShowText:
                    City city = (City)msg.obj;
                    temperature.setText(city.getTmp()+"°");
                    weather.setText(city.getCityName()+"|"+city.getWeather());
                    wind_dir.setText(city.getDir());
                    wind_sc.setText(city.getSc()+"级");
                    hum.setText(city.getHum()+"%");
                    qlty.setText("空气"+city.getQlty());
                    AQI.setText(city.getAQI());
                    Daily_weather dw1=city.getDaily_weathers().get(0);
                    Daily_weather dw2=city.getDaily_weathers().get(1);
                    Daily_weather dw3=city.getDaily_weathers().get(2);
                    day1_weather.setText(dw1.getWeather_d()+"| "+dw1.getWind_dir());
                    day2_weather.setText(dw2.getWeather_d()+"| "+dw2.getWind_dir());
                    day3_weather.setText(dw3.getWeather_d()+"| "+dw3.getWind_dir());
                    day1_tmp.setText(dw1.getMax()+"/"+dw1.getMin());
                    day2_tmp.setText(dw2.getMax()+"/"+dw2.getMin());
                    day3_tmp.setText(dw3.getMax()+"/"+dw3.getMin());
                    break;
                case ShowImage1:
                    Bitmap bitmap1 = (Bitmap)msg.obj;
                    icon_day1.setImageBitmap(bitmap1);
                    break;
                case ShowImage2:
                    Bitmap bitmap2 = (Bitmap)msg.obj;
                    icon_day2.setImageBitmap(bitmap2);
                    break;
                case ShowImage3:
                    Bitmap bitmap3 = (Bitmap)msg.obj;
                    icon_day3.setImageBitmap(bitmap3);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.show_weather);
        initView();
        City city = (City) getIntent().getSerializableExtra("city");
        final Message message1 = new Message();
        message1.obj=city;
        message1.what=ShowText;
        handler.sendMessage(message1);
            HttpUtil.sendHttpRequest("http://files.heweather.com/cond_icon/" + city.getDaily_weathers().get(0).getIcon_d() + ".png", new HttpCallbackListener() {
                @Override
                public void onFinish(InputStream in) {
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Message message2 = new Message();
                    message2.what = ShowImage1;
                    message2.obj = bitmap;
                    handler.sendMessage(message2);
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            });
        handler.sendMessage(message1);
        HttpUtil.sendHttpRequest("http://files.heweather.com/cond_icon/" + city.getDaily_weathers().get(1).getIcon_d() + ".png", new HttpCallbackListener() {
            @Override
            public void onFinish(InputStream in) {
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message message2 = new Message();
                message2.what = ShowImage2;
                message2.obj = bitmap;
                handler.sendMessage(message2);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
        handler.sendMessage(message1);
        HttpUtil.sendHttpRequest("http://files.heweather.com/cond_icon/" + city.getDaily_weathers().get(2).getIcon_d() + ".png", new HttpCallbackListener() {
            @Override
            public void onFinish(InputStream in) {
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message message2 = new Message();
                message2.what = ShowImage2;
                message2.obj = bitmap;
                handler.sendMessage(message2);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });

    }

    private void initView(){
        temperature = (TextView)findViewById(R.id.temperature);
        weather = (TextView)findViewById(R.id.weather);
        wind_dir = (TextView)findViewById(R.id.wind_dir);
        wind_sc = (TextView)findViewById(R.id.wind_sc);
        hum = (TextView)findViewById(R.id.hum);
        qlty = (TextView)findViewById(R.id.qlty);
        AQI = (TextView)findViewById(R.id.AQI);
        day1_weather = (TextView)findViewById(R.id.day1_weather);
        day2_weather = (TextView)findViewById(R.id.day2_weather);
        day3_weather = (TextView)findViewById(R.id.day3_weather);
        day1_tmp = (TextView)findViewById(R.id.day1_tmp);
        day2_tmp = (TextView)findViewById(R.id.day2_tmp);
        day3_tmp = (TextView)findViewById(R.id.day3_tmp);
        icon_day1=(ImageView)findViewById(R.id.icon_day1);
        icon_day2=(ImageView)findViewById(R.id.icon_day2);
        icon_day3=(ImageView)findViewById(R.id.icon_day3);
        detail = (Button)findViewById(R.id.detail);
        detail.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.detail:
                //跳转界面
                break;
            default:
                break;
        }
    }
}
