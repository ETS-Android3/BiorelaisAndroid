package com.example.biorelais_android.lib;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextThemeWrapper;

import com.example.biorelais_android.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MsgBox {

    // ---------------------------------------------
    // Messagebox ok
    public static void ok(Context cont, String titre, String body) {
        ok(cont, titre, body, 0, null);
    }
    public static void ok(Context cont, String titre, String body, int icon) {
        ok(cont, titre, body, icon, null);
    }
    public static void ok(Context cont, String titre, String body, int icon, DialogInterface.OnClickListener listener) {
        showing(
                cont,
                get(cont).setPositiveButton("Ok", listener),
                titre,
                body,
                icon);

    }

    // Messagebox oui non
    public static void yesNo(Context cont, String titre, String body) {
        yesNo(cont, titre, body, 0, null, null);
    }
    public static void yesNo(Context cont, String titre, String body, int icon) {
        yesNo(cont, titre, body, icon, null, null);
    }
    public static void yesNo(Context cont, String titre, String body, int icon, DialogInterface.OnClickListener onYes) {
        yesNo(cont, titre, body, icon, onYes, null);
    }
    public static void yesNo(Context cont, String titre, String body, int icon, DialogInterface.OnClickListener onYes, DialogInterface.OnClickListener onNo) {
        showing(
                cont,
                get(cont)
                        .setPositiveButton("Oui", onYes)
                        .setNegativeButton("Non", onNo),
                titre,
                body,
                icon);

    }
    // ---------------------------------------------



    // ---------------------------------------------
    private static MaterialAlertDialogBuilder get(Context cont) {
        Context context = new ContextThemeWrapper(cont, R.style.DialogTheme);
        MaterialAlertDialogBuilder dlg = new MaterialAlertDialogBuilder(context);
        return dlg;
    }

    private static void showing(Context cont, MaterialAlertDialogBuilder msg, String titre, String body, int icon) {
        ((Activity)cont).runOnUiThread(() -> {
            msg
                    .setTitle(titre)
                    .setIcon(icon)
                    .setMessage(body)
                    .create()
                    .show();
        });
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static void errorExpired(Context cont) {
        MsgBox.errorExpired(cont, null);
    }
    public static void errorExpired(Context cont, DialogInterface.OnClickListener event) {
        MsgBox.ok(
                cont,
                "Vous revoilà !",
                "Votre session à expirée ! Veuillez vous reconnecter.",
                R.drawable.img_clouderr,
                event
        );
    }

    public static void errorServer(Context cont) {
        MsgBox.errorServer(cont, null);
    }
    public static void errorServer(Context cont, DialogInterface.OnClickListener event) {
        MsgBox.ok(
                cont,
                "Erreur !",
                "Impossible de joindre le serveur distant !",
                R.drawable.img_clouderr,
                event
        );
    }

    public static void errorJson(Context cont) { MsgBox.errorJson(cont, null); }
    public static void errorJson(Context cont, DialogInterface.OnClickListener event) {
        MsgBox.ok(
                cont,
                "Ouuups...",
                "Impossible de récupérer les informations !",
                R.drawable.img_oops,
                event
        );
    }

    public static void errorUnkownUser(Context cont) {
        MsgBox.ok(
                cont,
                "Oops...",
                "Cet utilisateur n'existe pas !",
                R.drawable.img_oops
        );
    }
    // ---------------------------------------------

}
