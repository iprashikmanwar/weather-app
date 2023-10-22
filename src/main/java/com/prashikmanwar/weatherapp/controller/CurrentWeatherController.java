package com.prashikmanwar.weatherapp.controller;

import com.prashikmanwar.weatherapp.model.CurrentWeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currentWeather")
public class CurrentWeatherController {
    private final Logger logger = LoggerFactory.getLogger(CurrentWeatherController.class);

    @GetMapping("/{location}")
    public CurrentWeatherResponse getCurrentWeatherByLocation(@PathVariable String location){
        return new CurrentWeatherResponse("ARequest","Location","Currently");
    }
}
