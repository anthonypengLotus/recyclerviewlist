package com.example.myapplication.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject @JvmOverloads constructor(val shopService: ShopService
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        var request:Request  = chain.request();

        var response:Response  = chain.proceed(request);

        Log.d("TAG", "response.code=" + response.code);

        //根据和服务端的约定判断token过期
        if (isTokenExpired(response)) {
            Log.d("TAG", "自动刷新Token,然后重新请求数据");
            //同步请求方式，获取最新的Token
            var newToken = getNewToken();
            //使用新的Token，创建新的请求
            var newRequest:Request  = chain.request()
                .newBuilder()
                .header("Authorization", newToken)
                .build();
            //重新请求
            return chain.proceed(newRequest);
        }

        return response;

    }

    /**
     * 根据Response，判断Token是否失效
     * @param response
     * @return
     */

    private fun  isTokenExpired(response:Response):Boolean {
        if (response.code == 301) {
            return true;
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     * @return

     */

    private  fun getNewToken():String {
//        return shopService.refresh("").headers()["x-amzn-Remapped-authorization"] ?: ""
        return ""
    }

}