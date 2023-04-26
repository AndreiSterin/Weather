package com.teamoptimization;

import java.io.IOException;
import java.time.DayOfWeek;

public class ForecasterImpl implements Forecaster {
    MetOfficeForecasterClient forecasterClient;
    LocatorClient client;

    DayOfWeek today;
    public ForecasterImpl(DayOfWeek today, MetOfficeForecasterClient forecasterClient, LocatorClient client) {
        this.forecasterClient = forecasterClient;
        this.client = client;
        this.today = today;
    };
    @Override
    public MetOfficeForecasterClient.Forecast forecast(DayOfWeek day, String place) throws IOException {
        LocatorClient.Location location = client.locationOf(place);
        int dayDifference = (day.getValue() - today.getValue()) % 7;
        return forecasterClient.forecast(dayDifference, location.latitude, location.longitude);
    }
}
