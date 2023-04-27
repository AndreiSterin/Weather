package com.teamoptimization;

import com.teamoptimization.Forecaster;

import java.time.DayOfWeek;

public class AveragerForecaster implements Forecaster {
    private final Forecaster forecaster1;
    private final Forecaster forecaster2;

    public AveragerForecaster(Forecaster forecaster1, Forecaster forecaster2) {
        this.forecaster1 = forecaster1;
        this.forecaster2 = forecaster2;
    }

    @Override
    public Forecaster.Forecast forecast(DayOfWeek day, String place) {
        Forecaster.Forecast forecast1 = forecaster1.forecast(day, place);
        Forecaster.Forecast forecast2 = forecaster2.forecast(day, place);

        int min = (forecast1.minTemp + forecast2.minTemp) / 2;
        int max = (forecast1.maxTemp + forecast2.maxTemp) / 2;

        return new Forecaster.Forecast(min, max, forecast1.description);
    }
}