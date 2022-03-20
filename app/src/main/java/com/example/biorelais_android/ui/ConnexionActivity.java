package com.example.biorelais_android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.biorelais_android.R;
import com.example.biorelais_android.dao.daoJeton;
import com.example.biorelais_android.dao.daoUtilisateur;
import com.example.biorelais_android.dto.dtoJeton;
import com.example.biorelais_android.lib.Display;
import com.example.biorelais_android.lib.Encryption;
import com.example.biorelais_android.lib.FileSimply;
import com.example.biorelais_android.lib.MsgBox;
import com.example.biorelais_android.lib.RunnableParameter;

import java.io.IOException;

public class ConnexionActivity extends AppCompatActivity {

    // ---------------------------------------------
    private boolean showPass = false;
    private ProgressBar progConnex;
    // ---------------------------------------------



    // ---------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        // Ajoute les encoches
        Display.addNotch(this, R.color.biorelais_blue);

        // Events
        findViewById(R.id.imageView7).setOnClickListener(v -> showHidePassword());
        findViewById(R.id.button).setOnClickListener(v -> sel());
    }
    // ---------------------------------------------



    // ---------------------------------------------
    // Connexion
    private void sel() {
        // Recupere les infos
        final String login = Display.getStringInput(this, R.id.editTextTextPersonName);
        final String mdp = Display.getStringInput(this, R.id.editTextTextPersonName2);

        if (!login.equals("") && !mdp.equals("")) {
            // Anim start
            this.progConnex = Display.addLoadingButton(this, findViewById(R.id.button));

            // Lance la connexion
            daoUtilisateur.sel(
                    this,
                    // Si Success de la recuperation
                    new RunnableParameter() {
                        @Override
                        public void run() {}
                        @Override
                        public void run(Object param) {
                            connexion((String)param);
                        }
                    },
                    // Si fail de la recuperation
                    () -> runOnUiThread(() -> Display.remLoadingButton(findViewById(R.id.button), this.progConnex)),
                    login
            );

        } else {
            MsgBox.ok(
                    this,
                    "Ohoh..",
                    "Veuillez remplir les informations !",
                    R.drawable.img_pen
            );
        }
    }
    // ---------------------------------------------



    // ---------------------------------------------
    private void connexion(String sel) {
        // Recupere les infos
        final String login = Display.getStringInput(this, R.id.editTextTextPersonName);
        String mdp = Display.getStringInput(this, R.id.editTextTextPersonName2);

        // Hash le mdp en md5 et colle le sel
        mdp = Encryption.sha256(mdp + sel);

        // Lance la connexion
        daoJeton.verification(
                this,
                // Si Success de la recuperation
                new RunnableParameter() {
                    @Override
                    public void run() {}
                    @Override
                    public void run(Object param) {

                        MainActivity.currentSession = (dtoJeton) param;

                        // Enregistre le token dans un fichier pour l'option se souvenir de moi
                        if (((CheckBox)findViewById(R.id.checkBox2)).isChecked()) {
                            try {
                                FileSimply.writeAllText(MainActivity.appPath + "/token", MainActivity.currentSession.getValeur());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (FileSimply.fileExist(MainActivity.appPath + "/token")) {
                            FileSimply.fileDelete(MainActivity.appPath + "/token");
                        }

                        runOnUiThread(() -> {
                            Display.remLoadingButton(findViewById(R.id.button), progConnex);
                            // Redirige vers acceuil
                            Display.openAct(ConnexionActivity.this, AccueilActivity.class);
                        });

                    }
                },
                // Si fail de la recuperation
                () -> runOnUiThread(() -> Display.remLoadingButton(findViewById(R.id.button), this.progConnex)),
                login,
                mdp);
    }
    // ---------------------------------------------



    // ---------------------------------------------
    // Icone afficher/cacher le mot de passe
    private void showHidePassword() {
        this.showPass = !this.showPass;

        EditText edit = findViewById(R.id.editTextTextPersonName2);
        ImageView icon = findViewById(R.id.imageView7);

        if (this.showPass) {
            edit.setTransformationMethod(null);
            icon.setImageResource(R.drawable.img_eye);
        } else {
            edit.setTransformationMethod(new PasswordTransformationMethod());
            icon.setImageResource(R.drawable.img_hide);
        }
    }
    // ---------------------------------------------

}