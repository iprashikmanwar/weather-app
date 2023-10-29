package com.prashikmanwar.weatherapp.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record Location(
    @NotEmpty
    String name,
    String country,
    String region,
    String lat,
    String lon,
    String timezone_id,
    String localtime,
    Integer localtime_epoch,
    String utc_offset){
}