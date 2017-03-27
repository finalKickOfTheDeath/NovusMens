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
import com.math.novusmens_git.personnage.Item;
import com.math.novusmens_git.personnage.Joueur;

public class EnigmeDesertMagnetiqueActivity extends Enigme {

    private static final String ITEM_ENIMGE = "Caillou mystique";

    //niveau 1
    private BTree bTreeNiv1;

    //niveau 2
    private BTree bTreeNiv2_G;
    private BTree bTreeNiv2_D;

    //niveau 3
    private BTree bTreeNiv3_G_G;
    private BTree bTreeNiv3_G_D;
    private BTree bTreeNiv3_D_G;
    private BTree bTreeNiv3_D_D;

    //reponses
    private BTree bTreeREP_G_G_G;
    private BTree bTreeREP_G_G_D;
    private BTree bTreeREP_G_D_G;
    private BTree bTreeREP_G_D_D;
    private BTree bTreeREP_D_G_G;
    private BTree bTreeREP_D_G_D;
    private BTree bTreeREP_D_D_G;
    private BTree bTreeREP_D_D_D;

    //btree en cours d'affichage
    private static final int FINAL_STEP = 4; //niveau des reponses
    private BTree bTreeCourant;
    private int stepCourant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enigme_desert_magnetique);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent intent = getIntent();
        if(intent != null){
            setJoueur((Joueur) intent.getExtras().getParcelable("joueur"));
            Log.d("intent", "joueur point temps : " + getJoueur().getTimePoint());
        }
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setNumEnigme(getResources().getInteger(R.integer.level1_enigmeNarrative2DesertMagnetique));
        setNumNiveau(getResources().getInteger(R.integer.level1));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 6 il est : " + getNumEnigme());

        Typeface typeFaceSav = Typeface.createFromAsset(getAssets(),"fonts/savior1.ttf");
        TextView question = (TextView) findViewById(R.id.txtViewDesertMag);
        TextView repG = (TextView) findViewById(R.id.btnDesertRG);
        TextView repD = (TextView) findViewById(R.id.btnDesertRD);
        question.setTypeface(typeFaceSav);
        repG.setTypeface(typeFaceSav);
        repD.setTypeface(typeFaceSav);
        question.setTextSize(20);
        repG.setTextSize(20);
        repD.setTextSize(20);

        //initialisation des btree
        bTreeNiv1 = new BTree(getString(R.string.DM_Q1), getString(R.string.DM_Q1_RG), getString(R.string.DM_Q1_RD));

        bTreeNiv2_G = new BTree(getString(R.string.DM_Q2_G), getString(R.string.DM_Q2_G_RG), getString(R.string.DM_Q2_G_RD));
        bTreeNiv2_D = new BTree(getString(R.string.DM_Q2_D), getString(R.string.DM_Q2_D_RG), getString(R.string.DM_Q2_D_RD));

        bTreeNiv3_G_G = new BTree(getString(R.string.DM_Q3_G_G), getString(R.string.DM_Q3_G_G_RG), getString(R.string.DM_Q3_G_G_RD));
        bTreeNiv3_G_D = new BTree(getString(R.string.DM_Q3_G_D), getString(R.string.DM_Q3_G_D_RG), getString(R.string.DM_Q3_G_D_RD));
        bTreeNiv3_D_G = new BTree(getString(R.string.DM_Q3_D_G), getString(R.string.DM_Q3_D_G_RG), getString(R.string.DM_Q3_D_G_RD));
        bTreeNiv3_D_D = new BTree(getString(R.string.DM_Q3_D_D), getString(R.string.DM_Q3_D_D_RG), getString(R.string.DM_Q3_D_D_RD));

        bTreeREP_G_G_G = new BTree(getString(R.string.DM_Res_Q3_G_G_RG));
        bTreeREP_G_G_D = new BTree(getString(R.string.DM_Res_Q3_G_G_RD));
        bTreeREP_G_D_G = new BTree(getString(R.string.DM_Res_Q3_G_D_RG));
        bTreeREP_G_D_D = new BTree(getString(R.string.DM_Res_Q3_G_D_RD));
        bTreeREP_D_G_G = new BTree(getString(R.string.DM_Res_Q3_D_G_RG));
        bTreeREP_D_G_D = new BTree(getString(R.string.DM_Res_Q3_D_G_RD));
        bTreeREP_D_D_G = new BTree(getString(R.string.DM_Res_Q3_D_D_RG));
        bTreeREP_D_D_D = new BTree(getString(R.string.DM_Res_Q3_D_D_RD));

        //binding des btree
        try {
            bTreeNiv1.setLeftTree(bTreeNiv2_G);
            bTreeNiv1.setRightTree(bTreeNiv2_D);

            bTreeNiv2_G.setLeftTree(bTreeNiv3_G_G);
            bTreeNiv2_G.setRightTree(bTreeNiv3_G_D);
            bTreeNiv2_D.setLeftTree(bTreeNiv3_D_G);
            bTreeNiv2_D.setRightTree(bTreeNiv3_D_D);

            bTreeNiv3_G_G.setLeftTree(bTreeREP_G_G_G);
            bTreeNiv3_G_G.setRightTree(bTreeREP_G_G_D);
            bTreeNiv3_G_D.setLeftTree(bTreeREP_G_D_G);
            bTreeNiv3_G_D.setRightTree(bTreeREP_G_D_D);
            bTreeNiv3_D_G.setLeftTree(bTreeREP_D_G_G);
            bTreeNiv3_D_G.setRightTree(bTreeREP_D_G_D);
            bTreeNiv3_D_D.setLeftTree(bTreeREP_D_D_G);
            bTreeNiv3_D_D.setRightTree(bTreeREP_D_D_D);
        } catch (Exception e) {
            Log.d("btree", "exception lors du binding : " + e);
        }

        //initalisation des view
        ((TextView)findViewById(R.id.txtViewDesertMag)).setText(bTreeNiv1.getQuestion());
        ((TextView)findViewById(R.id.btnDesertRG)).setText(bTreeNiv1.getReponseGauche());
        ((TextView)findViewById(R.id.btnDesertRD)).setText(bTreeNiv1.getReponseDroite());

        bTreeCourant = bTreeNiv1;
        stepCourant = 1; //on est au bTree niveau 1

        findViewById(R.id.btnDesertRG).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bTreeCourant = bTreeCourant.getLeftTree();
                } catch (Exception e) {
                    Log.d("btree", "exception lors du changement de btreeCourant : " + e);
                }
                ((TextView)findViewById(R.id.txtViewDesertMag)).setText(bTreeCourant.getQuestion());
                ((TextView)findViewById(R.id.btnDesertRG)).setText(bTreeCourant.getReponseGauche());
                ((TextView)findViewById(R.id.btnDesertRD)).setText(bTreeCourant.getReponseDroite());
                stepCourant++;
                if(estResolue()) {
                    resultat();
                }
            }
        });

        findViewById(R.id.btnDesertRD).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bTreeCourant = bTreeCourant.getRightTree();
                } catch (Exception e) {
                    Log.d("btree", "exception lors du changement de btreeCourant : " + e);
                }
                ((TextView)findViewById(R.id.txtViewDesertMag)).setText(bTreeCourant.getQuestion());
                ((TextView)findViewById(R.id.btnDesertRG)).setText(bTreeCourant.getReponseGauche());
                ((TextView)findViewById(R.id.btnDesertRD)).setText(bTreeCourant.getReponseDroite());
                stepCourant++;
                if(estResolue()) {
                    resultat();
                }
            }
        });
    }

    @Override
    public boolean estResolue() {
        return (stepCourant == FINAL_STEP);
    }

    @Override
    public void resultat() {
        findViewById(R.id.txtViewDesertMag).setVisibility(View.INVISIBLE);
        findViewById(R.id.btnDesertRG).setVisibility(View.INVISIBLE);
        findViewById(R.id.btnDesertRD).setVisibility(View.INVISIBLE);
        int pt = 0;
        Item item = null;
        if(bTreeCourant.getQuestion() == getString(R.string.DM_Res_Q3_G_G_RG)) {
            //gain de points de temps
            pt = giveRandomPointTemps();
            Log.d("btree", "gain de point de temps : " + pt);
            getJoueur().winTimePoint(pt);
        }
        else if(bTreeCourant.getQuestion() == getString(R.string.DM_Res_Q3_G_G_RD)) {
            //rien de special
        }
        else if(bTreeCourant.getQuestion() == getString(R.string.DM_Res_Q3_G_D_RG)) {
            //rien de special
        }
        else if(bTreeCourant.getQuestion() == getString(R.string.DM_Res_Q3_G_D_RD)) {
            //gain d'item = pierre ambre
            item = getItemByName(ITEM_ENIMGE);
            getJoueur().win(item);
        }
        else if(bTreeCourant.getQuestion() == getString(R.string.DM_Res_Q3_D_G_RG)) {
            //perte de points de temps
            pt = giveRandomPointTemps();
            getJoueur().looseTimePoint(pt);
            pt = pt*(-1);
            Log.d("btree", "perte de point de temps : " + pt);
        }
        else if(bTreeCourant.getQuestion() == getString(R.string.DM_Res_Q3_D_G_RD)) {
            //game over
            getJoueur().gameOver();
        }
        else if(bTreeCourant.getQuestion() == getString(R.string.DM_Res_Q3_D_D_RG)) {
            //enigme des blocs débloquée (lol)
        }
        else if(bTreeCourant.getQuestion() == getString(R.string.DM_Res_Q3_D_D_RD)) {
            //perte de points de temps
            pt = giveRandomPointTemps();
            getJoueur().looseTimePoint(pt);
            pt = pt*(-1);
            Log.d("btree", "perte de point de temps : " + pt);
        }
        else {
            Log.d("btree", "erreur dans resultat");
        }
        //on prépare l'intent de reponse
        Intent intent = getIntent();
        intent.putExtra("joueur", getJoueur());
        setResult(RESULT_OK, intent);
        showResult(pt, item, bTreeCourant.getQuestion());
    }

    @Override
    protected void onPause() {
        saveState();
        super.onPause();
    }
}
