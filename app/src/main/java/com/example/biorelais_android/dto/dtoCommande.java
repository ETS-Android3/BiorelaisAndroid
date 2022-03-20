package com.example.biorelais_android.dto;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dtoCommande
{
    // ---------------------------------------------
    private  int idVente;
    private  int idUtilisateur;
    private  int idCommande;
    private  Date date;
    private boolean validee;
    private String remarque;

    public void setRemarque(String remarque) { this.remarque = remarque; }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setValidee(boolean validee) {
        this.validee = validee;
    }

    
    // ---------------------------------------------
    public String getRemarque() { return remarque; }

    public int getIdVente() {
        return idVente;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public Date getDate() {
        return date;
    }

    public boolean isValidee() {
        return validee;
    }


    // ---------------------------------------------
    public dtoCommande(int idVente, int idUtilisateur, int idCommande, Date date, boolean validee,String remarque) {
        this.idVente = idVente;
        this.idUtilisateur = idUtilisateur;
        this.idCommande = idCommande;
        this.validee = validee;
        this.date = date;
        this.remarque=remarque;
    }
    // ---------------------------------------------

    // ---------------------------------------------
    public static dtoCommande hydrateCommande(JSONObject json) throws JSONException, ParseException {
        // Récupère le contenue JSON de la réponse de la requête en paramètre//
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(json.getString("date_"));

        // Creer la commande
        dtoCommande uneCommande = new dtoCommande(
                json.getInt("idVente"),
                json.getInt("idUtilisateur"),
                json.getInt("idCommande"),
                date,
                json.getInt("validee") == 1,
                json.getString("remarque")
        );


        //Renvoie de l'objet//
        return uneCommande;
    }
    // ---------------------------------------------


}
