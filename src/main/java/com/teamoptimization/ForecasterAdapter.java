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
    public MetOfficeForecasterClient.Forecast forecast(DayOfWeek day, String place) {
        LocatorClient.Location location = null;
        try {
            location = client.locationOf(place);
            int dayDifference = (day.getValue() - today.getValue()) % 7;
            return forecasterClient.forecast(dayDifference, location.latitude, location.longitude);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
