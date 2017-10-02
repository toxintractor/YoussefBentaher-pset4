package com.bentaher.youssefbentaher_pset4;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mocro on 27/09/2017.
 */

public class listAdapter extends ArrayAdapter {
    private ArrayList<Taak> taken;
    private Context context;
    private MainActivity listActivity;
    DBHelper db;
    CheckBox theCHeckBox;
    Taak taak;


    public listAdapter(Context context, ArrayList<Taak> data) {
        super(context, 0 , data);
        this.taken  = data;
        this.listActivity = (MainActivity) context;
        this.context = context;

        db = new DBHelper(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        LayoutInflater theInflater = LayoutInflater.from(getContext());
        final View theView = theInflater.inflate(R.layout.listlayout, parent, false);

        taak = taken.get(position);
        final String taakNaam = taak.getTaak();

        final TextView theTextView = (TextView) theView.findViewById(R.id.taaktext);

        theTextView.setText(taakNaam);


        theCHeckBox = (CheckBox) theView.findViewById(R.id.checklist);
        theCHeckBox.setChecked(Boolean.parseBoolean(taak.getIschecked()));
        Log.i("check", String.valueOf(theCHeckBox.isChecked()));

        theCHeckBox.setOnClickListener(new keepCHeck());
        theTextView.setOnClickListener(new goItem(taakNaam));

        /*
        theView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, taakNaam, Toast.LENGTH_SHORT).show();
                Intent jumppage = new Intent(context, Main2Activity.class);
                jumppage.putExtra("data", taak);
                context.startActivity(jumppage);
            }

        });


        theCHeckBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(theCHeckBox.isChecked()){
                    Toast.makeText(context, "het is gecheckt", Toast.LENGTH_SHORT).show();
                    taak.setIschecked("true");
                    db.update(taak);
                }
                else{
                    Toast.makeText(context, "het is niet gecheckt", Toast.LENGTH_SHORT).show();
                    taak.setIschecked("false");
                    db.update(taak);
                }
            }

        });
        */







        return theView;
    }

    public int getCount(){
        return  super.getCount();
    }

    class keepCHeck implements View.OnClickListener {

        public keepCHeck(){
        }

        @Override
        public void onClick(View view) {
            if(theCHeckBox.isChecked()){
                Toast.makeText(context, "het is gecheckt", Toast.LENGTH_SHORT).show();
                taak.setIschecked("true");
                db.update(taak);
            }
            else{
                Toast.makeText(context, "het is niet gecheckt", Toast.LENGTH_SHORT).show();
                taak.setIschecked("false");
                db.update(taak);
            }

        }
    }

    class goItem implements View.OnClickListener {
        String taakn;
        public goItem(String tk){
            taakn = tk;
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, taakn, Toast.LENGTH_SHORT).show();
            Intent jumppage = new Intent(context, Main2Activity.class);
            jumppage.putExtra("data", taak);
            context.startActivity(jumppage);

        }
    }


}

