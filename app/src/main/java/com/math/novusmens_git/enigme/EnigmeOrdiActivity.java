package com.math.novusmens_git.enigme;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.media.MediaPlayer;
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

import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.math.novusmens_git.R;
import com.math.novusmens_git.database.PointDAO;
import com.math.novusmens_git.database.PossedePointDAO;
import com.math.novusmens_git.database.Sauvegarde;
import com.math.novusmens_git.database.SauvegardeDAO;
import com.math.novusmens_git.niveau.Niveau;
import com.math.novusmens_git.niveau.Niveau1Activity;
import com.math.novusmens_git.niveau.Point;
import com.math.novusmens_git.personnage.Item;
import com.math.novusmens_git.personnage.Joueur;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class EnigmeOrdiActivity extends Enigme {

    private final static String PASSWORD = "AnimusRoot12";
    private boolean mdpFind = false;
    private final String EXTRA_MUSIQUE = "musique";
    private MediaPlayer player;
    private ArrayList<Point> points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // pour mettre l'activité en fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enigme_ordi);
        Intent intent = getIntent();
        if(intent != null){
           /* player = MediaPlayer.create(this, R.raw.pjs4);
            player.setVolume(100, 100);
            player.seekTo(intent.getIntExtra(EXTRA_MUSIQUE,0));*/
            setJoueur((Joueur) intent.getExtras().getParcelable("joueur"));
            points = intent.getExtras().getParcelable("listePoint");
            Log.d("intent", "joueur point temps : " + getJoueur().getTimePoint());
            Log.d("intent", "liste de point passée par l'intent");
            for(Point p : points) {
                Log.d("intent", "point : " + p.getId() + " resolu : " + p.isResolu());
            }
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
        /*if(player != null) {
            player.start();
            player.setLooping(true);
        }*/
    }

    @Override
    protected void onPause() {
        Log.d("data", "on est dans onPause enigme ordi activity");
        saveState();
        super.onPause();
        //player.stop();
        //player.stop();
        /*
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
            sauvegardeDAO.ajouter(new Sauvegarde(now, getJoueur().getTimePoint(), getNumNiveau()));
            last = sauvegardeDAO.selectionSave();
            Log.d("data", "ce qu'il y a dans la dernière sauvegarde");
            Log.d("data", "id : " + last.getId());
            Log.d("data", "date : " + last.getDate());
            Log.d("data", "point de temps : " + last.getPointTemps());
            Log.d("data", "numNiveau : " + last.getNumNiveau());
        }
        //on updateResolu la sauvegarde
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.FRANCE);
        String now = format.format(new Date().getTime());
        last.setDate(now);
        last.setPointTemps(getJoueur().getTimePoint());
        sauvegardeDAO.update(last);
        Log.d("data", "ce qu'il y a dans la sauvegarde updateResolu");
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
        //on insert le point resolu
        if(estResolue()) {
            //on update le point resolu
            Log.d("data", "point to be updateResolu : " + points.get(getNumEnigme()).getId() + " " + points.get(getNumEnigme()).isResolu());
            pointDAO.updateResolu(points.get(getNumEnigme()));
            Log.d("data", "liste de point apres l'updateResolu");
            for(int i = 0; i < points.size(); i++) {
                Log.d("data", "point : " + points.get(i).getId() + " resolu = " + points.get(i).isResolu());
            }
            PossedePointDAO possedePointDAO = new PossedePointDAO(this);
            possedePointDAO.open();
            possedePointDAO.ajouter(last.getId(), points.get(getNumEnigme()).getId());
            Log.d("data", "liste des points resolus");
            ArrayList<Point> pointsResolus = possedePointDAO.selectionner(last);
            for(int j = 0; j < pointsResolus.size(); j++) {
                Log.d("data", "point : " + pointsResolus.get(j).getId());
            }
            possedePointDAO.close();
        }
        pointDAO.close();
        sauvegardeDAO.close();
        */
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("data", "on est dans onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        /*if(player != null){
            player.release();
            player=null;
        }*/
        Log.i("iut","je suis dans onDestroy");
    }

    @Override
    public boolean estResolue() {
        return(mdpFind);
    }

    @Override
    public void resultat() {
        //on met à jour le point resolu
        points.get(getNumEnigme()).setResolu(true);
        //on prepare l'intent de retour vers la map du niveau
        Intent intent = getIntent();
        intent.putExtra("joueur", getJoueur());
        intent.putExtra("listePoint", points);
        setResult(RESULT_OK, intent);
        showResult(0, null, "Dehors, tout n'est que désolation. Saurez-vous trouver la source de la vie ?");
    }

}
