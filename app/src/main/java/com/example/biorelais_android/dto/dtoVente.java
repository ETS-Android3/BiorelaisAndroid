package com.example.biorelais_android.dto;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dtoVente {

    // ---------------------------------------------
    private final int idVente;
    private final Date date;
    private final Date dateDebutProd;
    private final Date dateFinProd;
    private final Date dateDebutCli;
    private final Date dateFinCli;
    // ---------------------------------------------



    // ---------------------------------------------
    public dtoVente(int idVente, Date date, Date dateDebutProd, Date dateFinProd, Date dateDebutCli, Date dateFinCli) {
        this.idVente = idVente;
        this.date = date;
        this.dateDebutProd = dateDebutProd;
        this.dateFinProd = dateFinProd;
        this.dateDebutCli = dateDebutCli;
        this.dateFinCli = dateFinCli;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public Date getDateDebutCli() {
        return dateDebutCli;
    }

    public Date getDateFinCli() {
        return dateFinCli;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static dtoVente hydrateVente(JSONObject json) throws JSONException, ParseException {
        // Creer la vente
        dtoVente vente = new dtoVente(
                json.getInt("idVente"),

                new SimpleDateFormat("yyyy-MM-dd")
                        .parse(json.getString("date")),

                new SimpleDateFormat("yyyy-MM-dd")
                        .parse(json.getString("dateDebutProd")),
                new SimpleDateFormat("yyyy-MM-dd")
                .parse(json.getString("dateFinProd")),

                new SimpleDateFormat("yyyy-MM-dd")
                        .parse(json.getString("dateDebutCli")),
                new SimpleDateFormat("yyyy-MM-dd")
                        .parse(json.getString("dateFinCli"))
        );
        return vente;
    }
    // ---------------------------------------------

}
