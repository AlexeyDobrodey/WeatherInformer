package com.example.alexey.weatherinformer.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@ToString
public class Coord implements Serializable{
    private static final long serialVersionUID = 777L;
    private float lon;
    private float lat;
}
