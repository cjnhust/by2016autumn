package com.example.wujingzhe.theweather.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.wujingzhe.theweather.R;
import com.example.wujingzhe.theweather.service.AutoUpdateService;
import com.example.wujingzhe.theweather.util.Handle;
import com.example.wujingzhe.theweather.util.HttpCallbackListener;
import com.example.wujingzhe.theweather.util.HttpUtil;

import java.util.Date;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button refresh;
    private Button add;
    private Button developerInfo;
    private TextView firstCity;
    private ProgressDialog progressDialog;
    public String firstCityName=null;

    private long previousClick;
    private long currentClick=0;
    private long deltaTime;
    private static final long AN_HOUR=1000*3600;
    private Boolean isRefresh=false;
    private Boolean isFromSearchActivity;
    private String count0Save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refresh= (Button) findViewById(R.id.refresh);
        firstCity= (TextView) findViewById(R.id.first_city);
        add= (Button) findViewById(R.id.plus_sign);
        developerInfo= (Button) findViewById(R.id.developer_info);


        SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(this);
        String cityName=pref.getString("city_name",null);
        firstCityName=cityName;
        String address="http://api.map.baidu.com/telematics/v3/weather?location="
                +firstCityName+"&output=json&ak=1hpsNcYGhaHVgcxa64aGBGDCaPzxEH7b&mcode="
                +"08:61:5A:97:AA:83:DF:D8:84:02:5B:2C:30:07:E9:5C:E2:E4:DC:90"
                +";com.example.wujingzhe";
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                count0Save=Handle.handleWeatherResponse0(MainActivity.this,response);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!isFromSearchActivity){
                            firstCity.setText(count0Save);
                        }
                    }
                });
            }

            @Override
            public void onError(Exception e) {

            }
        });

        String weatherInfo0=getIntent().getStringExtra("weather_info");//weatherInfo0是简略天气信息
        //判断是否是从SearchActivity中跳转过来的，得不到值则取默认值，即第二个参数false
        isFromSearchActivity=getIntent().getBooleanExtra("from_search_activity",false);

        if (isFromSearchActivity){
            String[] Info0=weatherInfo0.split("\n");
            firstCityName=Info0[0];
            Intent i=new Intent(this, AutoUpdateService.class);
            startService(i);
            firstCity.setText(weatherInfo0);
        }


        firstCity.setText(weatherInfo0);
        firstCity.setOnClickListener(this);
        add.setOnClickListener(this);
        refresh.setOnClickListener(this);
        developerInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.first_city:
                if (isFromSearchActivity||(firstCityName!=null)){
                    previousClick=currentClick;
                    currentClick=(new Date()).getTime();
                    deltaTime=currentClick-previousClick;
                    if (deltaTime>AN_HOUR||isRefresh){
                        isRefresh=false;
                        String address;
                        showProgressDialog();
                        address="http://api.map.baidu.com/telematics/v3/weather?location="
                                +firstCityName+"&output=json&ak=1hpsNcYGhaHVgcxa64aGBGDCaPzxEH7b&mcode="
                                +"08:61:5A:97:AA:83:DF:D8:84:02:5B:2C:30:07:E9:5C:E2:E4:DC:90"
                                +";com.example.wujingzhe";
                        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                            @Override
                            public void onFinish(String response) {
                                String count= Handle.handleWeatherResponse(MainActivity.this,response);
                                closeProgressDialog();
                                Intent intent=new Intent(MainActivity.this,DetailInfoActivity.class);
                                intent.putExtra("detail_info",count);//count是详细信息
                                intent.putExtra("first_city_name",firstCityName);
                                startActivity(intent);//跳转到详细信息界面
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
                    }else {
                        //从文件中读取
                        readFromSharePreference();
                    }
                }
                break;
            case R.id.refresh:
                isRefresh=true;
                showProgressDialog();
                String address;
                address="http://api.map.baidu.com/telematics/v3/weather?location="
                        +firstCityName+"&output=json&ak=1hpsNcYGhaHVgcxa64aGBGDCaPzxEH7b&mcode="
                        +"08:61:5A:97:AA:83:DF:D8:84:02:5B:2C:30:07:E9:5C:E2:E4:DC:90"
                        +";com.example.wujingzhe";
                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        final String count0=Handle.handleWeatherResponse0(MainActivity.this,response);
                        closeProgressDialog();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                firstCity.setText(count0);
                            }
                        });
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                break;
            case R.id.plus_sign:
                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.developer_info:
                Intent intent1=new Intent(MainActivity.this,DeveloperInfoActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    //显示进度框
    private void showProgressDialog(){
        if(progressDialog==null){
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    //关闭进度框
    private void closeProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    //从文件中读取天气信息
    private void readFromSharePreference(){
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(this);
        String count=pref.getString("weather_detail_info","");
        String cityName=pref.getString("city_name","");
        Intent intent=new Intent(this,DetailInfoActivity.class);
        intent.putExtra("detail_info",count);
        intent.putExtra("first_city_name",cityName);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        ActivityCollector.finishAll();
    }
}
