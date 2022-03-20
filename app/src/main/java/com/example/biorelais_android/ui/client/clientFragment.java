package com.example.biorelais_android.ui.client;

import androidx.fragment.app.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.biorelais_android.lib.Display;
import com.example.biorelais_android.ui.MainActivity;
import com.example.biorelais_android.R;
import com.example.biorelais_android.dao.daoCommande;
import com.example.biorelais_android.dto.dtoCommande;
import com.example.biorelais_android.lib.MyAdapter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class clientFragment extends Fragment implements AdapterView.OnItemSelectedListener
{
    // ---------------------------------------------
    String [] values = {"Toutes","Aujourd'hui","En cours","Valide","Signaler"};
    private Fragment frg = this;
    // ---------------------------------------------

    // ---------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        remplirListeCommande("Toutes");
    }
    // ---------------------------------------------

    // ---------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client, container, false);

        Spinner spinner = view.findViewById(R.id.spinnerFiltre);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_dropdown_item,values);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return view;
    }
    // ---------------------------------------------

    private void remplirListeCommande(String filtre)
    {
        try {
            daoCommande.ToutesLesCommandesDuClient(this, MainActivity
                    .currentSession
                    .getUtilisateur()
                    .getIdUtilisateur()
                    ,MainActivity.currentSession,filtre);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------

    private void openCommandeClient(dtoCommande laCommande)
    {
        clientcommandeFragment nextFrag= new clientcommandeFragment();

        Bundle args = new Bundle();
        args.putInt("commande", laCommande.getIdCommande());
        args.putBoolean("valide", laCommande.isValidee());
        args.putString("remarque", laCommande.getRemarque());
        nextFrag.setArguments(args);

        this.getParentFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment ,nextFrag,"test")
                .addToBackStack(null)
                .commit();
    }

    // ---------------------------------------------

    public void CompleteCommande(ArrayList<dtoCommande> lesCommandes)
    {
        getActivity().runOnUiThread(() -> {

            if(lesCommandes!=null)
            {
                ArrayList mTitle =new ArrayList() ;
                ArrayList mDescription = new ArrayList();
                ArrayList<Bitmap> images = new ArrayList<>();
                SimpleDateFormat df =new SimpleDateFormat("dd MMMM. yyyy");
                Date date;

                for (dtoCommande uneCommande:lesCommandes)
                {
                    mTitle.add("Commande n "+ uneCommande.getIdCommande());
                    date = uneCommande.getDate();
                    String dateString = df.format(date);
                    mDescription.add(dateString);
                    Bitmap img = Display.getDraw(frg, R.drawable.img_commande).getBitmap();
                    images.add(img);
                }

                ListView listViewCommandesclients = getView().findViewById(R.id.listviewCommandeCient);
                MyAdapter adapter = new MyAdapter(getActivity(), mTitle, mDescription, images,R.layout.row);
                listViewCommandesclients.setAdapter(adapter);

                listViewCommandesclients.setOnItemClickListener((parent, view, position, id) ->
                {
                    //Ouverture de la commande//
                    openCommandeClient(lesCommandes.get(position));
                });
            }
            else
            {
                ListView listViewCommandesclients = getView().findViewById(R.id.listviewCommandeCient);
                MyAdapter adapter = new MyAdapter(getActivity(), new ArrayList(),new ArrayList(), new ArrayList(),R.layout.row);
                listViewCommandesclients.setAdapter(adapter);
            }
        }) ;
    }

    // ---------------------------------------------

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this.getContext(), values[position], Toast.LENGTH_LONG).show();
        remplirListeCommande(values[position]);
    }

    // ---------------------------------------------

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    // ---------------------------------------------
}