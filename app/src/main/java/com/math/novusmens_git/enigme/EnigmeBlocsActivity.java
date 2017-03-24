package com.math.novusmens_git.enigme;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.math.novusmens_git.R;

public class EnigmeBlocsActivity extends Enigme {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enigme_blocs);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setNumNiveau(getResources().getInteger(R.integer.level1));
        setNumEnigme(getResources().getInteger(R.integer.level1_enigmeBloc));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 8 il est : " + getNumEnigme());
    }

    @Override
    public boolean estResolue() {
        return false;
    }

    @Override
    public void resultat() {

    }
}
