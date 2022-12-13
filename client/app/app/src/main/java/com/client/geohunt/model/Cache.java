package com.client.geohunt.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cache {

   @SerializedName("id")
   @Expose
   private int id;
   @SerializedName("latitude")
   @Expose
   private String latitude;
   @SerializedName("longitude")
   @Expose
   private String longitude;
   @SerializedName("message")
   @Expose
   private Texte message;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getLatitude() {
      return latitude;
   }

   public void setLatitude(String latitude) {
      this.latitude = latitude;
   }

   public String getLongitude() {
      return longitude;
   }

   public void setLongitude(String longitude) {
      this.longitude = longitude;
   }

   public Texte getMessage() {
      return message;
   }

   public void setMessage(Texte message) {
      this.message = message;
   }
}
