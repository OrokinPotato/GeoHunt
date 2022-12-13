package com.client.geohunt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Texte {
    @SerializedName("texte")
    @Expose
    private String texte;
    @SerializedName("reponse")
    @Expose
    private String reponse;


    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
