package com.example.alexey.weatherinformer.models;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;



@Getter
@ToString
public class Weather implements Serializable{
    private static final long serialVersionUID = 777L;
    private int id;
    private String main;
    private String description;
    private String icon;
}
