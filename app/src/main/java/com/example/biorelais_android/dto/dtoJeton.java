package com.example.biorelais_android.dto;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dtoJeton {

    // ---------------------------------------------
    private final String valeur;
    private final Date dateExpiration;

    private dtoUtilisateur utilisateur;
    // ---------------------------------------------



    // ---------------------------------------------
    public dtoJeton(
            String valeur,
            Date dateExpiration
    ) {
        this.valeur = valeur;
        this.dateExpiration = dateExpiration;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public String getValeur() {
        return valeur;
    }

    public dtoUtilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(dtoUtilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static dtoJeton hydrateJeton(JSONObject json) throws JSONException, ParseException {
        // Creer le jeton
        dtoJeton jeton = new dtoJeton(
                json.getString("jeton"),
                new SimpleDateFormat("yyyy-MM-dd")
                        .parse(json.getString("dateExpiration"))
        );
        return jeton;
    }
    // ---------------------------------------------

}
