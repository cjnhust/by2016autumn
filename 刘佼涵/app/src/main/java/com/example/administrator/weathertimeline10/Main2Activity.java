package com.example.administrator.weathertimeline10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {
   private String[] data={"泉州","北京","上海","南京","天津","山东","桂林","江苏","海南","武汉"};
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent); // 启动Activity
            }
        });
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_list_item_1,data);
       ListView listView=(ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);//adapter为对象
    }
}