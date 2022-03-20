package com.example.biorelais_android.ui.client;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.biorelais_android.dto.dtoCommande;
import com.example.biorelais_android.dto.dtoLigneCommande;
import com.example.biorelais_android.dto.dtoProduit;
import com.example.biorelais_android.dto.dtoProposer;
import com.example.biorelais_android.lib.Display;
import com.example.biorelais_android.ui.MainActivity;
import com.example.biorelais_android.R;
import com.example.biorelais_android.dao.daoCommande;
import com.example.biorelais_android.lib.MsgBox;
import com.example.biorelais_android.lib.MyAdapter2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class clientGererCommandeFragment extends Fragment {

    // ---------------------------------------------
    private Fragment frg = this;
    ArrayList<Integer> lesIdProduits = new ArrayList<>();
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_client_gerer_commande, container, false);

        view.findViewById(R.id.ButtonSignaler).setOnClickListener(v -> {
            Signalement();
        });

        return view;
    }
    // ---------------------------------------------

    // ---------------------------------------------
    private void Signalement()
    {
        Bundle args = getArguments();
        int idCommande = args.getInt("commande");

        ArrayList<Long> spinnerValues = new ArrayList<>();

        ListView myListView =  getView().findViewById( R.id.listviewCommandeCient );

        for( int i = 0; i < myListView.getChildCount(); i++ ) {

            Spinner Spin = myListView.getChildAt(i).findViewById(R.id.spinnerSignaler);
            spinnerValues.add(Spin.getItemIdAtPosition(Spin.getSelectedItemPosition()));
        }

        EditText editText = (EditText) getView().findViewById(R.id.TextAreaRemarque);
        String value = editText.getText().toString();

        try {
            daoCommande.Signalement(clientGererCommandeFragment.this,idCommande,value,spinnerValues,lesIdProduits);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // ---------------------------------------------

    // ---------------------------------------------
    private void remplirCommande()
    {
        Bundle args = getArguments();
        int idCommande = args.getInt("commande");

        try {
            daoCommande.InfosCommande2(this,idCommande, MainActivity.currentSession.getValeur());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // ---------------------------------------------

    // ---------------------------------------------
    public void CompleteSignalement()
    {
        getActivity().runOnUiThread(() -> {
            MsgBox.ok(
                    getContext(),
                    "Signalement",
                    "Votre signalement a ete enregistrer avec succes",
                    R.drawable.img_user
            );

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
        });
    }
    // ---------------------------------------------

    // ---------------------------------------------
    public void CommandeRempli(LinkedHashMap<dtoProduit, Object[]> lesProduits )
    {
        dtoLigneCommande prlg = (dtoLigneCommande)lesProduits
                .entrySet()
                .iterator()
                .next()
                .getValue()[0];
        dtoCommande command = prlg.getCommande();

        Display.changeText(frg, R.id.TitrePageGerer, "Commande n°"+ command.getIdCommande());

        ArrayList<String> lesNoms = new ArrayList<>();
        ArrayList<String> Quantites = new ArrayList<>();
        ArrayList<Bitmap> images = new ArrayList<>();
        ArrayList<ArrayList> values = new ArrayList<>();


        for(dtoProduit leProduit : lesProduits.keySet()) {

            ArrayList<Integer> lesValeurs = new ArrayList<>();
            lesIdProduits.add(leProduit.getIdProduit());
            Bitmap img = null;
            try {
                String url = Display.findImgUrl(leProduit);
                img = Display.downloadImageFromURL(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(img == null) {
                img = Display.getDraw(frg, R.drawable.img_defaut).getBitmap();
            }

            images.add(img);

            Object[] obg = lesProduits.get(leProduit);
            dtoLigneCommande ligne = (dtoLigneCommande)obg[0];
            dtoProposer prop = (dtoProposer)obg[1];

            lesNoms.add(leProduit.getNom());
            Quantites.add("Quantité à livrer : " + ligne.getQuantiteALivrer() + " prix " + prop.getPrix());

            for(int t=0;t<ligne.getQuantiteALivrer()+1;t++) {
                lesValeurs.add(t);
            }
            values.add(lesValeurs);
        }

        getActivity().runOnUiThread(() -> {

            ListView listViewCommandesclients = getView().findViewById(R.id.listviewCommandeCient);
            MyAdapter2 adapter = new MyAdapter2(getActivity(), lesNoms, Quantites, images,values,R.layout.row2);
            listViewCommandesclients.setAdapter(adapter);
        });
    }
    // ---------------------------------------------
}