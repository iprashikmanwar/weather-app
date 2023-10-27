package com.prashikmanwar.weatherapp.model;

public record Location(
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