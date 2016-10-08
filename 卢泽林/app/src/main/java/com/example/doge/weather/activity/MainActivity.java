package com.example.doge.weather.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.doge.weather.R;
import com.example.doge.weather.db.WeatherDB;
import com.example.doge.weather.model.City;
import com.example.doge.weather.util.BitmapUtil;
import com.example.doge.weather.util.CityAdapter;
import com.example.doge.weather.util.HttpCallbackListener;
import com.example.doge.weather.util.HttpUtil;
import com.example.doge.weather.util.JsonUtil;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String MyKey = "bf4583de3693405897528eeddb7ee4e3";

    private PopupMenu popupMenu;
    private ListView mylist;
    private Menu menu;
    private List<City> cityList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        final String cityName="武汉";
        String address = "https://api.heweather.com/x3/weather?city="+cityName+"&key=" + MyKey;
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(InputStream in) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                try {
                    while((line =reader.readLine())!=null){
                        response.append(line);
                    }
                    City city = JsonUtil.getCityFromJson(response.toString());
                    WeatherDB.getInstance(MainActivity.this).saveCity(city);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });
        //加载数据
        cityList = WeatherDB.getInstance(MainActivity.this).LoadCity();
        //Menu
        popupMenu = new PopupMenu(this,findViewById(R.id.menu));
        menu = popupMenu.getMenu();
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        //ListView
        if(cityList!=null) {
            mylist = (ListView) findViewById(R.id.mylist);
            RelativeLayout empty = (RelativeLayout) findViewById(R.id.empty);
            mylist.setEmptyView(empty);
            CityAdapter cityAdapter = new CityAdapter(MainActivity.this, R.layout.city_item, cityList);
            mylist.setAdapter(cityAdapter);
            mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    City city = cityList.get(i);
                    Intent intent = new Intent(MainActivity.this,ShowWeather.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("city",city);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        //FloatingActionButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddCity.class);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
