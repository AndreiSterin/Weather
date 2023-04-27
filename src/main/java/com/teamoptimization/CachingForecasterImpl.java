package com.teamoptimization;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CachingForecasterImpl implements Forecaster {
    Forecaster forecaster;
    Map<Integer, Forecaster.Forecast> cache = new HashMap<>();

    public CachingForecasterImpl(Forecaster forecaster) {
        this.forecaster = forecaster;
    };
    @Override
    public Forecaster.Forecast forecast(DayOfWeek day, String place) {
        return cache.computeIfAbsent(Objects.hash(day, place), i -> forecaster.forecast(day, place));
    }

}
