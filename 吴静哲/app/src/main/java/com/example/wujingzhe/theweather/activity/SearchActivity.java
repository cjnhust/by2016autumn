package com.example.wujingzhe.theweather.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wujingzhe.theweather.R;
import com.example.wujingzhe.theweather.util.Handle;
import com.example.wujingzhe.theweather.util.HttpCallbackListener;
import com.example.wujingzhe.theweather.util.HttpUtil;

public class SearchActivity extends BaseActivity implements View.OnClickListener{

    private Button back;
    private Button search;
    private Button location;
    private EditText searchBox;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        back= (Button) findViewById(R.id.back);
        search= (Button) findViewById(R.id.search_button);
        location= (Button) findViewById(R.id.location);
        searchBox= (EditText) findViewById(R.id.search_box);

        back.setOnClickListener(this);
        search.setOnClickListener(this);
        location.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                this.finish();
                break;
            case R.id.search_button:
                showProgressDialog();
                String inputText=searchBox.getText().toString();
                String address="http://api.map.baidu.com/telematics/v3/weather?location="
                        +inputText+"&output=json&ak=1hpsNcYGhaHVgcxa64aGBGDCaPzxEH7b&mcode="
                        +"08:61:5A:97:AA:83:DF:D8:84:02:5B:2C:30:07:E9:5C:E2:E4:DC:90"
                        +";com.example.wujingzhe";
                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        final String count0 = Handle.handleWeatherResponse0(SearchActivity.this,response);
                        closeProgressDialog();
                        Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                        intent.putExtra("weather_info",count0);//count0是简略信息
                        intent.putExtra("from_search_activity",true);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                break;
            case R.id.location:
                //
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
}
