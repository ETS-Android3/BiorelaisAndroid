package com.example.biorelais_android.dto;


import org.json.JSONException;
import org.json.JSONObject;

public class dtoUnite {

    // ---------------------------------------------
    private final String codeUnite;
    private final String libelle;
    // ---------------------------------------------



    // ---------------------------------------------
    public dtoUnite(String codeUnite, String libelle) {
        this.codeUnite = codeUnite;
        this.libelle = libelle;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static dtoUnite hydrateUnite(JSONObject json) throws JSONException {
        // Creer l'unite
        dtoUnite unite = new dtoUnite(
                json.getString("codeUnite"),
                json.getString("libelle")
        );
        return unite;
    }
    // ---------------------------------------------

}
