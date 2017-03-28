package com.math.novusmens_git.enigme;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.math.novusmens_git.R;
import com.math.novusmens_git.database.ItemDAO;
import com.math.novusmens_git.database.PointDAO;
import com.math.novusmens_git.database.PossedeItemDAO;
import com.math.novusmens_git.database.PossedePointDAO;
import com.math.novusmens_git.database.Sauvegarde;
import com.math.novusmens_git.database.SauvegardeDAO;
import com.math.novusmens_git.niveau.IEnigme;
import com.math.novusmens_git.niveau.Point;
import com.math.novusmens_git.personnage.Item;
import com.math.novusmens_git.personnage.Joueur;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Math on 24/03/2017.
 */

public abstract class Enigme extends AppCompatActivity implements IEnigme {

    private int numNiveau;
    private int numEnigme; //correspond à l'index dans la liste de point du niveau
    private Joueur joueur;

    private MediaPlayer player;

    private MediaPlayer resolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        player = MediaPlayer.create(this, R.raw.pjs4);
        player.setVolume(100, 100);

        resolution = MediaPlayer.create(this, R.raw.pjs4_good);
        resolution.setVolume(2, 2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player != null) {
            player.start();
            player.setLooping(true);
        }
    }

    protected void onPause() {
        Log.d("data", "on est dans onPause enigme ordi activity");
        //saveState();
        super.onPause();
        player.stop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(player != null){
            player.release();
            player=null;
            resolution.release();
            resolution=null;
        }
        Log.i("iut","je suis dans onDestroy");
    }

    public int getNumNiveau() {
        return numNiveau;
    }

    public void setNumNiveau(int numNiveau) {
        this.numNiveau = numNiveau;
    }

    public int getNumEnigme() {
        return numEnigme;
    }

    public void setNumEnigme(int numEnigme) {
        this.numEnigme = numEnigme;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public abstract void resultat();

    public int giveRandomPointTemps() {
        double r = Math.random();
        r = (r * 10) / 2;
        return (int) Math.round(r);
    }

    public Item getItemByName(String name) {
        ItemDAO itemDAO = new ItemDAO(this);
        itemDAO.open();
        Item item = itemDAO.getByName(name);
        itemDAO.close();
        return item;
    }

    public void showResult(int point, Item item, String other) {
        resolution.start();
        Log.d("btree", "point : " + point);
        if(item != null)
            Log.d("btree", "item : " + item.getNom());
        Log.d("btree", "other : " + other);
        String linePointTemps = "";
        String lineItem = "";
        String lineOther= "";
        if(point > 1) {
            linePointTemps = "Points de temps gagnés : " + point;
        }
        else if(point < -1) {
            linePointTemps = "Points de temps perdus : " + point;
        }
        else if(point == 1) {
            linePointTemps = "Point de temps gagné : " + point;
        }
        else if(point == -1) {
            linePointTemps = "Point de temps perdu : " + point;
        }
        if(item != null) {
            lineItem = "Item gagné : " + item.getNom();
        }
        if(lineOther != null) {
            lineOther = other;
        }
        BottomDialog bottomDialog = new BottomDialog.Builder(this)
                .setTitle("Enigme resolue!")
                .setContent(lineOther + "\n\n" + linePointTemps + "\n\n" + lineItem)
                .setIcon(R.drawable.wolf_head)
                .setPositiveText("Continuer")
                .setPositiveBackgroundColorResource(R.color.black)
                .setPositiveTextColorResource(R.color.white)
                .onPositive(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {
                        Log.d("enigme", "on va finish");
                        finish();
                    }
                })
                .setCancelable(false) //empeche de faire disparaitre la fenetre quand on tap en dehors
                .build();
        bottomDialog.show();
    }

    public void saveState() {
        //sauvegarde de l'état du jeu
        //on récupère la derniere sauvegarde
        SauvegardeDAO sauvegardeDAO = new SauvegardeDAO(this);
        sauvegardeDAO.open();
        Sauvegarde last = sauvegardeDAO.selectionSave();
        Log.d("data", "ce qu'il y a dans la dernière sauvegarde");
        Log.d("data", "id : " + last.getId());
        Log.d("data", "date : " + last.getDate());
        Log.d("data", "point de temps : " + last.getPointTemps());
        Log.d("data", "numNiveau : " + last.getNumNiveau());
        //on met a jour cette sauvegarde
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String now = format.format(new Date().getTime());
        last.setDate(now);
        last.setPointTemps(getJoueur().getTimePoint());
        sauvegardeDAO.update(last);
        Log.d("data", "ce qu'il y a dans la sauvegarde updateResolu");
        Log.d("data", "id : " + last.getId());
        Log.d("data", "date : " + last.getDate());
        Log.d("data", "point de temps : " + last.getPointTemps());
        Log.d("data", "numNiveau : " + last.getNumNiveau());
        //on recupere la liste d'items du joueur
        ArrayList<Item> listItem = joueur.getItems();
        //on insere chaque item dans la table possede point
        PossedeItemDAO possedeItemDAO = new PossedeItemDAO(this);
        possedeItemDAO.open();
        for(Item item : listItem) {
            possedeItemDAO.ajouter(last.getId(), item.getId());
            Log.d("data", "item : " + item.getId() + " nom = " + item.getNom() + " ajouté dans la table possedeItem");
        }
        //on recupere ce qu'il y a dans la table possedeItem (debug)
        ArrayList<Item> items = possedeItemDAO.selectionner(last);
        Log.d("data", "ce qu'il y a dans la table possede item pour la derniere sauvegarde");
        for(Item item : items) {
            Log.d("data", "item : " + item.getId() + " nom : " + item.getNom());
        }
        //on recupere la liste de point (debug)
        PointDAO pointDAO = new PointDAO(this);
        pointDAO.open();
        ArrayList<Point> points = pointDAO.selectionner();
        Log.d("data", "ce qu'il y a dans la liste de point");
        for(int i = 0; i < points.size(); i++) {
            Log.d("data", "point : " + points.get(i).getId() + " resolu = " + points.get(i).isResolu());
        }
        //on insert le point resolu
        if(estResolue()) {
            //on upadate le point resolu
            Log.d("data", "point to be updateResolu : " + points.get(getNumEnigme()).getId() + " " + points.get(getNumEnigme()).isResolu());
            pointDAO.updateResolu(points.get(getNumEnigme()));
            Log.d("data", "liste de point après l'updateResolu");
            for(int i = 0; i < points.size(); i++) {
                Log.d("data", "point : " + points.get(i).getId() + " resolu = " + points.get(i).isResolu());
            }
            //on ajoute le point resolu à la liste des points résolu
            PossedePointDAO possedePointDAO = new PossedePointDAO(this);
            possedePointDAO.open();
            possedePointDAO.ajouter(last.getId(), points.get(getNumEnigme()).getId());
            //ce qu'il y a dans le liste de point résolus (debug)
            Log.d("data", "liste des points resolus");
            ArrayList<Point> pointsResolus = possedePointDAO.selectionner(last);
            for(int j = 0; j < pointsResolus.size(); j++) {
                Log.d("data", "point : " + pointsResolus.get(j).getId());
            }
            possedePointDAO.close();
        }
        pointDAO.close();
        possedeItemDAO.close();
        sauvegardeDAO.close();
    }

}
