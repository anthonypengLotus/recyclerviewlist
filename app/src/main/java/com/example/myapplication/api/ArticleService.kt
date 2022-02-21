package com.example.myapplication.api



import com.example.myapplication.securty.TrustAllCerts
import com.example.myapplication.securty.TrustAllHostnameVerifier
import com.example.myapplication.data.response.ArticleListResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory

interface ArticleService {

    @Headers("Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjE2MmVkZWI1ZDljMmU5YmNjODM1Yjc1YWU0YmMxZDJhODZiN2IxMTU5MTFiZGJiNjFkZDEzOGFiNDdhMzlmZDIyODlmMjhkYjRmNTY4NDU3In0.eyJhdWQiOiIzIiwianRpIjoiMTYyZWRlYjVkOWMyZTliY2M4MzViNzVhZTRiYzFkMmE4NmI3YjExNTkxMWJkYmI2MWRkMTM4YWI0N2EzOWZkMjI4OWYyOGRiNGY1Njg0NTciLCJpYXQiOjE1ODQ1MTU0ODksIm5iZiI6MTU4NDUxNTQ4OSwiZXhwIjoxNjc5MTIzNDg5LCJzdWIiOiIzMDYzMjUyIiwic2NvcGVzIjpbIioiXX0.QaYfFKRQUv5h-GlxOlzAFxkJfL5pYrLo0xWueCyqu69vG-75wLssdPG9UMpl-l8ENHU4nE27RClLMYWb73cn5wmMotKd-2OYsRi01DJcxIzEXAwgi1HmAruPk93FWxHAbndYWOXDkV0AtnHx869o3SwFVd1JzuF0zc98M2DsIdeAUp1I5sM8XjW9M4BwSEUmwTDr76Z2ezDEgTXKsILw-HUd1GfiG8ianxgarVrGNRreQa-ZJHgAor3IERSgx8Rup9OviO_xhXQc4UuNhgjnS6Y8DzLtFdeJKeqh1v6jAfiAYkg3QSSpo93KTR6hlrOGWgUkdjQWe2Xc1W9aIPd7g8Ruc4cjILC1qujAH3tBI_mFy2xPINZ5mG5AInm26Ypy2FHHZkasHcxmS6xBMpWPUjMYwq9rg5mY-crQeTRjvbq6LL7sLXGMos4Iqf2H58YjiaiQrswfhK5_wnpW_a7yx2umVWncvPVT9Qjt2nKjBbbXJckOjSc6Z97-c3QtTsH5chGudUlEbTYirk-hU-BL3uIERnGIxzjSXx_VVFTS9ajeDdhexwVCmN9bHgy5yXzpz3sjDWfVJSBaFNgp94ErcNskiMyyYSyK21CuM2ZeIp1HfskN7Azy1py9OSqBu7GSzLfAx9G7GhmCNdQ0dWSl9PGJOIUb1fRRVZoBd4RHkUU")
    @GET("{path}")
    suspend fun productList(@Path("path") path:String): ArticleListResponse


    companion object {
        fun createSSLSocketFactory(): SSLSocketFactory {
            var ssfFactory: SSLSocketFactory

                val sc: SSLContext = SSLContext.getInstance("TLS")
                sc.init(null, arrayOf(TrustAllCerts()), SecureRandom())
                ssfFactory = sc.getSocketFactory()
            return ssfFactory
        }


        private const val BASE_URL = "https://app.theasianparent.com/api/v2/category/feed/"

        fun create(): ArticleService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
//                .sslSocketFactory(createSSLSocketFactory(),com.example.myapplication.securty.TrustAllCerts())
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES) // write timeout
                .readTimeout(2, TimeUnit.MINUTES)
                .hostnameVerifier(TrustAllHostnameVerifier())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticleService::class.java)
        }
    }
}
