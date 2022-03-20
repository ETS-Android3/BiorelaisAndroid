package com.example.biorelais_android.dao;


import android.app.Activity;

import com.example.biorelais_android.dto.dtoVente;
import com.example.biorelais_android.lib.DBConnex;
import com.example.biorelais_android.lib.RunnableParameter;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class daoVente {

    // ---------------------------------------------
    public static void getProchaineVente(
            Activity act,
            RunnableParameter sucess,
            Runnable fail
    ) {
        DBConnex.requeteHTTP(
                act,
                "getProchaineVente",
                null,
                new RunnableParameter() {
                    @Override
                    public void run() {}
                    @Override
                    public void run(Object param) {
                        String rep = (String) param;
                        try {
                            // Contruit l'objet utilisateur
                            JSONObject json = new JSONObject(rep);
                            dtoVente vente = dtoVente.hydrateVente(json);
                            sucess.run(vente);
                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                            fail.run();
                        }
                    }
                },
                fail
        );

    }
    // ---------------------------------------------

}
