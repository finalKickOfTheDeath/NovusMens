package com.math.novusmens_git.menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.math.novusmens_git.R;

import java.io.IOException;

import database.Sauvegarde;
import database.SauvegardeDAO;
import niveau.Niveau1Activity;
import personnage.Joueur;

public class MenuActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private static final String JOUEUR = "JOUEUR";
    private static final String PREF_PTEMPS = "PREFS_TEMSP";
    private static final String PREFS_NAME = "PREFS_NAME";
    private static final int POINT_TEMPS_START = 12;
    final String EXTRA_MUSIQUE = "musique";
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // bloque le téléphone en paysage
        super.onCreate(savedInstanceState);
        // pour mettre l'activité en fullscreen
        // a mettre juste apres super.onCreate
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide(); // cache la barre de menu

        sharedPreferences = getBaseContext().getSharedPreferences(JOUEUR,MODE_PRIVATE);

        findViewById(R.id.btnJouer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commencer(v);
            }
        });

        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset(v);
            }
        });

        //Test pour musique
        player = MediaPlayer.create(this, R.raw.pjs4);
        player.setVolume(100, 100);
    }

    protected void commencer(View view){
        //Intent intent = new Intent(this, NarrationActivity.class);
        //startActivity(intent);
        //this.onStop();

        //Recupération de la dernière sauvegarde dans la base de donnée si elle existe
        SauvegardeDAO sauvegardeDAO = new SauvegardeDAO(this);
        sauvegardeDAO.open();
        Sauvegarde s = sauvegardeDAO.selectionSave();
        if(s == null) {
            //pas de sauvegarde existante
            Log.d("data", "pas de sauvegarde recuperee --> nouvelle partie");
            //creation du joueur
            Joueur j = new Joueur(POINT_TEMPS_START);
            sauvegardeDAO.close();
            Intent intent = new Intent(this, NarrationActivity.class);
            intent.putExtra(EXTRA_MUSIQUE,player.getCurrentPosition()); //sauvegarde la position courrante de la musique
            startActivity(intent);
        }
        else {
            //on a recuperer la derniere sauvegarde
            Log.d("data", "derniere sauvegarde recuperee --> reprendre partie");
            //creation du joueur
            Joueur j = new Joueur(s.getPointTemps());
            Toast.makeText(this, "Une partie à déja été commencé. Nous sommes toujours en développement", Toast.LENGTH_SHORT).show();
            sauvegardeDAO.close();
            Intent intent = new Intent(this, Niveau1Activity.class);
            intent.putExtra(EXTRA_MUSIQUE,player.getCurrentPosition()); //sauvegarde la position courrante de la musique
            startActivity(intent);
        }
        /*
        if(sharedPreferences.contains(PREF_PTEMPS)){ //&& sharedPreferences.contains(PREFS_NAME)
            int time = sharedPreferences.getInt(PREF_PTEMPS,0);
            //Création du joueur
            Joueur j = new Joueur(time);
            //String name = sharedPreferences.getString(PREFS_NAME,null);
            //Toast.makeText(this,"Bonjour " + name + ", vous avez " + age +"ans",Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Une partie à déja été commencé. Nous sommes toujours en développement", Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(this, Niveau1Activity.class);
            Intent intent = new Intent(this, EnigmeOrdiActivity.class);
            startActivity(intent);
        }
        else { // si aucune information n'est trouvées, on en ajoute un
            sharedPreferences.edit().putInt(PREF_PTEMPS, 20).apply(); //.putString(PREFS_NAME, "Alexis")
            Joueur j = new Joueur(20);
            //Toast.makeText(this, "Nous avons sauvegarder vos informations. Redémarrer pour mettre a jour", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, NarrationActivity.class);
            startActivity(intent);
            //this.onStop();
        }
        */
    }

    protected void reset(View view){
        //on reset la base de donnée
        SauvegardeDAO sauvegardeDAO = new SauvegardeDAO(this);
        sauvegardeDAO.open();
        sauvegardeDAO.reset();
        sauvegardeDAO.close();
        //sharedPreferences.edit().remove(PREF_PTEMPS).apply(); //.remove(PREFS_NAME)
        Toast.makeText(this, "Vous avez réinitialisé votre partie. Amusez-vous bien !", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(player != null){
            player.release();
            player=null;
        }
        Log.i("iut","je suis dans onPause");
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(Joueur.getTimePoint()>-1){
            sharedPreferences.edit().putInt(PREF_PTEMPS, Joueur.getTimePoint()).apply();
        }
        if(Joueur.getTimePoint()<=0){
            sharedPreferences.edit().remove(PREF_PTEMPS).apply();
        }
        if(player == null) {
            player = MediaPlayer.create(this, R.raw.pjs4);
            player.setVolume(100, 100);
        }
        player.start();
        player.setLooping(true);
        Log.i("iut","je suis dans onResume");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("iut","je suis dans onStop");
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

    private void initBase() {

    }
}
