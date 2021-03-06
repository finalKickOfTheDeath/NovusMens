package com.math.novusmens_git.enigme;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.math.novusmens_git.R;
import com.math.novusmens_git.database.PossedeItemDAO;
import com.math.novusmens_git.personnage.Item;
import com.math.novusmens_git.personnage.Joueur;

public class EnigmeSortie extends Enigme {

    private static final String ITEM_NEEDED = "Pousse d'espoir";

    private boolean estRendu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enigme_sortie);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent intent = getIntent();
        if(intent != null){
            setJoueur((Joueur) intent.getExtras().getParcelable("joueur"));
            Log.d("intent", "joueur point temps : " + getJoueur().getTimePoint());
        }
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setNumEnigme(getResources().getInteger(R.integer.level1_enigmeSortie));
        setNumNiveau(getResources().getInteger(R.integer.level1));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 4 il est : " + getNumEnigme());

        //font perso
        Typeface typeFaceSav = Typeface.createFromAsset(getAssets(),"fonts/savior1.ttf");
        TextView desc = (TextView) findViewById(R.id.txtSortie);
        TextView put = (TextView) findViewById(R.id.btnPutSortie);
        Button partir = (Button) findViewById(R.id.btnSortieRetour);
        desc.setTypeface(typeFaceSav);
        put.setTypeface(typeFaceSav);
        partir.setTypeface(typeFaceSav);
        desc.setTextSize(22);
        put.setTextSize(25);
        partir.setTextSize(25);

        if(getJoueur().has(getItemByName(ITEM_NEEDED))) {
            saveState();
            Log.d("enigme", "sortie resolue");
            findViewById(R.id.btnPutSortie).setVisibility(View.VISIBLE);

            findViewById(R.id.btnPutSortie).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    estRendu = true;
                    resultat();
                }
            });
        }
        else {
            findViewById(R.id.btnPutSortie).setVisibility(View.INVISIBLE);
        }

        findViewById(R.id.btnSortieRetour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on prepare l'intent de retour vers la map du niveau
                Intent intent = getIntent();
                intent.putExtra("joueur", getJoueur());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    public boolean estResolue() {
        return (estRendu);
    }

    @Override
    public void resultat() {
        //on retire la pousse d'espoir de l'inventaire du joueur
        Item pousse = getItemByName(ITEM_NEEDED);
        getJoueur().dismiss(pousse);
        //on l'enlève de la table possede item
        PossedeItemDAO possedeItemDAO = new PossedeItemDAO(this);
        possedeItemDAO.open();
        possedeItemDAO.supprimer(pousse.getId());
        possedeItemDAO.close();
        //on prepare l'intent de retour vers la map du niveau
        Intent intent = getIntent();
        intent.putExtra("joueur", getJoueur());
        setResult(RESULT_OK, intent);
        //on ouvre le dialog pour montrer les résultat
        showResult(0, null, "Une partie de votre mémoire vous revient...");
    }

    @Override
    protected void onPause() {
        saveState();
        super.onPause();
    }

}
