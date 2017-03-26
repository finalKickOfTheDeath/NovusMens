package com.math.novusmens_git.enigme;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.math.novusmens_git.R;
import com.math.novusmens_git.personnage.Item;
import com.math.novusmens_git.personnage.Joueur;

public class EnigmeRacines extends Enigme {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enigme_racines);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent intent = getIntent();
        if(intent != null){
            setJoueur((Joueur) intent.getExtras().getParcelable("joueur"));
            Log.d("intent", "joueur point temps : " + getJoueur().getTimePoint());
        }
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setNumEnigme(getResources().getInteger(R.integer.level1_enigmeRacine));
        setNumNiveau(getResources().getInteger(R.integer.level1));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 1 il est : " + getNumEnigme());

        findViewById(R.id.btnRacineAme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultat();
            }
        });

    }

    @Override
    public boolean estResolue() {
        return false;
    }

    @Override
    public void resultat() {
        //le joueur recoit un morceau d'ame = item
        Item ame = new Item("Morceau d'âme");
        getJoueur().win(ame);
        //on prepare l'intent de retour vers la map du niveau
        Intent intent = getIntent();
        intent.putExtra("joueur", getJoueur());
        setResult(RESULT_OK, intent);
        //on ouvre le dialog pour montrer les résultat
        showResult(0, ame, "Bravo!");
    }
}
