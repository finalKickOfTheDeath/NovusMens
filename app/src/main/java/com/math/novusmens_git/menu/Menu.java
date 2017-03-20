package com.math.novusmens_git.menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.math.novusmens_git.R;

import niveau.Niveau1Activity;

public class Menu extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private static final String PREFS = "PREFS";
    private static final String PREF_AGE = "PREFS_AGE";
    private static final String PREFS_NAME = "PREFS_NAME";

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

        sharedPreferences = getBaseContext().getSharedPreferences(PREFS,MODE_PRIVATE);

        findViewById(R.id.btnJouer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commencer(v);
            }
        });

        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset(v);
            }
        });

    }

    protected void commencer(View view){
        //Intent intent = new Intent(this, NarrationActivity.class);
        //startActivity(intent);
        //this.onStop();
        if(sharedPreferences.contains(PREF_AGE) && sharedPreferences.contains(PREFS_NAME)){
            int age = sharedPreferences.getInt(PREF_AGE,0);
            String name = sharedPreferences.getString(PREFS_NAME,null);
            //Toast.makeText(this,"Bonjour " + name + ", vous avez " + age +"ans",Toast.LENGTH_LONG).show();
            Toast.makeText(this, "Une partie à déja été commencé. Nous sommes toujours en développement", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Niveau1Activity.class);
            startActivity(intent);
        }
        else { // si aucune information n'est trouvées, on en ajoute un
            sharedPreferences.edit().putInt(PREF_AGE, 20).putString(PREFS_NAME, "Alexis").apply();
            //Toast.makeText(this, "Nous avons sauvegarder vos informations. Redémarrer pour mettre a jour", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, NarrationActivity.class);
            startActivity(intent);
            //this.onStop();
        }
    }

    protected void reset(View view){
        sharedPreferences.edit().remove(PREF_AGE).remove(PREFS_NAME).apply();
        Toast.makeText(this, "Vous avez réinitialisé votre partie. Amusez-vous bien !", Toast.LENGTH_LONG).show();
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
