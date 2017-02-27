package com.math.novusmens_git;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // bloque le téléphone en paysage
        super.onCreate(savedInstanceState);
        // pour mettre l'activité en fullscreen
        // a mettre juste apres super.onCreate
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide(); // cache la barre de menu
    }

    protected void commencer(View view){
        Intent intent = new Intent(this, NarrationActivity.class);
        startActivity(intent);
        //this.onStop();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("iut","je suis dans onPause");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("iut","je suis dans onResume");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("iut","je suis dans onStop");
    }
}
