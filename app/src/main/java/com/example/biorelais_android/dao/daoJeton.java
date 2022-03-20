package com.example.biorelais_android.dao;

import android.app.Activity;

import com.example.biorelais_android.R;
import com.example.biorelais_android.dto.dtoJeton;
import com.example.biorelais_android.dto.dtoStatut;
import com.example.biorelais_android.dto.dtoUtilisateur;
import com.example.biorelais_android.lib.DBConnex;
import com.example.biorelais_android.lib.MsgBox;
import com.example.biorelais_android.lib.RunnableParameter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;


public class daoJeton {

    // ---------------------------------------------
    public static void verificationByToken(
            Activity act,
            RunnableParameter sucess,
            Runnable fail,
            String token) {

        HashMap<String, Object> param = new HashMap<>();
        param.put("token", token);

        DBConnex.requeteHTTP(
                act,
                "authentificationByToken",
                param,
                new RunnableParameter() {
                    @Override
                    public void run() {}
                    @Override
                    public void run(Object param) {
                        String rep = (String) param;
                        if (!rep.isEmpty()) {
                            try {
                                // Contruit l'objet utilisateur
                                JSONObject json = new JSONObject(rep);

                                dtoStatut statut = dtoStatut.hydrateStatut(json);

                                dtoUtilisateur user = dtoUtilisateur.hydrateUser(json);
                                user.setStatut(statut);

                                dtoJeton jeton = dtoJeton.hydrateJeton(json);
                                jeton.setUtilisateur(user);

                                sucess.run(jeton);
                            } catch (JSONException | ParseException e) {
                                e.printStackTrace();
                                MsgBox.errorJson(act);
                                fail.run();
                            }
                        } else {
                            MsgBox.errorExpired(act);
                            fail.run();
                        }
                    }
                },
                fail
        );
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static void verification(
            Activity act,
            RunnableParameter sucess,
            Runnable fail,
            String login,
            String mdp) {

        HashMap<String, Object> param = new HashMap<>();
        param.put("login", login);
        param.put("mdp", mdp);

        DBConnex.requeteHTTP(
                act,
                "authentification",
                param,
                new RunnableParameter() {
                    @Override
                    public void run() {}
                    @Override
                    public void run(Object param) {
                        String rep = (String) param;
                        if (!rep.isEmpty()) {
                            try {
                                // Contruit l'objet utilisateur
                                JSONObject json = new JSONObject(rep);

                                dtoStatut statut = dtoStatut.hydrateStatut(json);

                                dtoUtilisateur user = dtoUtilisateur.hydrateUser(json);
                                user.setStatut(statut);

                                dtoJeton jeton = dtoJeton.hydrateJeton(json);
                                jeton.setUtilisateur(user);

                                sucess.run(jeton);
                            } catch (JSONException | ParseException e) {
                                e.printStackTrace();
                                MsgBox.errorJson(act);
                                fail.run();
                            }
                        } else {
                            MsgBox.ok(
                                    act,
                                    "Oops..",
                                    "Mot de passe incorrect !",
                                    R.drawable.img_oops
                            );
                            fail.run();
                        }
                    }
                },
                fail
        );
    }
    // ---------------------------------------------

}
