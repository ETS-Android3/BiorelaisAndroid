package com.example.biorelais_android.lib;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.biorelais_android.R;

import java.util.ArrayList;

public class MyAdapter2 extends ArrayAdapter {

    // ---------------------------------------------
    private final ArrayList<String> title;
    private final ArrayList<String> description;
    private final ArrayList<Bitmap> image;
    private final int idLayout;
    private final Activity context;
    ArrayList<ArrayList> values;
    // ---------------------------------------------



    // ---------------------------------------------
    public MyAdapter2 (
            Activity context,
            ArrayList<String> title,
            ArrayList<String> description,
            ArrayList<Bitmap> image,
            ArrayList<ArrayList> values,
            int idLayout) {
        super(context, idLayout, description);
        this.title = title;
        this.description = description;
        this.image = image;
        this.idLayout = idLayout;
        this.context = context;
        this.values=values;
    }
    // ---------------------------------------------

    // ---------------------------------------------
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        if(convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            row = inflater.inflate(idLayout, null, true);
        } else {
            row = convertView;
        }

        TextView textViewTitle = row.findViewById(R.id.textView1);
        TextView textViewDescription = row.findViewById(R.id.textView2);
        ImageView imageViewImage = row.findViewById(R.id.image);

        Spinner monSpin =  (Spinner) row.findViewById(R.id.spinnerSignaler);

        ArrayAdapter<String> a = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, values.get(position));
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monSpin.setAdapter(a);

        textViewTitle.setText(title.get(position));
        textViewDescription.setText(description.get(position));
        imageViewImage.setImageBitmap(image.get(position));

        return  row;
    }
    // ---------------------------------------------

}
