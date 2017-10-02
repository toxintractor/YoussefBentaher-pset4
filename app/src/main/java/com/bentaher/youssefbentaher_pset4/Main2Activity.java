package com.bentaher.youssefbentaher_pset4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    EditText naam, beschrijving;
    TextView txtBeschrijving;
    Button upDate, deLete;
    Taak taakObject;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new DBHelper(this);
        taakObject = (Taak) getIntent().getExtras().getSerializable("data");

        beschrijving = (EditText) findViewById(R.id.taakBeschrijving);
        naam = (EditText) findViewById(R.id.taakNaam);
        upDate  = (Button) findViewById(R.id.updaten);
        deLete = (Button) findViewById(R.id.deleten);
        txtBeschrijving = (TextView) findViewById(R.id.textBeschrijving);



        deLete.setOnClickListener(new Main2Activity.DeLete());
        upDate.setOnClickListener(new Main2Activity.UpDate());
        txtBeschrijving.setText(taakObject.getBeschrijving());





    }

    public class UpDate implements View.OnClickListener {

        public UpDate(){
        }

        @Override
        public void onClick(View view) {
            String snaam = naam.getText().toString();
            String sbeschrijving = beschrijving.getText().toString();

            if(!snaam.matches("")){
                taakObject.setTaak(naam.getText().toString());
            }

            if(!sbeschrijving.matches("")){
                taakObject.setBeschrijving(beschrijving.getText().toString());
            }


            db.update(taakObject);
            beschrijving.setText("");
            naam.setText("");
            txtBeschrijving.setText(taakObject.getBeschrijving());


        }
    }

    public class DeLete implements View.OnClickListener {

        public DeLete(){
        }

        @Override
        public void onClick(View view) {
            db.delete(taakObject);

            Intent jumppage = new Intent(Main2Activity.this, Main2Activity.class);
            startActivity(jumppage);

        }
    }

    public void onBackPressed() {
        Intent jumppage = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(jumppage);

    }


}
