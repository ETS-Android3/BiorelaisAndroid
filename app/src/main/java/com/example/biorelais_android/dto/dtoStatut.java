package com.example.biorelais_android.dto;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class dtoStatut {

    // ---------------------------------------------
    private final String codeStatut;
    private final String libelle;
    // ---------------------------------------------



    // ---------------------------------------------
    public dtoStatut(String codeStatut, String libelle) {
        this.codeStatut = codeStatut;
        this.libelle = libelle;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static dtoStatut hydrateStatut(JSONObject json) throws JSONException {
        // Creer le statut
        dtoStatut statut = new dtoStatut(
                json.getString("codeStatut"),
                json.getString("libelle")
        );
        return statut;
    }
    // ---------------------------------------------

}
