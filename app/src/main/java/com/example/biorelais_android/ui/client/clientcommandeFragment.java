package com.example.biorelais_android.ui.client;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.biorelais_android.ui.MainActivity;
import com.example.biorelais_android.R;
import com.example.biorelais_android.dao.daoCommande;
import com.example.biorelais_android.dto.dtoCommande;
import com.example.biorelais_android.dto.dtoLigneCommande;
import com.example.biorelais_android.dto.dtoProduit;
import com.example.biorelais_android.dto.dtoProposer;
import com.example.biorelais_android.lib.Display;
import com.example.biorelais_android.lib.MsgBox;
import com.example.biorelais_android.lib.MyAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class clientcommandeFragment extends Fragment
{
    // ---------------------------------------------
    private Fragment frg = this;
    // ---------------------------------------------

    // ---------------------------------------------

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        remplirCommande();
    }

    // ---------------------------------------------

    // ---------------------------------------------
    private void remplirCommande()
    {
        Bundle args = getArguments();
        int idCommande = args.getInt("commande");

        daoCommande.InfosCommande(this,idCommande,MainActivity.currentSession.getValeur());
    }
    // ---------------------------------------------

    // ---------------------------------------------
    public void CommandeRempli(LinkedHashMap<dtoProduit, Object[]> lesProduits)
    {
        double prixTotal = 0;
        ArrayList<String> lesNoms = new ArrayList<>();
        ArrayList<String> Quantites = new ArrayList<>();
        ArrayList<Bitmap> images = new ArrayList<>();

        for(dtoProduit leProduit : lesProduits.keySet()) {

            Bitmap img = null;
            try {
                String url = Display.findImgUrl(leProduit);
                img = Display.downloadImageFromURL(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(img == null) {
                // Image par defaut
                img = Display.getDraw(frg, R.drawable.img_defaut).getBitmap();
            }
            images.add(img);

            Object[] obg = lesProduits.get(leProduit);
            dtoLigneCommande ligne = (dtoLigneCommande)obg[0];
            dtoProposer prop = (dtoProposer)obg[1];

            prixTotal += prop.getPrix()*ligne.getQuantiteALivrer();

            lesNoms.add(leProduit.getNom());
            Quantites.add("Quantité à livrer : " + ligne.getQuantiteALivrer() + " prix " + prop.getPrix());
        }

        dtoLigneCommande prlg = (dtoLigneCommande)lesProduits
                .entrySet()
                .iterator()
                .next()
                .getValue()[0];
        dtoCommande command = prlg.getCommande();

        Display.changeText(frg, R.id.TitreCommandeClient, "Commande n°"+ command.getIdCommande());
        Display.changeText(frg, R.id.PrixTotal, "Total : "+ prixTotal +" €");

        getActivity().runOnUiThread(() -> {

            ListView listViewCommandesclients = getView().findViewById(R.id.listviewCommandeCient);
            MyAdapter adapter = new MyAdapter(getActivity(), lesNoms, Quantites, images, R.layout.row);
            listViewCommandesclients.setAdapter(adapter);
        });
    }

    // ---------------------------------------------

    // ---------------------------------------------
    public void openSignalerCommande() {

        Bundle args = getArguments();
        int idCommande = args.getInt("commande");

        clientGererCommandeFragment nextFrag= new clientGererCommandeFragment();

        Bundle args2 = new Bundle();
        args2.putInt("commande", idCommande);
        nextFrag.setArguments(args2);

        this.getParentFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, nextFrag,"test")
                .addToBackStack(null)
                .commit();
    }

    // ---------------------------------------------

    // ---------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_clientcommande, container, false);

        Bundle args = getArguments();
        boolean valide = args.getBoolean("valide");
        String remarque = args.getString("remarque");

        if(valide || !remarque.equals("null")) {
            view.findViewById(R.id.ButtonSignalerCommande).setEnabled(false);
            view.findViewById(R.id.ButtonConfirmerCommande).setEnabled(false);
        }

        view.findViewById(R.id.ButtonSignalerCommande).setOnClickListener(v -> {
            openSignalerCommande();
        });

        view.findViewById(R.id.ButtonConfirmerCommande).setOnClickListener(v -> {
            ConfirmerCommande();
        });

        return view;
    }
    // ---------------------------------------------

    // ---------------------------------------------

    private void ConfirmerCommande()
    {
        Bundle args = getArguments();
        int idCommande = args.getInt("commande");

        try {
            daoCommande.ConfirmerCommande(clientcommandeFragment.this,idCommande);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------

    // ---------------------------------------------

    public void CompleteConfirmation()
    {
        getActivity().runOnUiThread(() -> {
            MsgBox.ok(
                    getContext(),
                    "Commande confirmer",
                    "Votre commande est desormais terminer",
                    R.drawable.img_user
            );
        });

        Bundle args = getArguments();
        int idCommande = args.getInt("commande");

        clientFragment nextFrag= new clientFragment();

        Bundle args2 = new Bundle();
        args2.putInt("commande", idCommande);
        nextFrag.setArguments(args2);

        this.getParentFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, nextFrag,"test")
                .addToBackStack(null)
                .commit();
    }

    // ---------------------------------------------
}