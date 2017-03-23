package enigme;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
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

import database.PointDAO;
import database.PossedePointDAO;
import database.Sauvegarde;
import database.SauvegardeDAO;
import niveau.IEnigme;
import niveau.Niveau;
import niveau.Niveau1Activity;
import niveau.Point;
import personnage.Joueur;


public class EnigmeOrdiActivity extends AppCompatActivity implements IEnigme {

    private final static String PASSWORD = "AnimusRoot12";
    private final static int NUM_NIVEAU = 1;
    private final static int NUM_ENGME = 0;
    private boolean mdpFind = false;
    final String EXTRA_MUSIQUE = "musique";
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigme_ordi);
        Intent intent = getIntent();
        if(intent != null){
            player = MediaPlayer.create(this, R.raw.pjs4);
            player.setVolume(100, 100);
            player.seekTo(intent.getIntExtra(EXTRA_MUSIQUE,0));
        }
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        int i = getResources().getConfiguration().orientation;
        Log.d("orientation", "" + i);

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
        if(player != null) {
            player.start();
            player.setLooping(true);
        }
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
        player.stop();
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
            sauvegardeDAO.ajouter(new Sauvegarde(now, Joueur.getTimePoint(), NUM_NIVEAU));
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
            possedePointDAO.ajouter(last.getId(), points.get(NUM_ENGME).getId());
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
    protected void onDestroy(){
        super.onDestroy();
        if(player != null){
            player.release();
            player=null;
        }
        Log.i("iut","je suis dans onDestroy");
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
