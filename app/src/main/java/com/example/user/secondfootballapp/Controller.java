package com.example.user.secondfootballapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    static final String BASE_URL = "http://amopizza.ru/";

    public static Logger log = LoggerFactory.getLogger(Controller.class);
    public static FootballApi getApi() {
//    public static Controller getApi() {

//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(5, TimeUnit.MINUTES)
//                .readTimeout(5, TimeUnit.MINUTES)
//                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                .build();


        FootballApi testApi = retrofit.create(FootballApi.class);
//        Controller testApi = retrofit.create(Controller.class);
        return testApi;
    }

}
