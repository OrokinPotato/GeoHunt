package com.client.geohunt;

import android.app.Application;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.client.geohunt.model.*;
import com.client.geohunt.network.GeoAppNetwork;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GeoHuntViewModel extends AndroidViewModel {

    private final GeoAppNetwork geoAppNetwork;

    public LiveData<List<Cache>> cacheList;
    public LiveData<Cache> cache;
    public LiveData<User> user;

    public LiveData<Response<Message>> postUser;

    public GeoHuntViewModel(@NonNull Application application){
        super(application);
        geoAppNetwork = new GeoAppNetwork();
    }

    public LiveData<List<Cache>> getCacheList(){
        this.cacheList = geoAppNetwork.getCachesList();
        return this.cacheList;
    }

    public LiveData<Cache> getCache(int id){
        this.cache = geoAppNetwork.getCache(id);
        return this.cache;
    }

    public LiveData<User> getUser(String username){
        this.user = geoAppNetwork.getUser(username);
        return this.user;
    }

    public LiveData<String> postUser(User user){
        this.postUser = geoAppNetwork.postUser(user);
        MutableLiveData<String> str = new MutableLiveData<>();

        if (this.postUser.getValue() != null){
            if (this.postUser.getValue().isSuccessful()) {
                str.setValue("POST success:: " + this.postUser.getValue().code());
            }
            else {
                str.setValue("POST failure:: " + this.postUser.getValue().code());
            }
        }
        else {
            str.setValue("POST failure:: " + this.postUser.getValue().code());
        }
        return str;
    }

}
