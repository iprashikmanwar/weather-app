package com.prashikmanwar.weatherapp.repository;

import com.prashikmanwar.weatherapp.model.Current;
import com.prashikmanwar.weatherapp.model.CurrentWeatherResponse;
import com.prashikmanwar.weatherapp.model.Location;
import com.prashikmanwar.weatherapp.model.RequestInfo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/***
 * Not a Spring-Data Repository => not talking to the database
 * This will be used to keep the weather details in memory to work with them later in our application
 */
@Repository
public class CurrentWeatherCollectionRepository {
    private final List<CurrentWeatherResponse> currentWeatherResponseList = new ArrayList<>();

    CurrentWeatherCollectionRepository() {
    }

    @PostConstruct
    public void init() {
        CurrentWeatherResponse currentWeatherResponse = new CurrentWeatherResponse(
                new RequestInfo("City", "Pune, India", "en", "m"),
                new Location("Pune", "India", "Maharashtra", "18.533", "73.867", "Asia/Kolkata", "2023-10-28 12:51", 1698497460, "5.50"),
                new Current("07:21 AM", 29, 113, new String[]{"https://cdn.worldweatheronline.com/images/wsymbols01_png_64/wsymbol_0001_sunny.png"}, new String[]{"Sunny"}, 11, 112, "ESE", 1013, 0, 30, 4, 28, 7, 10));
        currentWeatherResponseList.add(currentWeatherResponse);
    }

    public List<CurrentWeatherResponse> findAll() {
        return currentWeatherResponseList;
    }

    public Optional<CurrentWeatherResponse> findByPlace(String name) {
        return currentWeatherResponseList.stream().filter(c -> c.location().name().equals(name)).findFirst();
    }

    public void save(CurrentWeatherResponse response) {
        currentWeatherResponseList.removeIf(c -> c.location().name().equals(response.location().name()));
        currentWeatherResponseList.add(response);
    }

    public boolean existByLocation(String location) {
        return currentWeatherResponseList.stream().filter(c -> c.location().name().equals(location)).count() >= 1;
    }

    public void deleteByLocation(String location) {
        currentWeatherResponseList.removeIf(c -> c.location().name().equals(location));
    }
}
