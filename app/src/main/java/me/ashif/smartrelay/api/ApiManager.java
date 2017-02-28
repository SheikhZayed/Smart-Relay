package me.ashif.smartrelay.api;

import retrofit2.Retrofit;

/**
 * Created by Ashif on 10/1/17,January,2017
 * TechJini Solutions
 * Banglore,India
 */

public class ApiManager {
    public static final String BASE_URL = "http://192.168.1.25/pwr/relays/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }
}