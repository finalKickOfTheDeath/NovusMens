package com.math.novusmens_git.enigme;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.math.novusmens_git.R;
import com.math.novusmens_git.personnage.Joueur;

public class EnigmePointBloqueActivity extends Enigme {

    private static final String ITEM_NEEDED = "Heart key";
    private static final String ITEM_ENIMGE = "Pousse d'espoir";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_point_bloque);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent intent = getIntent();
        if(intent != null){
            setJoueur((Joueur) intent.getExtras().getParcelable("joueur"));
            Log.d("intent", "joueur point temps : " + getJoueur().getTimePoint());
        }
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setNumEnigme(getResources().getInteger(R.integer.level1_enigmePointBloque));
        setNumNiveau(getResources().getInteger(R.integer.level1));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 7 il est : " + getNumEnigme());

        //font perso
        Typeface typeFaceSav = Typeface.createFromAsset(getAssets(),"fonts/savior1.ttf");
        TextView desc = (TextView) findViewById(R.id.txtDescPointBloque);
        TextView take = (TextView) findViewById(R.id.btnTakePointBloque);
        TextView let = (TextView) findViewById(R.id.btnLetPointBloque);
        desc.setTypeface(typeFaceSav);
        take.setTypeface(typeFaceSav);
        let.setTypeface(typeFaceSav);
        desc.setTextSize(25);
        take.setTextSize(25);
        let.setTextSize(25);

        findViewById(R.id.btnTakePointBloque).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("enigme", "on prends la pousse d'espoir");
            }
        });

        findViewById(R.id.btnLetPointBloque).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("enigme", "on laisse la pousse d'espoir");
            }
        });

    }

    @Override
    public boolean estResolue() {
        return false;
    }

    @Override
    public void resultat() {

    }
}
