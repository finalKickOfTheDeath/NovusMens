package com.math.novusmens_git.enigme;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.math.novusmens_git.R;
import com.math.novusmens_git.personnage.Joueur;



public class EnigmeOrdiActivity extends Enigme {

    private final static String PASSWORD = "AnimusRoot12";
    private boolean mdpFind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // pour mettre l'activité en fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enigme_ordi);
        Intent intent = getIntent();
        if(intent != null){
            setJoueur((Joueur) intent.getExtras().getParcelable("joueur"));
            Log.d("intent", "joueur point temps : " + getJoueur().getTimePoint());
        }
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        int i = getResources().getConfiguration().orientation;
        Log.d("orientation", "" + i);

        setNumEnigme(getResources().getInteger(R.integer.level1_enigmeOrdinateur));
        setNumNiveau(getResources().getInteger(R.integer.level1));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 5 il est : " + getNumEnigme());

        Typeface typeFaceSav = Typeface.createFromAsset(getAssets(),"fonts/savior1.ttf");

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            //font perso
            TextView txtMdp = (TextView) findViewById(R.id.txtPassword);
            txtMdp.setTypeface(typeFaceSav);
            txtMdp.setTextSize(60);

            findViewById(R.id.txtPassword).setVisibility(View.INVISIBLE);
            final ProgressBar barDegat = (ProgressBar) findViewById(R.id.progressBarDegat);
            barDegat.setMax(100);
            final ImageButton imgBtnCrane = (ImageButton) findViewById(R.id.imgBtnLapin);

            imgBtnCrane.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(barDegat.getProgress() == barDegat.getMax()) {
                        //on enlève le crane et on affiche le mot de passe
                        imgBtnCrane.setVisibility(View.INVISIBLE);
                        displayPassword();
                    }
                    else {
                        barDegat.incrementProgressBy(5);
                    }
                }
            });
        }
        else {
            Log.d("text", "On est là dans le else");

            TextView txtEnigme = (TextView) findViewById(R.id.txtEnigmeOrdi);
            TextView input = (TextView) findViewById(R.id.txtViewInput);
            EditText edit = (EditText) findViewById(R.id.editTextClavier);
            txtEnigme.setTypeface(typeFaceSav);
            txtEnigme.setTextSize(25);
            input.setTypeface(typeFaceSav);
            input.setTextSize(70);
            edit.setTypeface(typeFaceSav);
            edit.setTextSize(70);

            findViewById(R.id.imgBtnEnter).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("text", "On est là dans le onClick");
                    EditText editText = (EditText) findViewById(R.id.editTextClavier);
                    Log.d("text", String.valueOf(editText.getText()));
                    if(String.valueOf(editText.getText()).equals(PASSWORD)) {
                        Log.d("text", "On est là dans le if");
                        mdpFind = true;
                        resultat();
                    }
                }
            });
        }
    }

    private void displayPassword() {
        TextView password = (TextView) findViewById(R.id.txtPassword);
        password.setText(PASSWORD);
        password.setTextSize(45);
        password.setTextColor(ContextCompat.getColor(this, R.color.white));
        password.setGravity(Gravity.CENTER);
        password.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("data", "on est dans onPause enigme ordi activity");
        saveState();
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("data", "on est dans onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("iut","je suis dans onDestroy");
    }

    @Override
    public boolean estResolue() {
        return(mdpFind);
    }

    @Override
    public void resultat() {
        //on prepare l'intent de retour vers la map du niveau
        Intent intent = getIntent();
        intent.putExtra("joueur", getJoueur());
        setResult(RESULT_OK, intent);
        showResult(0, null, "Dehors, tout n'est que désolation. Saurez-vous trouver la source de la vie ?");
    }

}
