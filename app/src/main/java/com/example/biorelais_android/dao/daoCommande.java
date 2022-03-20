package com.example.biorelais_android.dao;

import android.app.Activity;
import android.util.Log;

import com.example.biorelais_android.dto.dtoCategorie;
import com.example.biorelais_android.lib.DBConnex;
import com.example.biorelais_android.lib.MsgBox;
import com.example.biorelais_android.lib.RunnableParameter;
import com.example.biorelais_android.ui.MainActivity;
import com.example.biorelais_android.dto.dtoCommande;
import com.example.biorelais_android.dto.dtoJeton;
import com.example.biorelais_android.dto.dtoLigneCommande;
import com.example.biorelais_android.dto.dtoProduit;
import com.example.biorelais_android.dto.dtoProposer;
import com.example.biorelais_android.dto.dtoUnite;
import com.example.biorelais_android.ui.client.clientFragment;
import com.example.biorelais_android.ui.client.clientGererCommandeFragment;
import com.example.biorelais_android.ui.client.clientcommandeFragment;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class daoCommande
{

    // ---------------------------------------------
    public static void InfosCommande(clientcommandeFragment act, int idCommande, String token) {
        RequestBody formBody = new FormBody.Builder()
                .add("command", "infosCommandeClient")
                .add("token", token)
                .add("id", String.valueOf(idCommande))
                .build();

        Request request = new Request.Builder()
                .url(MainActivity.SERVER_COMMAND)
                .post(formBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseStr = response.body().string();

                if (!responseStr.equals("false") && !responseStr.equals("")) {

                    try {

                        JSONArray jsonArrayLigneCommande = new JSONArray(responseStr);

                        LinkedHashMap<dtoProduit, Object[]> lesProduits = new LinkedHashMap<>();

                        for (int i = 0; i < jsonArrayLigneCommande.length(); i++) {

                            JSONObject json = jsonArrayLigneCommande.getJSONObject(i);

                            dtoCommande comm = dtoCommande.hydrateCommande(json);

                            dtoCategorie categ = dtoCategorie.hydrateCategorie(json);

                            dtoProduit prod = dtoProduit.hydrateProduit(json);
                            prod.setCategorie(categ);

                            dtoUnite unite = dtoUnite.hydrateUnite(json);

                            dtoProposer prop = dtoProposer.hydrateProposer(json);
                            prop.setProduit(prod);
                            prop.setUnite(unite);

                            dtoLigneCommande ligne = dtoLigneCommande.hydrateLigneCommande(json);
                            ligne.setProduit(prod);
                            ligne.setCommande(comm);

                            lesProduits.put(prod, new Object[] { ligne, prop });
                        }
                        act.CommandeRempli(lesProduits);
                    }
                    catch(JSONException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    // ---------------------------------------------

    /*
    // ---------------------------------------------
    public static void InfosCommande(
             Activity act,
             RunnableParameter sucess,
             Runnable fail,
             int idCommande
    ) {
        int userId = MainActivity.currentSession
                .getUtilisateur()
                .getIdUtilisateur();

        HashMap<String, Object> param = new HashMap<>();
        param.put("idCommande", idCommande);
        param.put("idUtilisateur", userId);

        DBConnex.requeteHTTP(
                act,
                "infosCommandeClient",
                param,
                new RunnableParameter() {
                    @Override
                    public void run() {}
                    @Override
                    public void run(Object param) {

                        String rep = (String) param;

                        try {
                            JSONArray jsonArrayLigneCommande = new JSONArray(rep);

                            LinkedHashMap<dtoProduit, Object[]> lesProduits = new LinkedHashMap<>();

                            for (int i = 0; i < jsonArrayLigneCommande.length(); i++) {

                                JSONObject json = jsonArrayLigneCommande.getJSONObject(i);

                                dtoCommande comm = dtoCommande.hydrateCommande(json);

                                dtoCategorie categ = dtoCategorie.hydrateCategorie(json);

                                dtoProduit prod = dtoProduit.hydrateProduit(json);
                                prod.setCategorie(categ);

                                dtoUnite unite = dtoUnite.hydrateUnite(json);

                                dtoProposer prop = dtoProposer.hydrateProposer(json);
                                prop.setProduit(prod);
                                prop.setUnite(unite);

                                dtoLigneCommande ligne = dtoLigneCommande.hydrateLigneCommande(json);
                                ligne.setProduit(prod);
                                ligne.setCommande(comm);

                                lesProduits.put(prod, new Object[] { ligne, prop });
                            }
                            sucess.run(lesProduits);
                        } catch (ParseException | JSONException e) {
                            e.printStackTrace();
                            MsgBox.errorJson(act);
                            fail.run();
                        }
                    }
                },
                fail
        );
    }
    // ---------------------------------------------
*/
    // ---------------------------------------------
    public static void InfosCommande2(clientGererCommandeFragment act, int idCommande, String token) throws InterruptedException
    {
        RequestBody formBody = new FormBody.Builder()
                .add("command", "infosCommandeClient")
                .add("token", token)
                .add("id", String.valueOf(idCommande))
                .build();

        Request request = new Request.Builder()
                .url(MainActivity.SERVER_COMMAND)
                .post(formBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseStr = response.body().string();

                if (!responseStr.equals("false") && !responseStr.equals("")) {

                    try {

                        JSONArray jsonArrayLigneCommande = new JSONArray(responseStr);

                        LinkedHashMap<dtoProduit, Object[]> lesProduits = new LinkedHashMap<>();

                        for (int i = 0; i < jsonArrayLigneCommande.length(); i++) {

                            JSONObject json = jsonArrayLigneCommande.getJSONObject(i);

                            dtoCommande comm = dtoCommande.hydrateCommande(json);

                            dtoCategorie categ = dtoCategorie.hydrateCategorie(json);

                            dtoProduit prod = dtoProduit.hydrateProduit(json);
                            prod.setCategorie(categ);

                            dtoUnite unite = dtoUnite.hydrateUnite(json);

                            dtoProposer prop = dtoProposer.hydrateProposer(json);
                            prop.setProduit(prod);
                            prop.setUnite(unite);

                            dtoLigneCommande ligne = dtoLigneCommande.hydrateLigneCommande(json);
                            ligne.setProduit(prod);
                            ligne.setCommande(comm);

                            lesProduits.put(prod, new Object[] { ligne, prop });
                        }
                        act.CommandeRempli(lesProduits);
                    }
                    catch(JSONException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    // ---------------------------------------------

    // ---------------------------------------------
    public static void ToutesLesCommandesDuClient(clientFragment act, int idClient, dtoJeton token,String filtre) throws InterruptedException
    {
        FormBody.Builder formBody = new FormBody.Builder()
                .add("command", "ToutesLesCommandesDuClient")
                .add("token", token.getValeur())
                .add("id", String.valueOf(idClient));

        switch (filtre) {
            case "Valide":
                formBody.add("valide", "1");
                break;
            case "En cours":
                formBody.add("valide", "0");
                break;
            case "Aujourd'hui":
                formBody.add("date_", (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
                break;
            case "Signaler":
                formBody.add("Signaler", "True");
                break;
        }

        Request request = new Request.Builder()
                .url(MainActivity.SERVER_COMMAND)
                .post(formBody.build())
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {}

            @Override
            public void onResponse(Call call,Response response) throws IOException {

                String responseStr = response.body().string();

                if (!responseStr.equals("false") && !responseStr.equals("")) {

                    try {

                        JSONArray jsonArrayCommandeClient = new JSONArray(responseStr);
                        ArrayList<dtoCommande> lesCommande = new ArrayList<>();

                        for (int i = 0; i < jsonArrayCommandeClient.length(); i++) {

                            JSONObject uneCommandeJson = jsonArrayCommandeClient.getJSONObject(i);
                            dtoCommande uneCommande = dtoCommande.hydrateCommande(uneCommandeJson);
                            lesCommande.add(uneCommande);
                        }
                        act.CompleteCommande(lesCommande);
                    }
                    catch(JSONException | ParseException e) {

                        e.printStackTrace();
                    }
                }
                else {
                    act.CompleteCommande(null);
                }
            }
        });
    }
    // ---------------------------------------------

    // ---------------------------------------------
    public static void ConfirmerCommande(clientcommandeFragment act, int idCommande) throws InterruptedException
    {
        RequestBody formBody = new FormBody.Builder()
                .add("command", "confirmerCommandeClient")
                .add("id", String.valueOf(idCommande))
                .build();

        Request request = new Request.Builder()
                .url(MainActivity.SERVER_COMMAND)
                .post(formBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseStr = response.body().string();

                if (!responseStr.equals("false") && !responseStr.equals("")) {
                    act.CompleteConfirmation();
                }
            }
        });
    }
    // ---------------------------------------------

    // ---------------------------------------------
    public static void Signalement(clientGererCommandeFragment act, int idCommande,String Remarque,ArrayList<Long> spinnerValues,ArrayList<Integer> ListeId) throws InterruptedException
    {
        int i=0;

        for (Long val:spinnerValues) {
            RequestBody formBody = new FormBody.Builder()
                    .add("command", "SignalementCommande")
                    .add("id", String.valueOf(idCommande))
                    .add("qtte",val.toString())
                    .add("idProduit",ListeId.get(i).toString())
                    .build();

            Request request = new Request.Builder()
                    .url(MainActivity.SERVER_COMMAND)
                    .post(formBody)
                    .build();

            OkHttpClient client = new OkHttpClient();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {}

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) { }
            });
            i++;
        }

        RequestBody formBody = new FormBody.Builder()
                .add("command", "SignalementCommandeRemarque")
                .add("id", String.valueOf(idCommande))
                .add("Remarque", Remarque)
                .build();

        Request request = new Request.Builder()
                .url(MainActivity.SERVER_COMMAND)
                .post(formBody)
                .build();

        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseStr = response.body().string();

                if (!responseStr.equals("false") && !responseStr.equals("")) {
                    act.CompleteSignalement();
                }
            }
        });
    }
    // ---------------------------------------------
}
