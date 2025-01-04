package com.example.dfhackthon.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private lateinit var retrofit: Retrofit
    private lateinit var REST_CLIENT: AuthApiInterface

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

//    val apiService: AuthApiInterface by lazy {
//        Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(AuthApiInterface::class.java)
//
//    }

    fun getApi(): AuthApiInterface {
        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        REST_CLIENT = retrofit.create(AuthApiInterface::class.java)
        return REST_CLIENT
    }

}
