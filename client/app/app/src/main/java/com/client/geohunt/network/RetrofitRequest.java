package com.client.geohunt.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest {

   //private  static GeoAppAPI retrofit;
   private  static Retrofit retrofit;

   public static Retrofit getRetrofitInstance(){
      if (retrofit == null){
         retrofit = new retrofit2.Retrofit.Builder()
                 .baseUrl("http://10.0.2.2:3000")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
       }
      return retrofit;
   }
}