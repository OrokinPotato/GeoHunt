package com.client.geohunt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
   @SerializedName("username")
   @Expose
   private String username;

   @SerializedName("score")
   @Expose
   private int score;

   @SerializedName("passwordHash")
   @Expose
   private String passwordHash;

   @SerializedName("emailAddr")
   @Expose
   private String emailAddr;

   @SerializedName("point")
   @Expose
   private int point;

   @SerializedName("lastLat")
   @Expose
   private String lastLat;

   @SerializedName("lastLon")
   @Expose
   private String lastLon;

   public User(String username) {
      this.username = username;
      this.score = 0;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }

   public String getPasswordHash() {
      return passwordHash;
   }

   public void setPasswordHash(String passwordHash) {
      this.passwordHash = passwordHash;
   }

   public String getEmailAddr() {
      return emailAddr;
   }

   public void setEmailAddr(String emailAddr) {
      this.emailAddr = emailAddr;
   }

   public int getPoint() {
      return point;
   }

   public void setPoint(int point) {
      this.point = point;
   }

   public String getLastLat() {
      return lastLat;
   }

   public void setLastLat(String lastLat) {
      this.lastLat = lastLat;
   }

   public String getLastLon() {
      return lastLon;
   }

   public void setLastLon(String lastLon) {
      this.lastLon = lastLon;
   }

}
