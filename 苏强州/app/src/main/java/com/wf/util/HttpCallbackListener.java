package com.wf.util;

public interface HttpCallbackListener {
void onFininsh(String response);
void onError(Exception e);
}
