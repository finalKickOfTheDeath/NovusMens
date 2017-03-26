package com.math.novusmens_git.niveau;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.math.novusmens_git.personnage.Item;

import java.util.ArrayList;

/**
 * Created by Math on 22/03/2017.
 */

public class Niveau extends AppCompatActivity {

    public static final int NB_POINT_NIVEAU1 = 10;

    int numNiveau;
    ArrayList<Point> points;
    ArrayList<Item> items;

    /*
    public Niveau(int numNiveau) {
        this.numNiveau = numNiveau;
        this.points = new ArrayList<Point>();
        if(numNiveau == 1) {
            setArrayListNiveau1();
        }
    }*/

    /*
    private void setArrayListNiveau1() {
        for(int i = 1; i <= NB_POINT_NIVEAU1; i++) {
            points.add(new Point(i, false));
        }
        Log.d("data", "on a set l'arrayList du niveau 1");
    }*/

    public int getNumNiveau() {
        return numNiveau;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setNumNiveau(int numNiveau) {
        this.numNiveau = numNiveau;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}