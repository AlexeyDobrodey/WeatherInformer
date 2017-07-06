package com.example.alexey.weatherinformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexey on 7/6/17.
 */

public class OpenWeatherController {

    public static final String URL = "http://api.openweathermap.org";
    public static final String URL_ICO = "http://openweathermap.org/img/w/";



    public static OpenWeatherAPI getAPIOpenWeather() {
         Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(URL)
                .build();

        return retrofit.create(OpenWeatherAPI.class);
    }
}
