package com.jkalebe.androidjeremiaskalebe.core.api

import com.google.gson.GsonBuilder
import com.jkalebe.androidjeremiaskalebe.utils.Constants.FAKE_URL_API
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient() {

    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }


    fun provideConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return GsonConverterFactory.create(gson)
    }


    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FAKE_URL_API)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
    fun <T> provideService(retrofit: Retrofit, type: Class<T>): T = retrofit.create(type)

}