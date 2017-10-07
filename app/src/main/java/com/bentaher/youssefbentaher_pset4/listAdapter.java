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
    Taak tk;


    public listAdapter(Context context, ArrayList<Taak> data) {
        super(context, 0 , data);
        this.taken  = data;
        this.listActivity = (MainActivity) context;
        this.context = context;

        db = DBHelper.getInsanse(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        //LayoutInflater theInflater = LayoutInflater.from(getContext());
        //final View theView = theInflater.inflate(R.layout.listlayout, parent, false);

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listlayout, parent, false);
        }


        final Taak tk = taken.get(position);
        final String taakNaam = tk.getTaak();

        final TextView theTextView = (TextView) view.findViewById(R.id.taaktext);

        theTextView.setText(taakNaam);


        final CheckBox theCHeckBox = (CheckBox) view.findViewById(R.id.checklist);
        theCHeckBox.setChecked(Boolean.parseBoolean(tk.getIschecked()));
        Log.i("check", String.valueOf(theCHeckBox.isChecked()));

        theCHeckBox.setOnClickListener(new keepCHeck(theCHeckBox, tk));
        theTextView.setOnClickListener(new goItem(tk));

        /*
        theTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, tk.getTaak(), Toast.LENGTH_SHORT).show();
                Intent jumppage = new Intent(context, Main2Activity.class);
                jumppage.putExtra("data", tk);
                context.startActivity(jumppage);
            }

        });


        theCHeckBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(theCHeckBox.isChecked()){
                    Toast.makeText(context, "het is gecheckt", Toast.LENGTH_SHORT).show();
                    tk.setIschecked("true");
                    db.update(tk);
                }
                else{
                    Toast.makeText(context, "het is niet gecheckt", Toast.LENGTH_SHORT).show();
                    tk.setIschecked("false");
                    db.update(tk);
                }
            }

        });
        */



        return view;
    }

    public int getCount(){
        return  super.getCount();
    }

    class keepCHeck implements View.OnClickListener {

        CheckBox ckBox;
        Taak tk1;
        public keepCHeck(CheckBox chBox, Taak tk2){
             ckBox = chBox;
            tk1 = tk2;
        }

        @Override
        public void onClick(View view) {
            if(ckBox.isChecked()){
                Toast.makeText(context, "het is gecheckt", Toast.LENGTH_SHORT).show();
                tk1.setIschecked("true");
                db.update(tk1);
            }
            else{
                Toast.makeText(context, "het is niet gecheckt", Toast.LENGTH_SHORT).show();
                tk1.setIschecked("false");
                db.update(tk1);
            }

        }
    }

    class goItem implements View.OnClickListener {
        Taak tk1;
        public goItem(Taak tk2){
            tk1 = tk2;
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(context, tk1, Toast.LENGTH_SHORT).show();
            Intent jumppage = new Intent(context, Main2Activity.class);
            jumppage.putExtra("data", tk1);
            context.startActivity(jumppage);

        }
    }


}

