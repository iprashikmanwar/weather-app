package com.prashikmanwar.weatherapp.controller;

import com.prashikmanwar.weatherapp.model.CurrentWeatherResponse;
import com.prashikmanwar.weatherapp.service.CurrentWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currentWeather")
public class CurrentWeatherController {
    private final Logger logger = LoggerFactory.getLogger(CurrentWeatherController.class);
    private final CurrentWeatherService currentWeatherService;

    CurrentWeatherController(CurrentWeatherService currentWeatherService){
        this.currentWeatherService = currentWeatherService;
    }

    @GetMapping(value = {"/{location}"}, produces = {"application/json"})
    public ResponseEntity<CurrentWeatherResponse> getCurrentWeatherByLocation(@PathVariable String location){
        try{
            CurrentWeatherResponse currentWeatherResponse = currentWeatherService.requestCurrentWeather();
            return ResponseEntity.ok(currentWeatherResponse);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }
}
