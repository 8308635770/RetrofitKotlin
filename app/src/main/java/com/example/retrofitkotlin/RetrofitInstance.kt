package com.example.retrofitkotlin

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class RetrofitInstance {

    companion object{
        fun getRetrofit():Retrofit{



            val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getLoggingIntercepter())
                    .build()

            return retrofit;

        }

        fun getLoggingIntercepter():OkHttpClient{

            val loggingInterceptor = HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            val okHttpClient =  OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

            return okHttpClient
        }


    }


}