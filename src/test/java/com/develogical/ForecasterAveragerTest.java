package com.develogical;

import com.teamoptimization.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.DayOfWeek;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ForecasterAveragerTest {
    @Test
    public void forecasterTest() throws IOException {
        Forecaster forecaster1 = mock(Forecaster.class);
        Forecaster forecaster2 = mock(Forecaster.class);

        Mockito.when(forecaster1.forecast(DayOfWeek.THURSDAY, "Oxford"))
                .thenReturn(new Forecaster.Forecast(12, 18, "sunny"));
        Mockito.when(forecaster2.forecast(DayOfWeek.THURSDAY, "Oxford"))
                .thenReturn(new Forecaster.Forecast(5, 15, "sunny"));

        Forecaster forecaster = new AveragerForecaster(forecaster1, forecaster2);
        Forecaster.Forecast forecast = forecaster.forecast(DayOfWeek.THURSDAY, "Oxford");
        assertEquals(forecast.minTemp, 8);
        assertEquals(forecast.maxTemp, 16);
    }
}
