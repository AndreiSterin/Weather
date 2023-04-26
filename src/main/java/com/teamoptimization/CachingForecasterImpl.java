package com.teamoptimization;

import java.io.IOException;
import java.time.DayOfWeek;

public class CachingForecasterImpl implements Forecaster {
    Forecaster forecaster;
    public CachingForecasterImpl(Forecaster forecaster) {
        this.forecaster = forecaster;
    };
    @Override
    public MetOfficeForecasterClient.Forecast forecast(DayOfWeek day, String place) throws IOException {
        return forecaster.forecast(day, place);
    }
}
