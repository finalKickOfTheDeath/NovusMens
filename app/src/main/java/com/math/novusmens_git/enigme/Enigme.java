package com.math.novusmens_git.enigme;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.math.novusmens_git.database.PointDAO;
import com.math.novusmens_git.database.PossedePointDAO;
import com.math.novusmens_git.database.Sauvegarde;
import com.math.novusmens_git.database.SauvegardeDAO;
import com.math.novusmens_git.niveau.IEnigme;
import com.math.novusmens_git.niveau.Point;
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

    public void saveState() {
        //sauvegarde de l'état du jeu
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
        Log.d("data", "ce qu'il y a dans la sauvegarde updateSetResolu");
        Log.d("data", "id : " + last.getId());
        Log.d("data", "date : " + last.getDate());
        Log.d("data", "point de temps : " + last.getPointTemps());
        Log.d("data", "numNiveau : " + last.getNumNiveau());
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
            Log.d("data", "point to be updateSetResolu : " + points.get(getNumEnigme()).getId() + " " + points.get(getNumEnigme()).isResolu());
            pointDAO.updateSetResolu(points.get(getNumEnigme()));
            Log.d("data", "liste de point apres l'updateSetResolu");
            for(int i = 0; i < points.size(); i++) {
                Log.d("data", "point : " + points.get(i).getId() + " resolu = " + points.get(i).isResolu());
            }
            //on ajoute le point resolu à la liste des points résolu
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
    }

}
