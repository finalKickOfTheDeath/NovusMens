package com.math.novusmens_git.niveau;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.javiersantos.bottomdialogs.BottomDialog;
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
import com.math.novusmens_git.personnage.Item;
import com.math.novusmens_git.personnage.Joueur;
import com.merkmod.achievementtoastlibrary.AchievementToast;

public class Niveau1Activity extends AppCompatActivity {

    private static  final int REQUEST_RETOURJOUEUR = 100;

    private final String EXTRA_MUSIQUE = "musique";
    private MediaPlayer player;
    private Joueur joueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //on récupère l'intent qui a lancé l'activité
        Intent intent = getIntent();
        if(intent != null){
            player = MediaPlayer.create(this, R.raw.pjs4_menu);
            player.setVolume(100, 100);
            player.seekTo(intent.getIntExtra(EXTRA_MUSIQUE,0));
            joueur = intent.getExtras().getParcelable("joueur");
            Log.d("intent", "joueur point temps : " + joueur.getTimePoint());
        }
        // pour mettre l'activité en fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_niveau1);
        //forcer l'orientation en mode paysage
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //cacher l'action bar
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        findViewById(R.id.btn1_narrationMaisonAbandonnee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joueur.move();
                Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeMaisonAbandonneeActivity.class);
                intent.putExtra("joueur", joueur);
                startActivityForResult(intent, REQUEST_RETOURJOUEUR);
            }
        });

        findViewById(R.id.btn2_enigmeRacine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joueur.move();
                Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeRacines.class);
                intent.putExtra("joueur", joueur);
                startActivityForResult(intent, REQUEST_RETOURJOUEUR);
            }
        });

        findViewById(R.id.btn3_pnj).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joueur.move();
                Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmePNJActivity.class);
                intent.putExtra("joueur", joueur);
                startActivityForResult(intent, REQUEST_RETOURJOUEUR);
            }
        });

        findViewById(R.id.btn4_enigmeJarres).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                joueur.move();
                Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeJarresActivity.class);
                intent.putExtra("joueur", joueur);
                startActivityForResult(intent, REQUEST_RETOURJOUEUR);
            }
        });

        findViewById(R.id.btn5_sortie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joueur.move();
                Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeSortie.class);
                intent.putExtra("joueur", joueur);
                startActivityForResult(intent, REQUEST_RETOURJOUEUR);
            }
        });

        findViewById(R.id.btn6_enigmeOrdi).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                joueur.move();
                Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeOrdiActivity.class);
                intent.putExtra("joueur", joueur);
                startActivityForResult(intent, REQUEST_RETOURJOUEUR);
            }
        });

        findViewById(R.id.btn7_narrationDesertMagnetique).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joueur.move();
                Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeDesertMagnetiqueActivity.class);
                intent.putExtra("joueur", joueur);
                startActivityForResult(intent, REQUEST_RETOURJOUEUR);
            }
        });

        findViewById(R.id.btn8_pointBloqué).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joueur.move();
                Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmePointBloqueActivity.class);
                intent.putExtra("joueur", joueur);
                startActivityForResult(intent, REQUEST_RETOURJOUEUR);
            }
        });

        findViewById(R.id.btn9_enigmeBloc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joueur.move();
                Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeBlocsActivity.class);
                intent.putExtra("joueur", joueur);
                startActivityForResult(intent, REQUEST_RETOURJOUEUR);
            }
        });

        findViewById(R.id.btn10_narrationPluiesAcides).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joueur.move();
                Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmePluiesAcidesActivity.class);
                intent.putExtra("joueur", joueur);
                startActivityForResult(intent, REQUEST_RETOURJOUEUR);
            }
        });

        findViewById(R.id.GridLayout).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("gesture", "on long click ok");
                showPlayerMenu();
                return true;
            }
        });

        //Toast.makeText(this, "Vous avez actuellement " + joueur.getTimePoint() + " points de temps", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(joueur.getTimePoint() <= 0){
            //Toast.makeText(this, "Vous avez perdu", Toast.LENGTH_SHORT).show();
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
            //Toast.makeText(this, "Vous avez actuellement " + joueur.getTimePoint() + " points de temps", Toast.LENGTH_SHORT).show();
            AchievementToast.makeAchievement(this, "Point de temps : " + joueur.getTimePoint(), AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(this, R.drawable.clickerordi)).show();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("iut","je suis dans onPause");
        if(player != null){
            player.release();
            player=null;
        }
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("enimge", "on est dans onActivityResult niveau 1");
        Log.d("enigme", "resultCode = " + resultCode);
        Log.d("enigme", "RESULT_OK = " + RESULT_OK);
        Log.d("enigme", "requestCode = " + requestCode);
        // on récupère le statut de retour de l'activité 2 c'est à dire l'activité numéro 1000
        //if(requestCode==1000){
        if(requestCode == REQUEST_RETOURJOUEUR) {
            if(resultCode == RESULT_OK) {
                joueur = data.getExtras().getParcelable("joueur");
                Log.d("intent", "joueur point temps revenu de maison : " + joueur.getTimePoint());
            }
            else if(resultCode == RESULT_CANCELED) {
                Log.d("intent", "RESULT_CANCELED = " + RESULT_CANCELED + " && resultCode = " + resultCode);
            }
        }
        // si le code de retour est égal à 1 on stoppe l'activité 1
        if(resultCode == 1){
            finish();
        }
       // }
    }

    private void showPlayerMenu() {
        String listeItem = "";
        for(Item i : joueur.getItems()) {
            listeItem += i.getNom() + "\n";
        }
        final BottomDialog bottomDialog = new BottomDialog.Builder(this)
                .setTitle("Inventaire")
                .setContent("Points de temps : " + joueur.getTimePoint() + "\n" + listeItem)
                .setIcon(R.drawable.wolf_head)
                .setPositiveText("Fermer")
                .setCancelable(true)
                .setPositiveBackgroundColorResource(R.color.black)
                .setPositiveTextColorResource(R.color.white)
                .onPositive(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {
                        Log.d("enigme", "on va finish");
                        dialog.dismiss();
                    }
                })
                .setCancelable(false) //empeche de faire disparaitre la fenetre quand on tap en dehors
                .build();
        bottomDialog.show();
    }


}
