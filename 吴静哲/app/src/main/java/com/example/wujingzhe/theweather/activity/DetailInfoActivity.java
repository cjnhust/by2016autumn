package com.example.wujingzhe.theweather.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wujingzhe.theweather.R;

public class DetailInfoActivity extends BaseActivity {

    private Button detailBack;
    private TextView titleText;
    private TextView detailInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        detailBack= (Button) findViewById(R.id.detail_back);
        titleText= (TextView) findViewById(R.id.title_text);
        detailInformation= (TextView) findViewById(R.id.detail_information);

        String detailInfo=getIntent().getStringExtra("detail_info");
        String cityName=getIntent().getStringExtra("first_city_name");

        detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailInfoActivity.this.finish();
            }
        });
        titleText.setText(cityName);
        detailInformation.setText(detailInfo);

    }
}
