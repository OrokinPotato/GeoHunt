package com.client.geohunt.network;

import android.os.Message;

import com.client.geohunt.model.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

public interface GeoAppAPI {

    @GET("/repere")
    Call<List<Cache>> getCachesList();

    @GET("/repere/{id}")
    Call<Cache> getCache(
            @Path("id") int id
    );

    @GET("/user/{username}")
    Call<User> getUser(
            @Path("username") String username
    );

    @POST("/user")
    Response<Message> postUser(
            @Body User user
    );

    /*
    @POST("/profils/{username}")
    Response<Message> postUser(
          @Query("username") String username,
          @Body
    );
    */
}
