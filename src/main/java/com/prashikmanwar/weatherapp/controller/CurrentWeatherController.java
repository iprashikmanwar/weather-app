package com.prashikmanwar.weatherapp.controller;

import com.prashikmanwar.weatherapp.model.CurrentWeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/currentWeather/v1")
public class CurrentWeatherController {
    private final Logger logger = LoggerFactory.getLogger(CurrentWeatherController.class);

    CurrentWeatherController(){
    }

    @GetMapping(value = {"/{location}"}, produces = {"application/json"})
    public ResponseEntity<CurrentWeatherResponse> getCurrentWeatherByLocation(@PathVariable String location){
        try{
            CurrentWeatherResponse currentWeatherResponse = new RestTemplate().getForEntity("http://api.weatherstack.com/current?access_key=27c50212cc3694b06738080e84844a3f&query=Pune",CurrentWeatherResponse.class).getBody();//currentWeatherService.requestCurrentWeather();
            return ResponseEntity.ok(currentWeatherResponse);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }
}
