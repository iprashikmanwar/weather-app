package com.prashikmanwar.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentWeatherResponse implements WeatherStackResponse {
    private RequestInfo request;
    private Location location;
    private Current current;
}