package com.client.geohunt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.client.geohunt.model.User;

public class UserActivity extends AppCompatActivity {

   GeoHuntViewModel viewModel;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_user);

      TextView tv_userText = findViewById(R.id.tv_userText);
      TextView tv_userAddr = findViewById(R.id.tv_userAddr);
      TextView tv_cacheFoundNum = findViewById(R.id.tv_cacheFoundNum);
      TextView tv_lastLatLng = findViewById(R.id.tv_positionLatLng);


      //Init user for testing, data should be retrieved with GET
      viewModel = new ViewModelProvider(this).get(GeoHuntViewModel.class);
      viewModel.getUser("user0").observe(this, new Observer<User>() {
         @Override
         public void onChanged(User user) {
            if (user != null){
               tv_userText.setText(user.getUsername());
            //   tv_userAddr.setText(user.getEmailAddr());
            //   tv_cacheFoundNum.setText(user.getPoint());
            //   String latlng = user.getLastLat()+", "+user.getLastLon();
            //   tv_lastLatLng.setText(latlng);
            }
         }
      });

      Button btnBack = findViewById(R.id.btnBack);
      Button btnChangeEmail = findViewById(R.id.btnChangeEmail);
      Button btnChangePassword = findViewById(R.id.btnChangePassword);
      btnBack.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            finish();
         }
      });
      btnChangeEmail.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Toast.makeText(UserActivity.this, "Not implemented.", Toast.LENGTH_SHORT).show();
         }
      });
      btnChangePassword.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Toast.makeText(UserActivity.this, "Not implemented.", Toast.LENGTH_SHORT).show();
         }
      });
   }
}
