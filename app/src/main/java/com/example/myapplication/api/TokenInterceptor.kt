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

        if (isTokenExpired(response)) {
            var newToken = getNewToken();
            var newRequest:Request  = chain.request()
                .newBuilder()
                .header("Authorization", newToken)
                .build();
            return chain.proceed(newRequest);
        }

        return response;

    }


    private fun  isTokenExpired(response:Response):Boolean {
        if (response.code == 301) {
            return true;
        }
        return false;
    }



    private  fun getNewToken():String {
//        return shopService.refresh("").headers()["x-amzn-Remapped-authorization"] ?: ""
        return ""
    }

}