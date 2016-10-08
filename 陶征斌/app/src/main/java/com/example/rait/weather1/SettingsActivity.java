package com.example.rait.weather1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Rait on 2016/10/7.
 */

public class SettingsActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);//注意为“R.layout.activity_second”
    }
}
