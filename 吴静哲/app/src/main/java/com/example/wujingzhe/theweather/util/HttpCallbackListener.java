package com.example.wujingzhe.theweather.util;

/**
 * Created by wujingzhe on 2016/10/6.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
