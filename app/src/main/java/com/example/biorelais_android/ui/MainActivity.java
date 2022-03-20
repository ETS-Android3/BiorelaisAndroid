package com.example.biorelais_android.ui;


import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.biorelais_android.R;
import com.example.biorelais_android.dao.daoJeton;
import com.example.biorelais_android.dto.dtoJeton;
import com.example.biorelais_android.lib.Display;
import com.example.biorelais_android.lib.FileSimply;
import com.example.biorelais_android.lib.RunnableParameter;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    // ---------------------------------------------
    public final static String SERVER_COMMAND = "http://192.168.1.96/BioRelaisAndroidServer/command.php";
    public final static String SERVER_COMMAND_IMAGE = "http://192.168.1.96/BioRelaisAndroidServer";
    public static String appPath;
    public static dtoJeton currentSession;
    // ---------------------------------------------



    // ---------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Défini le chemin com.bio/file...
        MainActivity.appPath = ((Context)this)
                .getApplicationContext()
                .getFilesDir()
                .getAbsolutePath();

        // Ajoute les encoches
        Display.addNotch(this, R.color.biorelais_blue);

        // Se souvenir de moi
        if(FileSimply.fileExist(MainActivity.appPath + "/token")) {
            try {
                String token = FileSimply.readOneLine(this, "token");
                connexionByToken(token);
            } catch (IOException e) {
                e.printStackTrace();
                openConnex();
            }
        } else {
            // Si aucun token
            openConnex();
        }
    }
    // ---------------------------------------------



    // ---------------------------------------------
    private void openConnex() {
        // Si aucun token
        Display.openAct(this, ConnexionActivity.class);
        finishAffinity();
    }
    // ---------------------------------------------



    // ---------------------------------------------

    private void openAccueil() {
        // Si aucun token
        Display.openAct(this, AccueilActivity.class);
        finishAffinity();
    }
    // ---------------------------------------------



    // ---------------------------------------------
    private void connexionByToken(String token) {
        // Lance la connexion
        daoJeton.verificationByToken(
                this,
                // Si Success de la recuperation
                new RunnableParameter() {
                    @Override
                    public void run() {}
                    @Override
                    public void run(Object param) {
                        currentSession = (dtoJeton) param;
                        // Redirige vers acceuil
                        runOnUiThread(() -> openAccueil());
                    }
                },
                // Si fail de la recuperation
                () -> runOnUiThread(() -> openConnex()),
                token
        );
    }

    // ---------------------------------------------

}