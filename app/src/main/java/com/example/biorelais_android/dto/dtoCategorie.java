package com.example.biorelais_android.dto;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dtoCategorie {

    // ---------------------------------------------
    private final int idCateg;
    private final String titre;
    // ---------------------------------------------



    // ---------------------------------------------
    public dtoCategorie(int idCateg, String titre) {
        this.idCateg = idCateg;
        this.titre = titre;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public String getTitre() {
        return titre;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static dtoCategorie hydrateCategorie(JSONObject json) throws JSONException {
        // Creer la categorie
        dtoCategorie categ = new dtoCategorie(
                json.getInt("idCateg"),
                json.getString("nomCategorie")
        );
        return categ;
    }
    // ---------------------------------------------

}
