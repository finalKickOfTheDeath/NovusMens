package com.math.novusmens_git.niveau;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.math.novusmens_git.R;
import com.math.novusmens_git.database.ItemDAO;
import com.math.novusmens_git.database.PointDAO;
import com.math.novusmens_git.database.PossedePointDAO;
import com.math.novusmens_git.database.Sauvegarde;
import com.math.novusmens_git.database.SauvegardeDAO;
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
import com.math.novusmens_git.personnage.ItemList;
import com.math.novusmens_git.personnage.Joueur;
import com.merkmod.achievementtoastlibrary.AchievementToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Niveau1Activity extends Niveau {

    private static final int NB_POINT_NIVEAU = 10;
    private static final int NUM_NIVEAU = 1;
    private static final int REQUEST_RETOURJOUEUR = 100;
    private static final int REQUEST_FIRSTRETOUR = 200;

    private final String EXTRA_MUSIQUE = "musique";
    private MediaPlayer player;

    private String[] nomItems = {"Morceau d'âme 1(1/2)", "Morceau d'âme 2(1/2)", "Caillou mystique", "Heart key", "Pousse d'espoir"};
    private Joueur joueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("niveau", "on est dans le onCreate");
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

        setNumNiveau(NUM_NIVEAU);

        Log.d("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");

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
    protected void onResume() {
        Log.d("niveau", "on est dans onResume");
        super.onResume();
        //on récupere la dernière sauvegarde
        SauvegardeDAO sauvegardeDAO = new SauvegardeDAO(this);
        sauvegardeDAO.open();
        Sauvegarde last = sauvegardeDAO.selectionSave();
        sauvegardeDAO.close();
        //si pas de sauvegarde existante --> initalisation des tables point et item
        if(last == null) {
            initPoint();
            initItem();
        }
        //on recupère la liste de point du niveau
        PointDAO pointDAO = new PointDAO(this);
        pointDAO.open();
        final ArrayList<Point> points = pointDAO.selectionner();
        pointDAO.close();
        Log.d("data", "dans la table points !!!!!!!");
        for(Point p : points) {
            Log.d("data", "point " + p.getId() + " " + p.isResolu());
        }
        //si pas de sauvegarde ou point de l'enigme ordi pas resolu --> on envoie le joueur sur l'enigme ordi
        if(last == null || (!points.get(getResources().getInteger(R.integer.level1_enigmeOrdinateur)).isResolu())) {
            Intent intentOrdi = new Intent(this, EnigmeOrdiActivity.class);
            intentOrdi.putExtra("joueur", joueur);
            intentOrdi.putExtra("listePoint", (Parcelable) getPoints());
            startActivityForResult(intentOrdi, REQUEST_FIRSTRETOUR);
        }
        //si une sauvegarde existe (last != null)
        else {
            if(/*points != null &&*/ points.get(getResources().getInteger(R.integer.level1_enigmeSortie)).isResolu()){
                // si le joueur gagne mais n'a plus de point de temps, on lui en rajoute un pour qu'il n'est pas un game over
                if(joueur.getTimePoint()==0) {
                    joueur.winTimePoint(1);
                }
                //narration pour aller vers le niveau suivant
                Intent intent = new Intent(this, NarrationActivity.class);
                intent.putExtra("joueur", joueur);
                startActivity(intent);
                finish();
            }
            else {
                if (joueur.getTimePoint() <= 0) {
                    //Toast.makeText(this, "Vous avez perdu", Toast.LENGTH_SHORT).show();
                    //narration game over
                    Intent intent = new Intent(this, NarrationActivity.class);
                    intent.putExtra("joueur", joueur);
                    startActivity(intent);
                    finish();
                } else {
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

            findViewById(R.id.btn1_narrationMaisonAbandonnee).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(points.get(getResources().getInteger(R.integer.level1_enigmeNarrative1MaisonAbandonne)).isResolu()) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Enigme déjà résolue!", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else {
                        joueur.move();
                        Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                        Intent intent = new Intent(v.getContext(), EnigmeMaisonAbandonneeActivity.class);
                        intent.putExtra("joueur", joueur);
                        startActivityForResult(intent, REQUEST_RETOURJOUEUR);
                    }
                }
            });

            findViewById(R.id.btn2_enigmeRacine).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(points.get(getResources().getInteger(R.integer.level1_enigmeRacine)).isResolu()) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Enigme déjà résolue!", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else {
                        joueur.move();
                        Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                        Intent intent = new Intent(v.getContext(), EnigmeRacines.class);
                        intent.putExtra("joueur", joueur);
                        startActivityForResult(intent, REQUEST_RETOURJOUEUR);
                    }
                }
            });

            findViewById(R.id.btn3_pnj).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(points.get(getResources().getInteger(R.integer.level1_enigmePNJ)).isResolu()) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Enigme déjà résolue!", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else {
                        joueur.move();
                        Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                        Intent intent = new Intent(v.getContext(), EnigmePNJActivity.class);
                        intent.putExtra("joueur", joueur);
                        startActivityForResult(intent, REQUEST_RETOURJOUEUR);
                    }
                }
            });

            findViewById(R.id.btn4_enigmeJarres).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(points.get(getResources().getInteger(R.integer.level1_enigmeJarre)).isResolu()) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Enigme déjà résolue!", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else {
                        joueur.move();
                        Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                        Intent intent = new Intent(v.getContext(), EnigmeJarresActivity.class);
                        intent.putExtra("joueur", joueur);
                        startActivityForResult(intent, REQUEST_RETOURJOUEUR);
                    }
                }
            });

            findViewById(R.id.btn5_sortie).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(points.get(getResources().getInteger(R.integer.level1_enigmeSortie)).isResolu()) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Enigme déjà résolue!", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else {
                        joueur.move();
                        Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                        Intent intent = new Intent(v.getContext(), EnigmeSortie.class);
                        intent.putExtra("joueur", joueur);
                        startActivityForResult(intent, REQUEST_RETOURJOUEUR);
                    }
                }
            });

            findViewById(R.id.btn6_enigmeOrdi).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(points.get(getResources().getInteger(R.integer.level1_enigmeOrdinateur)).isResolu()) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Enigme déjà résolue!", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else {
                        joueur.move();
                        Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                        Intent intent = new Intent(v.getContext(), EnigmeOrdiActivity.class);
                        intent.putExtra("joueur", joueur);
                        intent.putExtra("listePoint", (Parcelable) getPoints());
                        startActivityForResult(intent, REQUEST_RETOURJOUEUR);
                    }
                }
            });

            findViewById(R.id.btn7_narrationDesertMagnetique).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(points.get(getResources().getInteger(R.integer.level1_enigmeNarrative2DesertMagnetique)).isResolu()) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Enigme déjà résolue!", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else {
                        joueur.move();
                        Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                        Intent intent = new Intent(v.getContext(), EnigmeDesertMagnetiqueActivity.class);
                        intent.putExtra("joueur", joueur);
                        startActivityForResult(intent, REQUEST_RETOURJOUEUR);
                    }
                }
            });

            findViewById(R.id.btn8_pointBloqué).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ItemDAO itemDAO = new ItemDAO(getApplicationContext());
                    itemDAO.open();
                    Item key = itemDAO.getByName(nomItems[3]);
                    itemDAO.close();
                    if(points.get(getResources().getInteger(R.integer.level1_enigmePointBloque)).isResolu()) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Enigme déjà résolue!", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else if(!joueur.has(key)) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Ce point est bloqué", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else {
                        joueur.move();
                        Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                        Intent intent = new Intent(v.getContext(), EnigmePointBloqueActivity.class);
                        intent.putExtra("joueur", joueur);
                        startActivityForResult(intent, REQUEST_RETOURJOUEUR);
                    }
                }
            });

            findViewById(R.id.btn9_enigmeBloc).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!points.get(getResources().getInteger(R.integer.level1_enigmeNarrative2DesertMagnetique)).isResolu()) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Ce point est bloqué", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else if(points.get(getResources().getInteger(R.integer.level1_enigmeBloc)).isResolu()) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Enigme déjà résolue!", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else {
                        joueur.move();
                        Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                        Intent intent = new Intent(v.getContext(), EnigmeBlocsActivity.class);
                        intent.putExtra("joueur", joueur);
                        startActivityForResult(intent, REQUEST_RETOURJOUEUR);
                    }
                }
            });

            findViewById(R.id.btn10_narrationPluiesAcides).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(points.get(getResources().getInteger(R.integer.level1_enigmeNarrative3PluiesAcides)).isResolu()) {
                        AchievementToast.makeAchievement(Niveau1Activity.this, "Enigme déjà résolue!", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
                    }
                    else {
                        joueur.move();
                        Log.i("data", "Vous avez actuellement " + joueur.getTimePoint() + " points de temps");
                        Intent intent = new Intent(v.getContext(), EnigmePluiesAcidesActivity.class);
                        intent.putExtra("joueur", joueur);
                        startActivityForResult(intent, REQUEST_RETOURJOUEUR);
                    }
                }
            });
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("niveau", "on est dans le onPause de niveau1activity");
        if(player != null){
            player.release();
            player=null;
        }
        //on recupere la derniere sauvegarde
        SauvegardeDAO sauvegardeDAO = new SauvegardeDAO(this);
        sauvegardeDAO.open();
        Sauvegarde last = sauvegardeDAO.selectionSave();
        //si pas de sauvegarde --> on la créée
        if(last == null) {
            Log.d("data", "pas de sauvegarde dans niveau 1 --> on la créée");
            //initialisation des points et des items du niveau
            //initPoint();
            //initItem();
            //création de la premiere sauvegarde
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            String now = format.format(new Date().getTime());
            //on insert la sauvegarde dans la base
            sauvegardeDAO.ajouter(new Sauvegarde(now, joueur.getTimePoint(), getNumNiveau()));
            last = sauvegardeDAO.selectionSave();
            Log.d("data", "ce qu'il y a dans la dernière sauvegarde");
            Log.d("data", "id : " + last.getId());
            Log.d("data", "date : " + last.getDate());
            Log.d("data", "point de temps : " + last.getPointTemps());
            Log.d("data", "numNiveau : " + last.getNumNiveau());
        }
        //si il y une sauvegarde --> on l'update
        else {
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            String now = format.format(new Date().getTime());
            last.setDate(now);
            last.setPointTemps(joueur.getTimePoint());
            sauvegardeDAO.update(last);
            Log.d("data", "ce qu'il y a dans la sauvegarde update");
            Log.d("data", "id : " + last.getId());
            Log.d("data", "date : " + last.getDate());
            Log.d("data", "point de temps : " + last.getPointTemps());
            Log.d("data", "numNiveau : " + last.getNumNiveau());
        }
        sauvegardeDAO.close();
    }

    private void initPoint() {
        //creation de la liste de point du niveau
        PointList listPoint = new PointList();
        for(int i = 1; i <= NB_POINT_NIVEAU; i++) {
            listPoint.add(new Point(i, false));
        }
        setPoints(listPoint);
        Log.d("data", "on a set l'arrayList de points du niveau 1");
        //recuperation des points du niveau 1 (debug)
        ArrayList<Point> points = getPoints();
        Log.d("data", "ce qu'il y a dans l'arrayList de point");
        for(int i = 0; i < points.size(); i++) {
            Log.d("data", "point : " + points.get(i).getId() + "resolu = " + points.get(i).isResolu());
        }
        //on met les points dans la base de donnée
        PointDAO pointDAO = new PointDAO(this);
        pointDAO.open();
        for(int i = 0; i < listPoint.size(); i++) {
            pointDAO.ajouter(listPoint.get(i));
        }
        pointDAO.close();
    }

    private void initItem() {
        //creation de la liste d'item du niveau
        ItemList listItem = new ItemList();
        for(int i = 1; i <= nomItems.length; i++) {
            listItem.add(new Item(i, nomItems[i - 1]));
        }
        setItems(listItem);
        Log.d("data", "on a set l'arrayList d'item du niveau 1");
        //recuperation des items du niveau 1 (debug)
        ArrayList<Item> items = getItems();
        Log.d("data", "ce qu'il y a dans l'arrayList d'item");
        for(int i = 0; i < items.size(); i++) {
            Log.d("data", "item : " + items.get(i).getId() + "nom = " + items.get(i).getNom());
        }
        //on met les items dans la base de donnée
        ItemDAO itemDAO = new ItemDAO(this);
        itemDAO.open();
        for(int i = 0; i < listItem.size(); i++) {
            itemDAO.ajouter(listItem.get(i));
        }
        itemDAO.close();
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
        Log.d("intent", "on est dans onActivityResult niveau 1");
        Log.d("intent", "resultCode = " + resultCode);
        Log.d("intent", "RESULT_OK = " + RESULT_OK);
        Log.d("intent", "requestCode = " + requestCode);
        if(requestCode == REQUEST_RETOURJOUEUR) {
            if(resultCode == RESULT_OK) {
                joueur = data.getExtras().getParcelable("joueur");
                Log.d("intent", "joueur point temps revenu : " + joueur.getTimePoint());
            }
            else if(resultCode == RESULT_CANCELED) {
                Log.d("intent", "RESULT_CANCELED = " + RESULT_CANCELED + " && resultCode = " + resultCode);
            }
        }
        else if(requestCode == REQUEST_FIRSTRETOUR) {
            if(resultCode == RESULT_OK) {
                joueur = data.getExtras().getParcelable("joueur");
                Log.d("intent", "joueur point temps revenu : " + joueur.getTimePoint());
                showDidactitiel();
            }
            else if(resultCode == RESULT_CANCELED) {
                Log.d("intent", "RESULT_CANCELED = " + RESULT_CANCELED + " && resultCode = " + resultCode);
                finish();
            }
        }
    }

    private void showPlayerMenu() {
        String listeItem = "";
        for(Item i : joueur.getItems()) {
            listeItem += i.getNom() + "\n";
        }
        BottomDialog bottomDialog = new BottomDialog.Builder(this)
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
                        Log.d("intent", "on va finish");
                        dialog.dismiss();
                    }
                })
                .build();
        bottomDialog.show();
    }

    private void showDidactitiel() {
        BottomDialog bottomDialog = new BottomDialog.Builder(this)
                .setTitle("Didactitiel")
                .setContent("Pour accéder à votre inventaire, effectuer un tap long sur l'écran")
                .setIcon(R.drawable.clickerordi)
                .setPositiveText("Fermer")
                .setCancelable(true)
                .setPositiveBackgroundColorResource(R.color.black)
                .setPositiveTextColorResource(R.color.white)
                .onPositive(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .build();
        bottomDialog.show();
    }
}
