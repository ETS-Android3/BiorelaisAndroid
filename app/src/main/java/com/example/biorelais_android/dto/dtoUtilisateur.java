package com.example.biorelais_android.dto;

import org.json.JSONException;
import org.json.JSONObject;

public class dtoUtilisateur {

    // ---------------------------------------------
    private final int idUtilisateur;
    private final String mail;
    private final String nom;
    private final String prenom;

    private dtoStatut statut;
    // ---------------------------------------------



    // ---------------------------------------------
    public dtoUtilisateur(
            int idUtilisateur,
            String mail,
            String nom,
            String prenom
    ) {
        this.idUtilisateur = idUtilisateur;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public String getMail() {
        return mail;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setStatut(dtoStatut statut) {
        this.statut = statut;
    }

    public int getIdUtilisateur() { return idUtilisateur; }
    // ---------------------------------------------



    // ---------------------------------------------
    public static dtoUtilisateur hydrateUser(JSONObject json) throws JSONException {
        // Creer l'utilisateur
        dtoUtilisateur user = new dtoUtilisateur(
                json.getInt("idUtilisateur"),
                json.getString("mail"),
                json.getString("nom"),
                json.getString("prenom")
        );
        return user;
    }
    // ---------------------------------------------

}
