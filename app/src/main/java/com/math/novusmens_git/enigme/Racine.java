package com.math.novusmens_git.enigme;

import android.view.View;

/**
 * Created by Renan on 20/02/2017.
 */
public class Racine {
    private View[] racines = new View[2];


    public Racine(View extrémité1, View extrémité2){
        racines[0] = extrémité1;
        racines[1] = extrémité2;
    }

    public boolean estCorrect(View extrémité1, View extrémité2){
        return  (this.racines[0].equals(extrémité1) && this.racines[1].equals(extrémité2)) ||
                (this.racines[0].equals(extrémité2) && this.racines[1].equals(extrémité1)) ;
    }

    public void remove() {
        for (View v : racines){
            v.setVisibility(View.INVISIBLE);
        }
    }
}
