package com.math.novusmens_git.enigme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.math.novusmens_git.R;

public class EnigmeMaisonAbandonneeActivity extends AppCompatActivity {

    private int numNiveau;
    private int numEnigme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigme_maison_abandonnee);

        numEnigme= getResources().getInteger(R.integer.level1_enigmeNarrative1MaisonAbandonne);
        numNiveau = getResources().getInteger(R.integer.level1);
        Log.d("data", "num niveau devrait être 1 il est : " + numNiveau);
        Log.d("data", "num enigme devrait être 0 il est : " + numEnigme);
    }
}
