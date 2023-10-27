package com.prashikmanwar.weatherapp.model;

public record CurrentWeatherResponse(
    RequestInfo request,
    Location location,
    Current current){
}