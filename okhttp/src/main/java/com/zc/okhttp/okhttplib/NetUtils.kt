package com.zc.okhttp.okhttplib

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.IOException


fun String.httpGet(block: ((String) -> Unit)) {
    OkhttpUtil.okHttpGet(this, null, null, object : CallBackUtil.CallBackStringResponse() {
        override fun onResponse(response: String?) {

            block(response+"")
        }

        override fun onFailure(call: Call?, e: Exception?) {
            block(" {\n" +
                    "\"Status\": 0,\n" +
                    "\"Message\": \"失败\",\n" +
                    "\"Content\": [],\n" +
                    "\"RowCount\": 0\n" +
                    "}")
        }
    })
}

fun String.httpGet(param: Map<String, String>, block: ((String) -> Unit)) {
    OkhttpUtil.okHttpGet(this, param, null, object : CallBackUtil.CallBackStringResponse() {
        override fun onResponse(response: String) {
            block(response)
        }
    })
}

//fun String.httpGet(
//    param: Map<String, String>,
//    headerMap: Map<String, String>,
//    block: (String) -> Unit
//) {
//    OkhttpUtil.okHttpGet(this, param, headerMap, object : CallBackUtil.CallBackStringResponse() {
//        override fun onResponse(response: String) {
//            block(response)
//        }
//    })
//}

//fun String.httpPost(param: Map<String, String>, block: ((String) -> Unit)) {
//    val headMap = mapOf("token" to MVUtils.getString(CommConstVal.MMKV_KEY_TOKEN_,"").toString())
//    OkhttpUtil.okHttpPost(this, param, headMap,object : CallBackUtil.CallBackStringResponse() {
//        override fun onResponse(response: String) {
//            block(response)
//        }
//
//        override fun onFailure(call: Call?, e: java.lang.Exception?) {
//            super.onFailure(call, e)
//            block("{\"isSuccess\":false,\"errorCode\":\"网络错误\",\"data\":null}")
//        }
//    })
//
//}

//提交body 参数 =》jsonObject
fun String.postObject(jsonObject: JSONObject, headers:Map<String,String>?,block: ((String) -> Unit)) {
    var JSON1 = MediaType.parse("application/json; charset=utf-8");
    val okHttpClient = OkHttpClient.Builder();
    val body: RequestBody =
        RequestBody.create(JSON1, java.lang.String.valueOf(jsonObject))
    val logging = HttpLoggingInterceptor { message: String? ->
        Log.e(
            "okhttp response json ",
            message+""
        )
    }
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    okHttpClient.addInterceptor(logging)

    var requestBuild =  Request.Builder()
        .url(this )
//        .header("token",MVUtils.getString(CommConstVal.MMKV_KEY_TOKEN_,"").toString())
        .post(body)
    if (headers!=null) {
        for (key in headers!!.keys) {
            headers[key]?.let { requestBuild.addHeader(key, it) }
        }
    }

    var request=requestBuild. build()

    val call: Call = okHttpClient.build().newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
//            Log.i("postObject", "onFailure: e $e ,call $call")
            block("{\"isSuccess\":false,\"errorCode\":\"网络错误\",\"data\":null}")
        }

        override fun onResponse(call: Call, response: Response) {
//            Log.i("postObject", " onResponse: Response ${response.body()?.string()} ,call ${call}")
            block("${response.body()?.string()}")

        }

    })

}

fun String.httpPost(
    param: Map<String, String>,
    headerMap: Map<String, String>,
    block: ((String) -> Unit)
) {
    OkhttpUtil.okHttpPost(this, param, headerMap, object : CallBackUtil.CallBackStringResponse() {
        override fun onResponse(response: String) {
            block(response)
        }
    })
}

fun String.httpPostJson(
    jsonStr: String, block: ((String) -> Unit)
) {

    OkhttpUtil.okHttpPostJson(
        this,
        jsonStr,
        null,
        object : CallBackUtil.CallBackStringResponse() {
            override fun onResponse(response: String) {
                block(response)
            }
        })
}
