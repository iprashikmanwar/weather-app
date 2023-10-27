package com.prashikmanwar.weatherapp.model;

public record RequestInfo(
    String type,
    String query,
    String language,
    String unit
){}