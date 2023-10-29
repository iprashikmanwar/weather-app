package com.prashikmanwar.weatherapp.controller.contentnegotiation.v2;

import com.prashikmanwar.weatherapp.model.CurrentWeatherResponse;
import com.prashikmanwar.weatherapp.repository.CurrentWeatherCollectionRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


/***
 * When 2 application on the same machine try to use the resources from one to another(server, this one)
 * is we donot provide this annotation it will give an error of CORS policy
 * 'No-Access-Control-Allow-Origin' header is present
 * If CrossOrigin not specified = browser will block the access to server resources => watches it as an effort to security breach
 */
@RestController
@CrossOrigin
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void createDummyWeather(@Valid @RequestBody CurrentWeatherResponse weather) {    //@Valid validates the incoming requestBody object - if not won't even go inside the method(BAD_REQUEST)
        try {
            logger.info("Creation request received for {}", weather.location().name());
            repository.save(weather);
            logger.info("Request completed for {}", weather.location().name());
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)      //Everything is good - we are just not sending out anything in response
    @PutMapping(value = "/{location}", produces = "application/json")
    public void updateCurrentWeatherByLocationfromList(@RequestBody CurrentWeatherResponse weather, @PathVariable String location) {
        logger.info("Request received for updating weather at: {}", location);
        if (!repository.existByLocation(location)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
        }
        repository.save(weather);
        logger.info("Request completed for {}", weather.location().name());
    }

    @DeleteMapping(value = "/{location}")
    public void deleteCurrentWeatherByLocationfromList(@PathVariable String location) {
        logger.info("Request received for deleting weather at: {}", location);
        repository.deleteByLocation(location);
    }

}
