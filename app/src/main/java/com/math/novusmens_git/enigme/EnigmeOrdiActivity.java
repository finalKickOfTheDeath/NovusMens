package com.math.novusmens_git.enigme;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.math.novusmens_git.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.math.novusmens_git.database.PointDAO;
import com.math.novusmens_git.database.PossedePointDAO;
import com.math.novusmens_git.database.Sauvegarde;
import com.math.novusmens_git.database.SauvegardeDAO;
import com.math.novusmens_git.niveau.IEnigme;
import com.math.novusmens_git.niveau.Niveau;
import com.math.novusmens_git.niveau.Niveau1Activity;
import com.math.novusmens_git.niveau.Point;
import com.math.novusmens_git.personnage.Joueur;


public class EnigmeOrdiActivity extends AppCompatActivity implements IEnigme {

    private final static String PASSWORD = "AnimusRoot12";
    private int numNiveau;
    private int numEnigme;
    private boolean mdpFind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigme_ordi);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        int i = getResources().getConfiguration().orientation;
        Log.d("orientation", "" + i);

        numEnigme= getResources().getInteger(R.integer.level1_enigmeOrdinateur);
        numNiveau = getResources().getInteger(R.integer.level1);
        Log.d("data", "num niveau devrait être 1 il est : " + numNiveau);
        Log.d("data", "num enigme devrait être 5 il est : " + numEnigme);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            findViewById(R.id.txtPassword).setVisibility(View.INVISIBLE);
            final ProgressBar barDegat = (ProgressBar) findViewById(R.id.progressBarDegat);
            barDegat.setMax(100);
            final ImageButton imgBtnCrane = (ImageButton) findViewById(R.id.imgBtnCrane);

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
            findViewById(R.id.imgBtnEnter).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("text", "On est là dans le onClick");
                    EditText editText = (EditText) findViewById(R.id.editTextClavier);
                    Log.d("text", String.valueOf(editText.getText()));
                    if(String.valueOf(editText.getText()).equals(PASSWORD)) {
                        Log.d("text", "On est là dans le if");
                        mdpFind = true;
                        Toast.makeText(getApplicationContext(), "Enigme résolue !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(v.getContext(), Niveau1Activity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    }

    private void displayPassword() {
        TextView password = (TextView) findViewById(R.id.txtPassword);
        password.setText(PASSWORD);
        password.setTextSize(45);
        password.setGravity(Gravity.CENTER);
        password.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Checks the orientation of the screen
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        Log.d("data", "on est dans onPause");
        super.onPause();
        //on sauvegarde l'état du jeu
        SauvegardeDAO sauvegardeDAO = new SauvegardeDAO(this);
        sauvegardeDAO.open();
        Sauvegarde last = sauvegardeDAO.selectionSave();
        if(last == null) {
            Log.d("data", "pas de sauvegarde dans enimge ordi --> on la créée");
            //initialisation des points dans la base de données
            initPoint();
            //creation de la premiere sauvegarde
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE);
            String now = format.format(new Date().getTime());
            //on insert la sauvegarde dans la base
            sauvegardeDAO.ajouter(new Sauvegarde(now, Joueur.getTimePoint(), numNiveau));
            last = sauvegardeDAO.selectionSave();
            Log.d("data", "ce qu'il y a dans la dernière sauvegarde");
            Log.d("data", "id : " + last.getId());
            Log.d("data", "date : " + last.getDate());
            Log.d("data", "point de temps : " + last.getPointTemps());
            Log.d("data", "numNiveau : " + last.getNumNiveau());
        }
        //on update la sauvegarde
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE);
        String now = format.format(new Date().getTime());
        last.setDate(now);
        last.setPointTemps(Joueur.getTimePoint());
        sauvegardeDAO.update(last);
        Log.d("data", "ce qu'il y a dans la sauvegarde update");
        Log.d("data", "id : " + last.getId());
        Log.d("data", "date : " + last.getDate());
        Log.d("data", "point de temps : " + last.getPointTemps());
        Log.d("data", "numNiveau : " + last.getNumNiveau());
        //on recupere la liste de point
        PointDAO pointDAO = new PointDAO(this);
        pointDAO.open();
        ArrayList<Point> points = pointDAO.selectionner();
        Log.d("data", "ce qu'il y a dans la liste de point");
        for(int i = 0; i < points.size(); i++) {
            Log.d("data", "point : " + points.get(i).getId() + " resolu = " + points.get(i).isResolu());
        }
        pointDAO.close();
        //on insert le point resolu
        if(estResolue()) {
            PossedePointDAO possedePointDAO = new PossedePointDAO(this);
            possedePointDAO.open();
            possedePointDAO.ajouter(last.getId(), points.get(numEnigme).getId());
            Log.d("data", "liste des points resolus");
            ArrayList<Point> pointsResolus = possedePointDAO.selectionner(last);
            for(int j = 0; j < pointsResolus.size(); j++) {
                Log.d("data", "point : " + pointsResolus.get(j).getId());
            }
            possedePointDAO.close();
        }
        sauvegardeDAO.close();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("data", "on est dans onStop");
    }

    @Override
    public boolean estResolue() {
        return(mdpFind);
    }

    private void initPoint() {
        //creation du niveau 1 et de ses points
        Niveau niveau1 = new Niveau(1);
        //recuperation des points du niveau 1
        ArrayList<Point> points = niveau1.getPoints();
        Log.d("data", "ce qu'il y a dans l'arrayList de point");
        for(int i = 0; i < points.size(); i++) {
            Log.d("data", "point : " + points.get(i).getId() + "resolu = " + points.get(i).isResolu());
        }
        PointDAO pointDAO = new PointDAO(this);
        pointDAO.open();
        //on met les points dans la base de donnée
        for(int i = 0; i < points.size(); i++) {
            pointDAO.ajouter(points.get(i));
        }
        pointDAO.close();
    }
}
