package com.develogical;

import com.teamoptimization.*;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ForecasterTest {
//    @Test
//    public void forecasterTest() throws IOException {
//        MetOfficeForecasterClient forecasterClient = mock(MetOfficeForecasterClient.class);
//        LocatorClient locatorClient = mock(LocatorClient.class);
//        Mockito.when(locatorClient.locationOf("Oxford"))
//                .thenReturn(new LocatorClient.Location(BigDecimal.valueOf(51.752022), BigDecimal.valueOf(-1.257677)));
//        Mockito.when(forecasterClient.forecast(1, BigDecimal.valueOf(51.752022), BigDecimal.valueOf(-1.257677)))
//                .thenReturn(new MetOfficeForecasterClient.Forecast(12, 18, "sunny"));
//
//        Forecaster forecaster = new ForecasterImpl(DayOfWeek.WEDNESDAY, forecasterClient, locatorClient);
//        MetOfficeForecasterClient.Forecast forecast = forecaster.forecast(DayOfWeek.THURSDAY, "Oxford");
//        assertEquals(forecast.minTemp, 12);
//        assertEquals(forecast.maxTemp, 18);
//        assertEquals(forecast.description, "sunny");
//    }
    @Test
    public void asksDelegateForForecastIfNeverSeenBefore() throws IOException {
        Forecaster delegate = mock(Forecaster.class);
        Mockito.when(delegate.forecast(DayOfWeek.THURSDAY, "Oxford"))
                .thenReturn(new MetOfficeForecasterClient.Forecast(12, 18, "sunny"));

        Forecaster forecaster = new CachingForecasterImpl(delegate);
        MetOfficeForecasterClient.Forecast forecast = forecaster.forecast(DayOfWeek.THURSDAY, "Oxford");
        assertEquals(forecast.minTemp, 12);
        assertEquals(forecast.maxTemp, 18);
        assertEquals(forecast.description, "sunny");

        verify(delegate).forecast(DayOfWeek.THURSDAY, "Oxford");
    }
}
