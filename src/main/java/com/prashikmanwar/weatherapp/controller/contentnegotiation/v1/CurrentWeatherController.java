package com.prashikmanwar.weatherapp.controller.contentnegotiation.v1;

import com.prashikmanwar.weatherapp.configuration.WeatherAppProperties;
import com.prashikmanwar.weatherapp.model.CurrentWeatherResponse;
import com.prashikmanwar.weatherapp.repository.CurrentWeatherCollectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/currentWeather")
public class CurrentWeatherController {
    private final Logger logger = LoggerFactory.getLogger(CurrentWeatherController.class);
    private final CurrentWeatherCollectionRepository repository;

    /*@Value("${weatherStack.basePath: www.google.com}")
    //www.google.com - is a default value provided for the parameter weatherStack.basePath i.e. if not provided google.com will be picked
    private String WEATHERSTACK_URL;*/
    private final WeatherAppProperties weatherAppProperties;

    CurrentWeatherController(CurrentWeatherCollectionRepository repository, WeatherAppProperties weatherAppProperties) {
        this.repository = repository;
        this.weatherAppProperties = weatherAppProperties;
    }

    @GetMapping(value = {"/{location}"}, produces = {"application/json"})
    public ResponseEntity<CurrentWeatherResponse> getCurrentWeatherByLocation(@PathVariable String location) {
        try {
            logger.info("Request received for getting weather at {}", location);
            CurrentWeatherResponse currentWeatherResponse = new RestTemplate().getForEntity(weatherAppProperties.basePath() + location, CurrentWeatherResponse.class).getBody();
            logger.debug("Response received from external service for {} - {}", currentWeatherResponse.location().name(), currentWeatherResponse.current().weather_descriptions());
            repository.save(currentWeatherResponse);
            return ResponseEntity.ok(currentWeatherResponse);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            logger.info("Request completed for {}", location);
        }
    }
}
