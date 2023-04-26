package com.oocode;

import com.teamoptimization.*;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Example {
    public static void main(String[] args) throws IOException {
        Forecaster adapter = new NavyForecasterAdapter(LocalDate.now().getDayOfWeek(), new NavyForecastingClient(), new LocatorClient());
        Forecaster forecaster = new CachingForecasterImpl(adapter);
        MetOfficeForecasterClient.Forecast forecast = forecaster.forecast(DayOfWeek.THURSDAY, "Oxford");
        System.out.printf("forecaster: min=%s max=%s description=%s%n",
                forecast.minTemp, forecast.maxTemp, forecast.description);
        forecast = forecaster.forecast(DayOfWeek.THURSDAY, "Oxford");
        System.out.printf("forecaster: min=%s max=%s description=%s%n",
                forecast.minTemp, forecast.maxTemp, forecast.description);
        forecast = forecaster.forecast(DayOfWeek.THURSDAY, "Oxford");
        System.out.printf("forecaster: min=%s max=%s description=%s%n",
                forecast.minTemp, forecast.maxTemp, forecast.description);
    }

    private static void forecast(String day, String place) throws IOException {
        int dayNumber = DayOfWeek.valueOf(day.toUpperCase()).getValue();
        LocatorClient.Location location = new LocatorClient().locationOf(place);
        MetOfficeForecasterClient forecasterClient = new MetOfficeForecasterClient();
        MetOfficeForecasterClient.Forecast forecast =
                forecasterClient.forecast(dayNumber, location.latitude, location.longitude);
        System.out.printf("forecaster: %s day=%s min=%s max=%s description=%s%n",
                place, day, forecast.minTemp, forecast.maxTemp, forecast.description);
    }

    private static void forecast2(String day, String place) throws IOException {
        NavyForecastingClient forecasting = new NavyForecastingClient();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day.toUpperCase());
        int minTemp = forecasting.min(dayOfWeek, place);
        int maxTemp = forecasting.max(dayOfWeek, place);
        String description = forecasting.desc(dayOfWeek, place);
        System.out.printf("forecaster: %s day=%s min=%s max=%s description=%s%n",
                place, day, minTemp, maxTemp, description);    }
}
