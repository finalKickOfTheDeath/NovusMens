package com.math.novusmens_git.enigme;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Checkable;

import com.math.novusmens_git.R;
import com.math.novusmens_git.personnage.Item;
import com.math.novusmens_git.personnage.Joueur;

import java.util.LinkedList;

public class EnigmeRacines extends Enigme {

    private static final String ITEM_ENIMGE = "Morceau d'âme 1(1/2)";

    private LinkedList<Racine> racines;
    private LinkedList<Checkable> proposition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enigme_racines);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent intent = getIntent();
        if(intent != null){
            setJoueur((Joueur) intent.getExtras().getParcelable("joueur"));
            Log.d("intent", "joueur point temps : " + getJoueur().getTimePoint());
        }
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setNumEnigme(getResources().getInteger(R.integer.level1_enigmeRacine));
        setNumNiveau(getResources().getInteger(R.integer.level1));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 1 il est : " + getNumEnigme());

        racines = new LinkedList<>();
        proposition = new LinkedList<>();
        initRacines();
    }

    private void initRacines() {
        racines.add(new Racine(findViewById(R.id.buttonA),findViewById(R.id.button3)));
        racines.add(new Racine(findViewById(R.id.buttonB),findViewById(R.id.button4)));
        racines.add(new Racine(findViewById(R.id.buttonC),findViewById(R.id.button1)));
        racines.add(new Racine(findViewById(R.id.buttonD),findViewById(R.id.button5)));
        racines.add(new Racine(findViewById(R.id.buttonE),findViewById(R.id.button2)));
    }

    public void onClick(View view) {

        if (! ((Checkable)view).isChecked() ){
            proposition.removeLast();
        }
        else {
            proposition.add((Checkable)view);
            if(proposition.size() == 2){

                Log.d("racine", "Il y a " + racines.size() +" racines");
                for(Racine r : racines)
                    if (r.estCorrect((View)proposition.get(0),(View)proposition.get(1))) {
                        // Action de prop Correcte ( => retirer la racine)
                        r.remove();
                        racines.remove(r);
                        Log.d("racine", "ON ENELVE UNE RACINE DE LA LISTE!!!");

                        Log.d("racine", "Il reste " + racines.size() +" racines");
                        proposition.clear();
                        if(racines.isEmpty()) {
                            resultat();
                        }
                        return;
                    }

                Log.d("racine", "Il reste " + racines.size() +" racines");
                //Aucune racine ne convient, la prop est donc incorrecte

                //désactivation des widgets sélectionnés
                for (Checkable element : proposition)
                    element.setChecked(false);
                proposition.clear();

            }
        }
    }

    @Override
    public boolean estResolue() {
        return (getJoueur().has(getItemByName(ITEM_ENIMGE)));
    }

    @Override
    public void resultat() {
        //le joueur recoit un morceau d'ame = item
        //Item ame = new Item("Morceau d'âme");
        //on va chercher l'item dans la bd
        Item item = getItemByName(ITEM_ENIMGE);
        getJoueur().win(item);
        //on prepare l'intent de retour vers la map du niveau
        Intent intent = getIntent();
        intent.putExtra("joueur", getJoueur());
        setResult(RESULT_OK, intent);
        //on ouvre le dialog pour montrer les résultat
        showResult(0, item, "Le circuit semble refonctionner!");
    }

    @Override
    protected void onPause() {
        saveState();
        super.onPause();
    }

}
