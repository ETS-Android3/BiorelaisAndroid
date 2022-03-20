package com.example.biorelais_android.dao;

import android.app.Activity;

import com.example.biorelais_android.lib.DBConnex;
import com.example.biorelais_android.lib.MsgBox;
import com.example.biorelais_android.lib.RunnableParameter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class daoUtilisateur {


    // ---------------------------------------------
    public static void sel(
            Activity act,
            RunnableParameter sucess,
            Runnable fail,
            String login) {

        HashMap<String, Object> param = new HashMap<>();
        param.put("login", login);

        DBConnex.requeteHTTP(
                act,
                "getSel",
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
                                String sel = json.getString("sel");
                                sucess.run(sel);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                MsgBox.errorJson(act);
                                fail.run();
                            }
                        } else {
                            MsgBox.errorUnkownUser(act);
                            fail.run();
                        }
                    }
                },
                fail
        );
    }
    // ---------------------------------------------

}
