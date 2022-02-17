package com.example.myapplication.api



import com.example.myapplication.data.database.ArticleEntity
import com.example.myapplication.data.response.DataResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import javax.inject.Inject

interface ShopService {

    @Headers("Client:android-app")
    @POST("auth/refresh")
    suspend fun refresh(@Header("Refresh-Authorization") token:String): Response<Any?>


    @GET("article/navigation")
    suspend fun navigation(@Header("Authorization") token:String): DataResponse
//
//
    @GET("https://app.theasianparent.com/api/v2/category/feed/relationship")
    suspend fun productList(): ArticleEntity
//
//
//    @GET("product/view")
//    suspend fun productDetail(@Query("teamCode") teamCode: String): TeamStandingResponse



    companion object {
        private const val BASE_URL = "https://api.mwellph.com/"

        fun create(): ShopService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)

                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ShopService::class.java)
        }
    }
}