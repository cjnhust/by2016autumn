package com.example.administrator.weathertimeline10;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import newclass.Fruit;
import android.app.Notification.Builder;
@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity {
    private Button button2;
    private Button but1 = null;
    private NotificationManager nm = null;
    private PendingIntent contentIntent = null;
    private List<Weather> weatherList = new ArrayList<Weather>();//ArrayList 是一个动态数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//采用R_layout进行布局
        init();
        button2 = (Button) findViewById(R.id.button2);//转回来是一个view对象，用button强制转换
        button2.setOnClickListener(new OnClickListener() {//
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);//intent用来连接两个活动,第一个是上下文参数
                startActivity(intent); // 启动Activity
            }
        });
        initWeathers();
        WeatherAdapter adapter = new WeatherAdapter(MainActivity.this, R.layout.weather, weatherList);//在Weather方法中一一对应
        ListView listview = (ListView) findViewById(R.id.list_view1);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Weather weather = weatherList.get(position);
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        });
    }
    private void initWeathers(){
        Weather cloudy =new Weather("当前地点",R.mipmap.cloudy,"19 ℃");
        weatherList.add(cloudy);
        Weather sun=new Weather("当前地点",R.mipmap.sun,"19 ℃");
        weatherList.add(sun);
        Weather heavyrain=new Weather("当前地点",R.mipmap.heavyrain,"19 ℃");
        weatherList.add(heavyrain);
        Weather littlerain=new Weather("当前地点",R.mipmap.littlerain,"19 ℃");
        weatherList.add(littlerain);
        Weather cloudysun=new Weather("当前地点",R.mipmap.cloudysun,"19 ℃");
        weatherList.add(cloudysun);
    }
    public void init() {
        but1 = (Button) findViewById(R.id.but1);
        but1.setOnClickListener(onclick);
        nm = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        contentIntent = PendingIntent.getActivity(MainActivity.this, 0, notificationIntent, 0);
    }
    OnClickListener onclick = new OnClickListener() {
        private final int NOTIFICATION_BASE_NUMBER = 110;
        private Builder builder = null;
        private Notification n = null;

        public void onClick(View arg0) {
            switch (arg0.getId()) {
                case R.id.but1:
                    NotificationManager nm = (NotificationManager) MainActivity.this
                            .getSystemService(NOTIFICATION_SERVICE);
                   // Resources res = MainActivity.this.getResources();
                    builder = new Notification.Builder(MainActivity.this);
                    builder.setContentIntent(contentIntent)
                            .setSmallIcon(R.mipmap.sun)
                            .setWhen(System.currentTimeMillis())
                            .setAutoCancel(true)
                            .setContentTitle("It is sunny!")
                            .setContentText("This is a context!");
                    n = builder.build();//获取一个通知
                    nm.notify(NOTIFICATION_BASE_NUMBER, n);
            }
        }
    };
}


