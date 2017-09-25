package com.bentaher.youssefbentaher_pset4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTxt = (EditText) findViewById(R.id.editInvoer);
    }

    //Opslaan in shareprefferences
    public void saveShared(View view){
        String edtValue = edtTxt.getText().toString();

        SharedPreferences prefs = this.getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("editText", edtValue);
        editor.commit();

    }

    //ophalen ui shareprefferences
    public void loadShared(View view){

    }

    //ophalen ui shareprefferences
    public void makeIntent(View view){
        Intent jumppage = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(jumppage);
    }
}
