package com.example.jr.applipersistancebdd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView)findViewById(R.id.ChapitreListView);
        ChapitreBaseSQLite db = new ChapitreBaseSQLite(this);

        Log.d("Insert: ", "Inserting ..");
        db.addChapitre(new Chapitre("Test", " Tetstdsvuyfbfjberkgnerokg,erigkenoe"));
        db.addChapitre(new Chapitre("Test1", " Tetstdsvuyfbfjberkgnerokg,erigkenoe"));
        db.addChapitre(new Chapitre("Test2", " Tetstdsvuyfbfjberkgnerokg,erigkenoe"));


        Log.d("Reading: ", "Reading all Chapitres..");
        List<Chapitre> chapitreList = db.getAllChapitre();

        for (Chapitre chapitre : chapitreList) {
            String log = "Id: " + chapitre.getId() + " ,Name: " + chapitre.getName() + " ,Address: " + chapitre.getDescription();
            Log.d("Chapitre: : ", log);
        }
    }
}
