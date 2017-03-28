package com.math.novusmens_git.enigme;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.math.novusmens_git.R;
import com.math.novusmens_git.database.PossedeItemDAO;
import com.math.novusmens_git.personnage.Item;
import com.math.novusmens_git.personnage.Joueur;

public class EnigmePointBloqueActivity extends Enigme {

    private static final String ITEM_NEEDED = "Heart key";
    private static final String ITEM_ENIMGE = "Pousse d'espoir";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_point_bloque);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent intent = getIntent();
        if(intent != null){
            setJoueur((Joueur) intent.getExtras().getParcelable("joueur"));
            Log.d("intent", "joueur point temps : " + getJoueur().getTimePoint());
        }
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setNumEnigme(getResources().getInteger(R.integer.level1_enigmePointBloque));
        setNumNiveau(getResources().getInteger(R.integer.level1));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 7 il est : " + getNumEnigme());

        //font perso
        Typeface typeFaceSav = Typeface.createFromAsset(getAssets(),"fonts/savior1.ttf");
        TextView desc = (TextView) findViewById(R.id.txtDescPointBloque);
        TextView take = (TextView) findViewById(R.id.btnTakePointBloque);
        TextView let = (TextView) findViewById(R.id.btnLetPointBloque);
        desc.setTypeface(typeFaceSav);
        take.setTypeface(typeFaceSav);
        let.setTypeface(typeFaceSav);
        desc.setTextSize(25);
        take.setTextSize(25);
        let.setTextSize(25);

        //la premiere fois qu'on arrive sur l'enigme on enleve la heart key au joueur
        /*
        if(getJoueur().has(getItemByName(ITEM_NEEDED))) {
            Log.d("enigme", "on va enlever la heart key au joueur");
            letKey();
        }
        */

        findViewById(R.id.btnTakePointBloque).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("enigme", "on prends la pousse d'espoir");
                //si on n'a pas encore la pousse d'espoir
                if(!estResolue())
                    resultat();
            }
        });

        findViewById(R.id.btnLetPointBloque).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("enigme", "on laisse la pousse d'espoir");
                //on prepare l'intent de retour vers la map du niveau
                Intent intent = getIntent();
                intent.putExtra("joueur", getJoueur());
                setResult(RESULT_OK, intent);
                //on s'en va
                BottomDialog bottomDialog = new BottomDialog.Builder(EnigmePointBloqueActivity.this)
                        .setTitle("")
                        .setContent(getString(R.string.pointBloque_txtLet))
                        .setIcon(R.drawable.wolf_head)
                        .setPositiveText("Continuer")
                        .setPositiveBackgroundColorResource(R.color.black)
                        .setPositiveTextColorResource(R.color.white)
                        .onPositive(new BottomDialog.ButtonCallback() {
                            @Override
                            public void onClick(BottomDialog dialog) {
                                Log.d("enigme", "on va finish");
                                finish();
                            }
                        })
                        .setCancelable(false) //empeche de faire disparaitre la fenetre quand on tap en dehors
                        .build();
                bottomDialog.show();
            }
        });

    }

    @Override
    public boolean estResolue() {
        return getJoueur().has(getItemByName(ITEM_ENIMGE));
    }

    @Override
    public void resultat() {
        //on enleve l'item key au joueur
        letKey();
        //on ajoute la pousse d'espoir au joueur
        Item pousse = getItemByName(ITEM_ENIMGE);
        getJoueur().win(pousse);
        //on prepare l'intent de retour vers la map du niveau
        Intent intent = getIntent();
        intent.putExtra("joueur", getJoueur());
        setResult(RESULT_OK, intent);
        showResult(0, pousse, getString(R.string.pointBloque_txtTake));
    }

    private void letKey() {
        //on enlève la heart key au joueur
        Item key = getItemByName(ITEM_NEEDED);
        getJoueur().dismiss(key);
        //on l'enlève de la table possède item
        PossedeItemDAO possedeItemDAO = new PossedeItemDAO(this);
        possedeItemDAO.open();
        possedeItemDAO.supprimer(key.getId());
        possedeItemDAO.close();
    }

    @Override
    protected void onPause() {
        saveState();
        super.onPause();
    }

}
