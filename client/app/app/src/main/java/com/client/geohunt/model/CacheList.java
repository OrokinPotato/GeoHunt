package com.client.geohunt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CacheList {
   @SerializedName("listCaches")
   @Expose
   private List<Cache> ListCaches;

   public List<Cache> getListCaches() {
      return ListCaches;
   }

   public void setListCaches(List<Cache> listCaches) {
      ListCaches = listCaches;
   }
}
