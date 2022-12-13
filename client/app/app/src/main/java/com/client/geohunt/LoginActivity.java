package com.client.geohunt;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.client.geohunt.model.User;

import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {
    GeoHuntViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(GeoHuntViewModel.class);
        Button btnLogin = findViewById(R.id.connecter_bt);
        EditText editUsername = findViewById(R.id.username_edit);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //connectUser(editUsername.getText().toString());
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void connectUser(String username) {
        User user = new User(username);
        user.setUsername(username);
        user.setScore(0);
        viewModel.postUser(user).observe(LoginActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                    Log.d(TAG, s);
                }
        });
    }
}