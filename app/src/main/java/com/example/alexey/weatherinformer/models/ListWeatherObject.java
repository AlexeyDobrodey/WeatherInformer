package com.example.alexey.weatherinformer.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ListWeatherObject {
    private int cnt;
    @SerializedName("list")
    private List<WeatherObject> weatherObjects;
}
