package com.zc.okhttp.okhttplib;


import android.util.Log;

import okhttp3.Call;
import okhttp3.Response;

public abstract class CallBackUtil<T> {

    public void onProgress(float progress, long total) {
    }

    public void onError(final Call call, final Exception e) {
        Log.i("TAG", "onError: Exception "+e.toString());
        onFailure(call, e);
    }


    public void onSuccess(Call call, Response response) {
        final T obj = onParseResponse(call, response);
        onResponse(obj);
    }


    /**
     * 解析response，执行在子线程
     */
    public abstract T onParseResponse(Call call, Response response);

    /**
     * 访问网络失败后被调用，执行在UI线程
     */
    public abstract void onFailure(Call call, Exception e);

    /**
     * 访问网络成功后被调用，执行在UI线程
     */
    public abstract void onResponse(T response);


    public static abstract class CallBackDefault extends CallBackUtil<Response> {
        @Override
        public Response onParseResponse(Call call, Response response) {
            return response;
        }
    }

    public static abstract class CallBackString extends CallBackUtil<String> {
        @Override
        public String onParseResponse(Call call, Response response) {
            return response2String(response);
        }
    }

    //只关心正确响应，不管错误
    public static abstract class CallBackStringResponse extends CallBackUtil<String> {
        @Override
        public void onFailure(Call call, Exception e) {
            System.out.println("没有网络时会报什么异常呢？？");
            e.printStackTrace();
        }

        @Override
        public String onParseResponse(Call call, Response response) {
            return response2String(response);
        }
    }

    public String response2String(Response response) {
        String result="";
        try {
            if (response.code() == 200) {
                result = response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.d("gh0st",response.toString());
        } catch (Exception e) {
        }
        return result;
    }
}
