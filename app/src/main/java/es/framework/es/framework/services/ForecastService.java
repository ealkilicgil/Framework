package es.framework.es.framework.services;


import java.util.concurrent.Executors;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;



/**
 * Created by 02481552 on 11.10.2015.
 */
public class ForecastService {
    private static final String API_URL="https://api.forecast.io/";
    private static final String TAG ="Forecast Service" ;

    public interface WeatherService{
        @GET("/forecast/{key}/{latitude},{longitude}")
        Call<Forecast> getForecastAsync(
                @Path("key") String key,
                @Path("latitude") String latitude,
                @Path("longitude") String longitude
        );
    }

    public Forecast loadForecastData(){
            final Forecast forecast=new Forecast();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL).
                            addConverterFactory(GsonConverterFactory.create())
                    .callbackExecutor(Executors.newFixedThreadPool(1))
                    .build();
            WeatherService service = retrofit.create(WeatherService.class);
            final Call<Forecast> result =service.getForecastAsync("7948360da5ac4beac75eec72190caa7a", "37.8267", "-122.423");
            result.enqueue(new Callback<Forecast>() {
                @Override
                public void onResponse(Response<Forecast> response, Retrofit retrofit) {
                    try {
                        double[] mTemperatures = new double[response.body().hourly.data.size()];
                        forecast.hourly= response.body().hourly;
//                        for (int i = 0; i < response.body().hourly.data.size(); i++) {
//
//                            System.out.println(response.body().hourly.data.get(i).icon);
//
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
                //     service.getForecastAsync("7948360da5ac4beac75eec72190caa7a", "37.8267", "-122.423", callback);

            });
        return forecast;
    }
}
