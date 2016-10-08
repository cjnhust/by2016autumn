package com.example.doge.weather.util;

import java.io.InputStream;

/**
 * Created by Doge on 2016/10/4.
 */
public interface HttpCallbackListener {
    void onFinish(InputStream in);

    void onError(Exception e);
}
