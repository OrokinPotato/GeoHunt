package com.client.geohunt;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.client.geohunt.model.*;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import kotlin.TuplesKt;

public class CachesActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener{

    private static final int DEFAULT_ZOOM = 500;

    SupportMapFragment mapFragment;

    GeoHuntViewModel viewModel;

    private List<Pair<LatLng,String>> locList = new ArrayList<>();
    private LatLng userPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caches_map);

        //Init cache and user for testing, data should be retrieved with GET
        viewModel = new ViewModelProvider(this).get(GeoHuntViewModel.class);

        viewModel.getUser("user0").observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user != null)
                {
                   // userPos = new LatLng(Double.parseDouble(user.getLastLat()), Double.parseDouble(user.getLastLon()));
                }
                else {
                   // userPos = null;
                }
            }
        });

        viewModel.getCacheList().observe(this, new Observer<List<Cache>>() {
            @Override
            public void onChanged(List<Cache> caches) {
                if (caches != null){
                    for (Cache cache: caches) {
                        LatLng latLng = new LatLng(Double.parseDouble(cache.getLatitude()), Double.parseDouble(cache.getLongitude()));
                        String mes = cache.getMessage().getTexte();
                        locList.add(new Pair<>(latLng, mes));
                    }
                }
                else {
                    locList = null;
                }
            }
        });

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        if (locList != null){
            int i = 0;
            for (Pair<LatLng, String> pair: locList) {
                googleMap.addCircle(new CircleOptions()
                        .center(pair.first)
                        .radius(100000)
                        .clickable(true)
                ).setTag(i);
                i += 1;
            }
        }
        googleMap.setOnCircleClickListener(new GoogleMap.OnCircleClickListener() {
            @Override
            public void onCircleClick(@NonNull Circle circle) {
                Integer tag = (Integer) circle.getTag();
                String text = locList.get(tag).second;
                Toast.makeText(CachesActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
        if (userPos != null){
            googleMap.addMarker(new MarkerOptions()
                    .position(userPos)
                    .title("Your position"));
        }
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, DEFAULT_ZOOM));
    }

}
