package com.zc.okhttp.okhttplib;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpUtil {
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";

    /**
     * get请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpGet(String url, CallBackUtil callBack) {
        okHttpGet(url, null, null, callBack);
    }

    /**
     * get请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpGet(String url, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpGet(url, paramsMap, null, callBack);
    }

    /**
     * get请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpGet(String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_GET, url, paramsMap, headerMap, callBack).execute();
    }

    /**
     * post请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPost(String url, CallBackUtil callBack) {
        okHttpPost(url, null, callBack);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPost(String url, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpPost(url, paramsMap, null, callBack);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPost(String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST, url, paramsMap, headerMap, callBack).execute();
    }

    /**
     * post请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPut(String url, CallBackUtil callBack) {
        okHttpPut(url, null, callBack);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPut(String url, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpPut(url, paramsMap, null, callBack);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPut(String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_PUT, url, paramsMap, headerMap, callBack).execute();
    }

    /**
     * post请求
     *
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpDelete(String url, CallBackUtil callBack) {
        okHttpDelete(url, null, callBack);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpDelete(String url, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpDelete(url, paramsMap, null, callBack);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpDelete(String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_DELETE, url, paramsMap, headerMap, callBack).execute();
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPostJson(String url, String jsonStr, CallBackUtil callBack) {
        okHttpPostJson(url, jsonStr, null, callBack);
    }

    /**
     * post请求，可以传递参数
     *
     * @param url：url
     * @param jsonStr：json格式的键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPostJson(String url, String jsonStr, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST, url, jsonStr, headerMap, callBack).execute();
    }

    public static void okHttpUploadFile(String url, File file, String fileKey, String fileType, CallBackUtil callBack) {
        okHttpUploadFile(url, file, fileKey, fileType, null, callBack);
    }

    public static void okHttpUploadFile(String url, File file, String fileKey, String fileType, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpUploadFile(url, file, fileKey, fileType, paramsMap, null, callBack);
    }

    public static void okHttpUploadFile(String url, File file, String fileKey, String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST, url, paramsMap, file, fileKey, fileType, headerMap, callBack).execute();
    }

    public static void okHttpUploadListFile(String url, List<File> fileList, String fileKey, String fileType, CallBackUtil callBack) {
        okHttpUploadListFile(url, null, fileList, fileKey, fileType, callBack);
    }

    public static void okHttpUploadListFile(String url, Map<String, String> paramsMap, List<File> fileList, String fileKey, String fileType, CallBackUtil callBack) {
        okHttpUploadListFile(url, paramsMap, fileList, fileKey, fileType, null, callBack);
    }

    public static void okHttpUploadListFile(String url, Map<String, String> paramsMap, List<File> fileList, String fileKey, String fileType, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST, url, paramsMap, fileList, fileKey, fileType, headerMap, callBack).execute();
    }

    public static void okHttpUploadMapFile(String url, Map<String, File> fileMap, String fileType, CallBackUtil callBack) {
        okHttpUploadMapFile(url, fileMap, fileType, null, callBack);
    }

    public static void okHttpUploadMapFile(String url, Map<String, File> fileMap, String fileType, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpUploadMapFile(url, fileMap, fileType, paramsMap, null, callBack);
    }

    public static void okHttpUploadMapFile(String url, Map<String, File> fileMap, String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST, url, paramsMap, fileMap, fileType, headerMap, callBack).execute();
    }

    public static void postLongTimeOut(String url, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        System.out.println(url);
        //创建OkhttpClient
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(60 * 1000, TimeUnit.SECONDS) //连接超时
                .readTimeout(60 * 1000, TimeUnit.SECONDS) //读取超时
                .writeTimeout(60 * 1000, TimeUnit.SECONDS) //写超时
                .build();
        //创建RequestBuilder
        Request.Builder requestBuilder = new Request.Builder();
        //设置参数
        FormBody.Builder formBody = new FormBody.Builder();
        if (paramsMap != null) {
            for (String key : paramsMap.keySet()) {
                formBody.add(key, paramsMap.get(key));
            }
        }
        if (headerMap != null) {
            for (String key : headerMap.keySet()) {
                requestBuilder.addHeader(key, headerMap.get(key));
            }
        }
        requestBuilder.post(formBody.build());
        requestBuilder.url(url);
        httpClient.newCall(requestBuilder.build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (callBack != null) callBack.onError(call, e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (callBack != null) callBack.onSuccess(call, response);
            }
        });
    }

    public static void okHttpPostJsonFile(String url, String json, Map<String, String> params, Map<String, File> fileMap, CallBackUtil callBack) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(60 * 1000, TimeUnit.SECONDS) //连接超时
                .readTimeout(60 * 1000, TimeUnit.SECONDS) //读取超时
                .writeTimeout(60 * 1000, TimeUnit.SECONDS) //写超时
                .build();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        //添加文件
        for (String key : fileMap.keySet()) {
            System.out.println(fileMap.get(key).getName() + " " + fileMap.get(key).getAbsolutePath());
            builder.addFormDataPart(key, fileMap.get(key).getName(), RequestBody.create( MediaType.parse("multipart/form-data"),fileMap.get(key)));
        }
        //添加json
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        RequestBody requestBody = RequestBody.create(JSON,json );
        builder.addPart(requestBody);

        //添加Params
        for (String key : params.keySet()) {
            builder.addFormDataPart(key, params.get(key));
        }
        Request.Builder requestBuild = new Request.Builder();
        requestBuild.post(builder.build());
        requestBuild.url(url);
        httpClient.newCall(requestBuild.build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                if (callBack != null) callBack.onError(call, e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (callBack != null) callBack.onSuccess(call, response);
            }
        });
    }

    private static String getMimeType(String filename) {
        FileNameMap filenameMap = URLConnection.getFileNameMap();
        String contentType = filenameMap.getContentTypeFor(filename);
        if (contentType == null) {
            contentType = "application/octet-stream"; //* exe,所有的可执行程序
        }
        return contentType;
    }
}
