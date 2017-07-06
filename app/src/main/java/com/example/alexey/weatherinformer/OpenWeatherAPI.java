package com.example.alexey.weatherinformer;

import com.example.alexey.weatherinformer.models.ListWeatherObject;
import com.example.alexey.weatherinformer.models.WeatherObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexey on 7/5/17.
 */

public interface OpenWeatherAPI {
    @GET("data/2.5/group")
    Call<ListWeatherObject> getListWeatherObjects(@Query("id") String idCountries, @Query("units")String metric, @Query("appid") String appid);
}
