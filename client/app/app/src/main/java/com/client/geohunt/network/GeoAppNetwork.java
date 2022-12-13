package com.client.geohunt.network;

import android.os.Message;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.client.geohunt.model.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeoAppNetwork {

    private static final String TAG = GeoAppNetwork.class.getSimpleName();
    private GeoAppAPI geoAPI;

    public GeoAppNetwork(){
        geoAPI = RetrofitRequest.getRetrofitInstance().create(GeoAppAPI.class);
    }
    /*
    public LiveData<List<Cache>> getCachesList(){
        final MutableLiveData<List<Cache>> data = new MutableLiveData<>();
        geoAPI.getCachesList()
                .enqueue(new Callback<List<Cache>>() {
                         @Override
                         public void onResponse(Call<List<Cache>> call, Response<List<Cache>> response) {
                             Log.d(TAG, "Callback onResponse::" + response);
                             if (response.body() != null){
                                 data.setValue(response.body());
                                 Log.d(TAG, "Data List length::" + response.body().size());
                                 Log.d(TAG, "Data List 1st elem::" + response.body().get(1).getId());
                             }
                         }
                         @Override
                         public void onFailure(Call<List<Cache>> call, Throwable t) {
                            data.setValue(null);
                            Log.d(TAG, "Callback onFailure::" + t);
                         }
                });
        return data;
    }
    */
    public LiveData<List<Cache>> getCachesList(){
        final MutableLiveData<List<Cache>> data = new MutableLiveData<>();
        List<Cache> lc = new ArrayList<>();
        geoAPI.getCachesList().enqueue(
                new Callback<CacheList>() {
                    @Override
                    public void onResponse(Call<CacheList> call, Response<CacheList> response) {
                        Log.d(TAG, "GET return onResponse::" + response.code());
                        if (response.body() != null) {
                            Log.d(TAG, "Data List Data length::" + response.body().getListCaches().size());
                            Log.d(TAG, "Data List 1st elem::" + response.body().getListCaches().get(0));
                            lc.addAll(response.body().getListCaches());
                            data.setValue(lc);
                        }
                    }

                    @Override
                    public void onFailure(Call<CacheList> call, Throwable t) {
                        Log.d(TAG, "GET getCachesList onFailure throwable::" + t);
                        data.setValue(null);
                    }
                }
        );
        return data;
    }

    public LiveData<Cache> getCache(int id) {
        final MutableLiveData<Cache> data = new MutableLiveData<>();
        geoAPI.getCache(id).enqueue(
                new Callback<Cache>() {
                    @Override
                    public void onResponse(Call<Cache> call, Response<Cache> response) {
                        Log.d(TAG, "GET return onResponse::" + response.code());
                        if (response.body() != null) {
                            Log.d(TAG, "Data Cache id::" + response.body().getId());
                            Log.d(TAG, "Data Cache message::" + response.body().getMessage());
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Cache> call, Throwable t) {
                        Log.d(TAG, "GET getCache onFailure throwable::" + t);
                        data.setValue(null);
                    }
                }
        );
        return data;
    }

    public LiveData<User> getUser(String username){
        final MutableLiveData<User> data = new MutableLiveData<>();
        geoAPI.getUser(username).enqueue(
                new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d(TAG, "GET return onResponse::" + response.code());
                        if (response.body() != null) {
                            Log.d(TAG, "Data User username::" + response.body().getUsername());
                            Log.d(TAG, "Data User email Addr::" + response.body().getEmailAddr());
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d(TAG, "GET getUser onFailure throwable::" + t);
                        data.setValue(null);
                    }
                }
        );
        return data;
    }

    public LiveData<Response<Message>> postUser(User user){
        final MutableLiveData<Response<Message>> data = new MutableLiveData<>();

        Response<Message> res;
        try {
            res = geoAPI.postUser(user);
            data.setValue(res);

            Log.d(TAG, "POST return success::" + data.getValue().isSuccessful());
            Log.d(TAG, "POST return code::" + data.getValue().code());

        } catch (Throwable t) {
            Log.d(TAG, "POST postUser throwable::" + t);
        }

        return data;
    }

    public void createPost(String username) {
        geoAPI.createPost(username);
    }
}
