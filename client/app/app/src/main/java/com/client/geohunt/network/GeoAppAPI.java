package com.client.geohunt.network;

import android.os.Message;

import com.client.geohunt.model.*;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

public interface GeoAppAPI {

    @GET("/repere")
    Call<CacheList> getCachesList();

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

    @FormUrlEncoded
    @POST("/user")
    Call<User> createPost(@Field("username") String username);
    /*
    @POST("/profils/{username}")
    Response<Message> postUser(
          @Query("username") String username,
          @Body
    );
    */
}
