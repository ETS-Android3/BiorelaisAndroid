package com.example.biorelais_android.dao;


import android.app.Activity;

import com.example.biorelais_android.dto.dtoProduit;
import com.example.biorelais_android.dto.dtoProposer;
import com.example.biorelais_android.dto.dtoUnite;
import com.example.biorelais_android.dto.dtoVente;
import com.example.biorelais_android.lib.DBConnex;
import com.example.biorelais_android.lib.RunnableParameter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class daoProposer {

    // ---------------------------------------------
    public static void getNouveauProduit(
            Activity act,
            RunnableParameter sucess,
            Runnable fail
    ) {
        DBConnex.requeteHTTP(
                act,
                "getNouveauProduit",
                null,
                new RunnableParameter() {
                    @Override
                    public void run() {}
                    @Override
                    public void run(Object param) {
                        String rep = (String) param;
                        try {
                            // Contruit l'objet proposer
                            JSONObject json = new JSONObject(rep);

                            dtoUnite unite = dtoUnite.hydrateUnite(json);

                            dtoVente vente = dtoVente.hydrateVente(json);

                            dtoProduit produit = dtoProduit.hydrateProduit(json);

                            dtoProposer prop = dtoProposer.hydrateProposer(json);
                            prop.setUnite(unite);
                            prop.setVente(vente);
                            prop.setProduit(produit);

                            sucess.run(prop);
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
