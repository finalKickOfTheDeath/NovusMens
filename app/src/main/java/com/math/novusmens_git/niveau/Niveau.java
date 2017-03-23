package com.math.novusmens_git.niveau;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Math on 22/03/2017.
 */

public class Niveau {
    public static final int NB_POINT_NIVEAU1 = 10;

    int numNiveau;
    ArrayList<Point> points;

    public Niveau(int numNiveau) {
        this.numNiveau = numNiveau;
        this.points = new ArrayList<Point>();
        if(numNiveau == 1) {
            setArrayListNiveau1();
        }
    }

    private void setArrayListNiveau1() {
        for(int i = 1; i <= NB_POINT_NIVEAU1; i++) {
            points.add(new Point(i, false));
        }
        Log.d("data", "on a set l'arrayList du niveau 1");
    }

    public int getNumNiveau() {
        return numNiveau;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

}