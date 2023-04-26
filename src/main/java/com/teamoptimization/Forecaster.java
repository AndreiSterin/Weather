package com.teamoptimization;

import java.io.IOException;;
import java.time.DayOfWeek;

public interface Forecaster {
    MetOfficeForecasterClient.Forecast forecast(DayOfWeek day, String place) throws IOException;
}
