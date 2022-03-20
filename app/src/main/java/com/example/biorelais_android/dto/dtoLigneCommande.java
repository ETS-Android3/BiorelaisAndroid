package com.example.biorelais_android.dto;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class dtoLigneCommande {

    // ---------------------------------------------
    private final double quantiteALivrer;
    private final double quantiteLivree;
    private final double quantiteRecuperee;

    private dtoCommande commande;
    private dtoProduit produit;
    // ---------------------------------------------



    // ---------------------------------------------
    public dtoLigneCommande(double quantiteALivrer, double quantiteLivree, double quantiteRecuperee) {
        this.quantiteALivrer = quantiteALivrer;
        this.quantiteLivree = quantiteLivree;
        this.quantiteRecuperee = quantiteRecuperee;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public double getQuantiteALivrer() {
        return quantiteALivrer;
    }

    public double getQuantiteLivree() {
        return quantiteLivree;
    }

    public double getQuantiteRecuperee() {
        return quantiteRecuperee;
    }

    public dtoCommande getCommande() {
        return commande;
    }

    public dtoProduit getProduit() {
        return produit;
    }

    public void setCommande(dtoCommande commande) {
        this.commande = commande;
    }

    public void setProduit(dtoProduit produit) {
        this.produit = produit;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static dtoLigneCommande hydrateLigneCommande(JSONObject json) throws JSONException {
        // Creer la ligne de commande
        dtoLigneCommande ligne = new dtoLigneCommande(
                json.getDouble("quantiteALivrer"),
                json.getDouble("quantiteLivree"),
                json.getDouble("quantiteRecuperee")
        );
        return ligne;
    }
    // ---------------------------------------------

}