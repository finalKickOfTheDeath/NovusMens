package com.math.novusmens_git.niveau;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.math.novusmens_git.R;
import com.math.novusmens_git.enigme.EnigmeBlocsActivity;
import com.math.novusmens_git.enigme.EnigmeDesertMagnetiqueActivity;
import com.math.novusmens_git.enigme.EnigmeJarresActivity;
import com.math.novusmens_git.enigme.EnigmeMaisonAbandonneeActivity;
import com.math.novusmens_git.enigme.EnigmeOrdiActivity;
import com.math.novusmens_git.enigme.EnigmePNJActivity;
import com.math.novusmens_git.enigme.EnigmePluiesAcidesActivity;
import com.math.novusmens_git.enigme.EnigmeRacines;
import com.math.novusmens_git.enigme.EnigmePointBloqueActivity;
import com.math.novusmens_git.enigme.EnigmeSortie;
import com.math.novusmens_git.menu.NarrationActivity;
import com.math.novusmens_git.personnage.Joueur;

public class Niveau1Activity extends AppCompatActivity {
    // on met le package d'où provient l'intent
    public final static String EXTRA_MESSAGE = "com.math.novusmens_git.niveau.TOWARDENIGME";
    final String EXTRA_MUSIQUE = "musique";
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // on récupère l'intent qui a lancé l'activité
        Intent intent = getIntent();
        if(intent != null){
            player = MediaPlayer.create(this, R.raw.pjs4_menu);
            player.setVolume(100, 100);
            player.seekTo(intent.getIntExtra(EXTRA_MUSIQUE,0));
        }
        // pour mettre l'activité en fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_niveau1);
        //forcer l'orientation en mode paysage
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // cacher l'action bar
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        findViewById(R.id.btn1_narrationMaisonAbandonnee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("data", "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeMaisonAbandonneeActivity.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme maison abandonnee");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn2_enigmeRacine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("data", "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeRacines.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme des racines");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn3_pnj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("data", "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmePNJActivity.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme pnj");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn4_enigmeJarres).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("data", "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeJarresActivity.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme jarres");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn5_sortie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("data", "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeSortie.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme sortie");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn6_enigmeOrdi).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("data", "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeOrdiActivity.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme ordi");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn7_narrationDesertMagnetique).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("data", "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeDesertMagnetiqueActivity.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme desert magnetique");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn8_pointBloqué).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("data", "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmePointBloqueActivity.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme point bloqué");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn9_enigmeBloc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("data", "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeBlocsActivity.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme blocs");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn10_narrationPluiesAcides).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("data", "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmePluiesAcidesActivity.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme pluies acides");
                startActivity(intent);
            }
        });

        Toast.makeText(this, "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(Joueur.getTimePoint()<=0){
            Toast.makeText(this, "Vous avez perdu", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, NarrationActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            if (player == null) {
                player = MediaPlayer.create(this, R.raw.pjs4_menu);
                player.setVolume(100, 100);
            }
            player.start();
            player.setLooping(true);
            Toast.makeText(this, "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps", Toast.LENGTH_SHORT).show();
        }
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
    protected void onDestroy(){
        super.onDestroy();
        if(player != null){
            player.release();
            player=null;
        }
        Log.i("iut","je suis dans onDestroy");
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {

        Log.i("iut","Je suis dans onActivityResult ");
        // on récupère le statut de retour de l'activité 2 c'est à dire l'activité numéro 1000
        //if(requestCode==1000){
            // si le code de retour est égal à 1 on stoppe l'activité 1
            if(resultCode==1){
                finish();
            }
       // }
        super.onActivityResult (requestCode, resultCode, data);
    }


}
