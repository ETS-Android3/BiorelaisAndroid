package com.example.biorelais_android.dto;


import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class dtoProduit {

    // ---------------------------------------------
    private final int idProduit;
    private final String nom;
    private final String descriptif;

    private dtoProducteur producteur;
    private dtoCategorie categorie;
    // ---------------------------------------------



    // ---------------------------------------------
    public dtoProduit(int idProduit, String nom, String descriptif) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.descriptif = descriptif;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public int getIdProduit() {
        return idProduit;
    }

    public String getNom() {
        return nom;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public dtoCategorie getCategorie() {
        return categorie;
    }

    public void setProducteur(dtoProducteur producteur) {
        this.producteur = producteur;
    }

    public void setCategorie(dtoCategorie categorie) {
        this.categorie = categorie;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static dtoProduit hydrateProduit(JSONObject json) throws JSONException {
        // Creer le produit
        dtoProduit produit = new dtoProduit(
                json.getInt("idProduit"),
                json.getString("nomProduit"),
                json.getString("descriptif")
        );
        return produit;
    }
    // ---------------------------------------------

}
