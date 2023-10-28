package com.prashikmanwar.weatherapp.controller;

import com.prashikmanwar.weatherapp.model.Current;
import com.prashikmanwar.weatherapp.model.CurrentWeatherResponse;
import com.prashikmanwar.weatherapp.model.Location;
import com.prashikmanwar.weatherapp.model.RequestInfo;
import com.prashikmanwar.weatherapp.repository.CurrentWeatherCollectionRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/currentWeather/v1")
public class CurrentWeatherController {
    private final Logger logger = LoggerFactory.getLogger(CurrentWeatherController.class);
    private final CurrentWeatherCollectionRepository repository;

    CurrentWeatherController(CurrentWeatherCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = {"/{location}"}, produces = {"application/json"})
    public ResponseEntity<CurrentWeatherResponse> getCurrentWeatherByLocation(@PathVariable String location) {
        try {
            CurrentWeatherResponse currentWeatherResponse = new RestTemplate().getForEntity("http://api.weatherstack.com/current?access_key=27c50212cc3694b06738080e84844a3f&query=Pune", CurrentWeatherResponse.class).getBody();
            return ResponseEntity.ok(currentWeatherResponse);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @GetMapping("")
    public List<CurrentWeatherResponse> findAll() {
        return repository.findAll();
    }
}
