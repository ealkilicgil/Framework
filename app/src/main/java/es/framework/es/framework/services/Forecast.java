package es.framework.es.framework.services;

import java.util.List;

/**
 * Created by 02481552 on 11.10.2015.
 */
public class Forecast {
    public HourlyForecast hourly;

    public class HourlyForecast{
        public List<HourData> data;

        public class HourData {
            public double temperature;
            public String icon;
        }
    }

}
