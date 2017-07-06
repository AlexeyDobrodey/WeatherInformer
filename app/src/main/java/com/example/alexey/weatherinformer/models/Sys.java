package com.example.alexey.weatherinformer.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;



@Getter
@ToString
public class Sys implements Serializable{
    private static final long serialVersionUID = 777L;
    private int type;
    private int id;
    private float message;
    private String country;
    private int sunrise;
    private int sunset;
}
