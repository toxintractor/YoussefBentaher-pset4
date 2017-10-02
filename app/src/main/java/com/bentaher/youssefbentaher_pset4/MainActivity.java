package com.bentaher.youssefbentaher_pset4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText naamTxt, beschrijvingTxt;
    Button invr;
    DBHelper db;
    Taak taak;
    ListView lstView;
    private ArrayList<Taak> taakArray;
    private listAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        naamTxt = (EditText) findViewById(R.id.taakNaam);
        beschrijvingTxt = (EditText) findViewById(R.id.taakBeschrijving);
        invr = (Button) findViewById(R.id.invoeren);


        invr.setOnClickListener(new putWords());

        taakArray = db.getAllData();

        setAdapter();



    }

    public class putWords implements View.OnClickListener {

        public putWords(){
        }

        @Override
        public void onClick(View view) {
            taak = new Taak(naamTxt.getText().toString(), beschrijvingTxt.getText().toString(), "false");
            boolean check = db.insertData(taak);
            naamTxt.setText("");
            beschrijvingTxt.setText("");
            Log.i("check", String.valueOf(check));
            taakArray = db.getAllData();
            setAdapter();
        }
    }

    public  void setAdapter(){
        adapter = new listAdapter(this, taakArray);
        lstView = (ListView) findViewById(R.id.lstView);
        lstView.setAdapter(adapter);

    }

    public class getList implements View.OnClickListener {

        public getList(){
        }

        @Override
        public void onClick(View view) {
            ArrayList<Taak> taken = db.getAllData();

            Intent jumppage = new Intent(MainActivity.this, Main2Activity.class);
            jumppage.putExtra("data", taken);
            startActivity(jumppage);

        }
    }




    /*
    //Opslaan in shareprefferences
    public void saveShared(View view){
        String edtValue = edtTxt.getText().toString();

        SharedPreferences prefs = this.getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("editText", edtValue);
        editor.commit();

    }

    //ophalen ui shareprefferences
    public void loadShared(){

    }

    //ophalen ui shareprefferences
    public void makeIntent(View view){
        Intent jumppage = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(jumppage);
    }
    */
}
