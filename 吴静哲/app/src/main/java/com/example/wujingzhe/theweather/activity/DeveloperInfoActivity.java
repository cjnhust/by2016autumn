package com.example.wujingzhe.theweather.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wujingzhe.theweather.R;

public class DeveloperInfoActivity extends BaseActivity {

    private Button developerInfoBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developer_info_layout);
        developerInfoBack= (Button) findViewById(R.id.developer_info_back);
        developerInfoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
