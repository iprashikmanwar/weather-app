package com.prashikmanwar.weatherapp.model;

public record Current(
    String observation_time,
    Integer temperature,
    Integer weather_code,
    String[] weather_icons,
    String[] weather_descriptions,
    Integer wind_speed,
    Integer wind_degree,
    String wind_dir,
    Integer pressure,
    Integer precip,
    Integer humidity,
    Integer cloudcover,
    Integer feelslike,
    Integer uv_index,
    Integer visibility
){}