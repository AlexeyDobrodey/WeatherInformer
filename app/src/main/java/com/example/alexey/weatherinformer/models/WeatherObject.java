package com.example.alexey.weatherinformer.models;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class WeatherObject implements Serializable{
    private static final long serialVersionUID = 777L;

    private Coord coord;
    private List<Weather> weather;
    private Main main;

    private String base;
    private int visibility;

    private Wind wind;
    private Clouds clouds;

    private int dt;
    private Sys sys;
    private int id;
    private String name;
    private int cod;
}