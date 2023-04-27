package com.teamoptimization;

import java.io.IOException;
import java.time.DayOfWeek;

public class ForecasterAdapter implements Forecaster {
    MetOfficeForecasterClient forecasterClient;
    LocatorClient client;

    DayOfWeek today;
    public ForecasterAdapter(DayOfWeek today, MetOfficeForecasterClient forecasterClient, LocatorClient client) {
        this.forecasterClient = forecasterClient;
        this.client = client;
        this.today = today;
    };
    @Override
    public Forecaster.Forecast forecast(DayOfWeek day, String place) {
        LocatorClient.Location location = null;
        try {
            location = client.locationOf(place);
            int dayDifference = (day.getValue() - today.getValue()) % 7;
            MetOfficeForecasterClient.Forecast forecast = forecasterClient.forecast(dayDifference, location.latitude, location.longitude);
            return new Forecaster.Forecast(forecast.minTemp, forecast.maxTemp, forecast.description);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
