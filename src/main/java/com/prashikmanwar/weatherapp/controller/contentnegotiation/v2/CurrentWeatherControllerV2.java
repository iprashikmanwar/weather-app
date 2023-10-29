package com.prashikmanwar.weatherapp.controller.contentnegotiation.v2;

import com.prashikmanwar.weatherapp.model.CurrentWeatherResponse;
import com.prashikmanwar.weatherapp.repository.CurrentWeatherCollectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v2/currentWeather")
public class CurrentWeatherControllerV2 {
    private final Logger logger = LoggerFactory.getLogger(CurrentWeatherControllerV2.class);
    private final CurrentWeatherCollectionRepository repository;

    CurrentWeatherControllerV2(CurrentWeatherCollectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/")
    public List<CurrentWeatherResponse> getCurrentWeatherfromList() {
        logger.info("Request received for getting All");
        return repository.findAll();
    }

    @GetMapping(value = "/{location}", produces = "application/json")
    public CurrentWeatherResponse getCurrentWeatherByLocationfromList(@PathVariable String location) {
        logger.info("Request received for getting weather at: {}", location);
        return repository.findByPlace(location).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!"));
    }

}
