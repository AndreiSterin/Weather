package com.teamoptimization;

import java.io.IOException;
import java.time.DayOfWeek;

public class NavyForecasterAdapter implements Forecaster {
    NavyForecastingClient forecasterClient;
    LocatorClient client;

    DayOfWeek today;
    public NavyForecasterAdapter(DayOfWeek today, NavyForecastingClient forecasterClient, LocatorClient client) {
        this.forecasterClient = forecasterClient;
        this.client = client;
        this.today = today;
    };
    @Override
    public MetOfficeForecasterClient.Forecast forecast(DayOfWeek day, String place) {
        try {
            return new MetOfficeForecasterClient.Forecast(forecasterClient.min(day,place),
                    forecasterClient.max(day,place), forecasterClient.desc(day,place));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
