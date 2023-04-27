package com.teamoptimization;

import java.io.IOException;;
import java.time.DayOfWeek;

public interface Forecaster {
    public static class Forecast {
        public int minTemp;
        public int maxTemp;
        public String description;

        public Forecast(int minTemp, int maxTemp, String description) {
            this.minTemp = minTemp;
            this.maxTemp = maxTemp;
            this.description = description;
        }

        public Forecast() {
        }

        @Override
        public String toString() {
            return "Forecast(" + minTemp + ", " + maxTemp + ", " + description + ")";
        }
    }
    Forecast forecast(DayOfWeek day, String place);
}
