package com.example.biorelais_android.dto;


import org.json.JSONException;
import org.json.JSONObject;

public class dtoProposer {

    // ---------------------------------------------
    private final double quantite;
    private final double prix;

    private dtoUnite unite;
    private dtoProduit produit;
    private dtoVente vente;
    // ---------------------------------------------



    // ---------------------------------------------
    public dtoProposer(double quantite, double prix) {
        this.quantite = quantite;
        this.prix = prix;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public double getQuantite() {
        return quantite;
    }

    public dtoProduit getProduit() {
        return produit;
    }

    public double getPrix() {
        return prix;
    }

    public dtoUnite getUnite() {
        return unite;
    }

    public void setUnite(dtoUnite unite) {
        this.unite = unite;
    }

    public void setProduit(dtoProduit produit) {
        this.produit = produit;
    }

    public void setVente(dtoVente vente) {
        this.vente = vente;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static dtoProposer hydrateProposer(JSONObject json) throws JSONException {
        // Creer le proposer
        dtoProposer prop = new dtoProposer(
                json.getDouble("quantite"),
                json.getDouble("prix")
        );
        return prop;
    }
    // ---------------------------------------------

}
