package com.example.alexey.weatherinformer.models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Main implements Serializable{
    private static final long serialVersionUID = 777L;
    private float temp;
    private float pressure;
    private float humidity;
    @SerializedName("temp_min")
    private float minTemp;
    @SerializedName("temp_max")
    private float maxTemp;
}
