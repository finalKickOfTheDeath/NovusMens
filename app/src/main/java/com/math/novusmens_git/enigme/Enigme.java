package com.math.novusmens_git.enigme;

import android.support.v7.app.AppCompatActivity;

import com.math.novusmens_git.niveau.IEnigme;


/**
 * Created by Math on 24/03/2017.
 */

public abstract class Enigme extends AppCompatActivity implements IEnigme {

    private int numNiveau;
    private int numEnigme;


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
}
