package com.example.alexey.weatherinformer.models;

import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class Wind implements Serializable{
    private static final long serialVersionUID = 777L;
    private float speed;
    private float deg;
}
