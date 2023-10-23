package com.prashikmanwar.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestInfo {
    private String type;
    private String query;
    private String language;
    private String unit;
}