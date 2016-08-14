package ru.mydelivery.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Request {

    private static API instance;

    public static API getInstance() {
        if(instance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://mydelivery.96.lt")
                    .build();
           return instance = retrofit.create(API.class);
        }
        return instance;
    }
}
