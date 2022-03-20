package com.example.biorelais_android.lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.biorelais_android.ui.MainActivity;
import com.example.biorelais_android.R;
import com.example.biorelais_android.dto.dtoCategorie;
import com.example.biorelais_android.dto.dtoProduit;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class Display {

    // ---------------------------------------------
    // Gère l'encoche
    public static void addNotch(Activity act, int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(color));
        }
    }

    public static void openAct(Context act, Class open) {
        act.startActivity(new Intent(act, open));
    }

    public static void openFrg(Fragment base, Fragment but, int container) {
        base.getFragmentManager().beginTransaction()
                .replace(((ViewGroup)base.getView().getParent()).getId(), but)
                .addToBackStack(null)
                .commit();
    }

    public static void closeFrg(Fragment frg) {
        FragmentManager mng = frg.getFragmentManager();
        mng.beginTransaction().remove(frg).commit();
        mng.popBackStack();
    }

    public static  void runUi(Fragment frg, Runnable run) {
        Activity act = frg.getActivity();
        if (act != null) {
            act.runOnUiThread(run);
        }
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static String getStringInput(Activity act, int id) {
        return ((EditText) act.findViewById(id))
                .getText()
                .toString();
    }
    public static String getStringInput(Fragment act, int id) {
        return getStringInput(act.getActivity(), id);
    }
    public static  View getElem(Fragment frg, int idElem) {
        return frg.getActivity().findViewById(idElem);
    }
    public static  BitmapDrawable getDraw(Fragment frg, int idElem) {
        return (BitmapDrawable)frg.getActivity().getDrawable(idElem);
    }
    public static int getColor(Fragment frg, int idElem) {
        return frg.getActivity().getResources().getColor(idElem);
    }


    public static void goneElem(Fragment frg, int idElem) {
        runUi(frg, () -> getElem(frg, idElem).setVisibility(View.GONE));
    }
    public static void degoneElem(Fragment frg, int idElem) {
        runUi(frg, () -> getElem(frg, idElem).setVisibility(View.VISIBLE));
    }
    public static void changeText(Fragment frg, int idElem, String text) {
        runUi(frg, () -> ((TextView)getElem(frg, idElem)).setText(text));
    }
    public static void changeImg(Fragment frg, int idElem, Bitmap img) {
        runUi(frg, () -> ((ImageView)getElem(frg, idElem)).setImageBitmap(img));
    }
    public static boolean estCheck(Fragment frg, int idElem) {
        return ((RadioButton)Display.getElem(frg, idElem)).isChecked();
    }
    public static void clearListView(Fragment frg, int idElem) {
        ListView list = (ListView)Display.getElem(frg, idElem);
        list.setAdapter(null);
    }


    public static void setOnclickEvent(Fragment frg, int idElem, View.OnClickListener event) {
        Display.getElem(frg, idElem)
                .setOnClickListener(event);
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static Bitmap downloadImageFromURL(String url) throws IOException {
        URL obj = new URL(url);
        Bitmap bmp = BitmapFactory.decodeStream(obj.openConnection().getInputStream());
        return bmp;
    }

    public static String findImgUrl(dtoProduit leProd) {
        dtoCategorie laCeteg = leProd.getCategorie();
        String url =  MainActivity.SERVER_COMMAND_IMAGE + "/img/" + laCeteg.getTitre() + "/" + leProd.getIdProduit() + ".jpg";
        Log.e("Url",url);
        return url;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static String convertToEuro(double prix) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String euro = formatter.format(prix);
        euro = euro.replaceAll("\\s+","");
        return euro;
    }

    public static String convertToDecimal(double quantite) {
        return convertToDecimal(quantite, false);
    }
    public static String convertToDecimal(double quantite, boolean replaceComma) {
        DecimalFormat df = new DecimalFormat("0.#");
        String dec = df.format(quantite);
        if (replaceComma) {
            dec = dec.replace(',', '.');
        }
        return dec;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static ProgressBar addLoadingButton(Activity act, Button btn) {
        // Recupere le layout principal
        ConstraintLayout layout = (ConstraintLayout)btn.getParent();
        // Creer le progress
        ProgressBar load = new ProgressBar(act);
        load.setIndeterminate(true);
        load.setIndeterminateDrawable(act.getResources().getDrawable(R.drawable.sh_progress));
        load.setElevation(btn.getElevation());
        load.setId(View.generateViewId());
        layout.addView(load);
        // Place le progress APRES l'avoir afficher
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(
                load.getId(), ConstraintSet.TOP,
                btn.getId(), ConstraintSet.TOP,
                0);
        constraintSet.connect(
                load.getId(), ConstraintSet.LEFT,
                btn.getId(), ConstraintSet.LEFT,
                0);
        constraintSet.connect(
                load.getId(), ConstraintSet.RIGHT,
                btn.getId(), ConstraintSet.RIGHT,
                0);
        constraintSet.connect(
                load.getId(), ConstraintSet.BOTTOM,
                btn.getId(), ConstraintSet.BOTTOM,
                0);
        constraintSet.applyTo(layout);
        // Cache le text du button
        btn.setTextScaleX(0);
        // Rafraichi l'écran
        layout.invalidate();
        // Renvoi la progress bar
        return load;
    }

    public static void remLoadingButton(Button btn, ProgressBar load) {
        // Recupere le layout principal
        ViewGroup layout = (ViewGroup) load.getParent();
        // Supprime la progress bar
        layout.removeView(load);
        // Reaffiche le text
        btn.setTextScaleX(1);
    }
    // ---------------------------------------------

}
