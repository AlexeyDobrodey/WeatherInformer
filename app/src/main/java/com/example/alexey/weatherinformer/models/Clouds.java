package com.example.alexey.weatherinformer.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Clouds implements Serializable{
    private static final long serialVersionUID = 777L;
    private int all;
}
