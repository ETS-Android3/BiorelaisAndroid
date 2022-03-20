package com.example.biorelais_android.lib;

import android.app.Activity;

import com.example.biorelais_android.ui.MainActivity;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class DBConnex {

    // ---------------------------------------------
    public static void requeteHTTP(
            Activity act,
            String command,
            HashMap<String, Object> params,
            RunnableParameter successFunc,
            Runnable failFunc) {

        // Build la requete
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("command", command);
        if (params != null) {
            for (String key : params.keySet()) {
                formBody.add(key, String.valueOf(params.get(key)));
            }
        }
        RequestBody requestBody = formBody.build();

        Request request = new Request.Builder()
                .url(MainActivity.SERVER_COMMAND + "/command.php")
                .post(requestBody)
                .build();

        // Execute la requete
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Recupere la reponse
                String responseStr = response.body().string();
                successFunc.run(responseStr);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // Fail http
                e.printStackTrace();
                MsgBox.errorServer(act);
                failFunc.run();
            }
        });

    }
    // ---------------------------------------------

}
