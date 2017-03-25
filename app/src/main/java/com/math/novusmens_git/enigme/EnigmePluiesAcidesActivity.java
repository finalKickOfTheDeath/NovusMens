package com.math.novusmens_git.enigme;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.math.novusmens_git.R;

public class EnigmePluiesAcidesActivity extends Enigme {

    private BTree Niveau1;
    private BTree Niveau2_G;
    private BTree Niveau2_D;
    private BTree Niveau3_G_G;
    private BTree Niveau3_G_D;
    private BTree Niveau3_D_G;
    private BTree Niveau3_D_D;

    private BTree Resultat_Q3_G_G_RG;
    private BTree Resultat_Q3_G_G_RD;
    private BTree Resultat_Q3_G_D_RG;
    private BTree Resultat_Q3_G_D_RD;
    private BTree Resultat_Q3_D_G_RG;
    private BTree Resultat_Q3_D_G_RD;
    private BTree Resultat_Q3_D_D_RG;
    private BTree Resultat_Q3_D_D_RD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enigme_pluies_acides);
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setNumEnigme(getResources().getInteger(R.integer.level1_enigmeNarrative3PluiesAcides));
        setNumNiveau(getResources().getInteger(R.integer.level1));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 9 il est : " + getNumEnigme());

        /*-------------- CREATION DES MODULES DE NIVEAUX --------------*/
        Niveau1 = new BTree(getString(R.string.PA_Q1));
        try {
            Niveau1.setReponseGauche(getString(R.string.PA_Q1_RG));
            Niveau1.setReponseDroite(getString(R.string.PA_Q1_RD));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Niveau2_G = new BTree(getString(R.string.PA_Q2_G));
        try {
            Niveau2_G.setReponseGauche(getString(R.string.PA_Q2_G_RG));
            Niveau2_G.setReponseDroite(getString(R.string.PA_Q2_G_RD));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Niveau2_D = new BTree(getString(R.string.PA_Q2_D));
        try {
            Niveau2_D.setReponseGauche(getString(R.string.PA_Q2_D_RG));
            Niveau2_D.setReponseDroite(getString(R.string.PA_Q2_D_RD));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Niveau3_G_G = new BTree(getString(R.string.PA_Q3_G_G));
        try {
            Niveau3_G_G.setReponseGauche(getString(R.string.PA_Q3_G_G_RG));
            Niveau3_G_G.setReponseDroite(getString(R.string.PA_Q3_G_G_RD));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Niveau3_G_D = new BTree(getString(R.string.PA_Q3_G_D));
        try {
            Niveau3_G_D.setReponseGauche(getString(R.string.PA_Q3_G_D_RG));
            Niveau3_G_D.setReponseDroite(getString(R.string.PA_Q3_G_D_RD));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Niveau3_D_G = new BTree(getString(R.string.PA_Q3_D_G));
        try {
            Niveau3_D_G.setReponseGauche(getString(R.string.PA_Q3_D_G_RG));
            Niveau3_D_G.setReponseDroite(getString(R.string.PA_Q3_D_G_RD));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Niveau3_D_D = new BTree(getString(R.string.PA_Q3_D_D));
        try {
            Niveau3_D_D.setReponseGauche(getString(R.string.PA_Q3_D_D_RG));
            Niveau3_D_D.setReponseDroite(getString(R.string.PA_Q3_D_D_RD));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Resultat_Q3_G_G_RG= new BTree(getString(R.string.PA_Res_Q3_G_G_RG));
        Resultat_Q3_G_G_RD= new BTree(getString(R.string.PA_Res_Q3_G_G_RD));
        Resultat_Q3_G_D_RG= new BTree(getString(R.string.PA_Res_Q3_G_D_RG));
        Resultat_Q3_G_D_RD= new BTree(getString(R.string.PA_Res_Q3_G_D_RD));
        Resultat_Q3_D_G_RG= new BTree(getString(R.string.PA_Res_Q3_D_G_RG));
        Resultat_Q3_D_G_RD= new BTree(getString(R.string.PA_Res_Q3_D_G_RD));
        Resultat_Q3_D_D_RG= new BTree(getString(R.string.PA_Res_Q3_D_D_RG));
        Resultat_Q3_D_D_RD= new BTree(getString(R.string.PA_Res_Q3_D_D_RD));

        /*-------------- FIN DE CREATION DES MODULES DE NIVEAUX --------------*/

        /*-------------- LIAISON DES MODULES --------------*/

        TextView question=(TextView)findViewById(R.id.question);
        question.setText(Niveau1.getQuestion());

        TextView reponseGauche=(TextView)findViewById(R.id.reponseGauche);
        reponseGauche.setText(Niveau1.getReponseGauche());


        TextView reponseDroite=(TextView)findViewById(R.id.reponseDroite);
        reponseDroite.setText(Niveau1.getReponseDroite());

        /*-------------- FIN DE LIAISON DES MODULES --------------*/

    }

    public void repondre(View v){
        int id = v.getId();
        TextView question=(TextView)findViewById(R.id.question);
        TextView reponseDroite=(TextView)findViewById(R.id.reponseDroite);
        TextView reponseGauche=(TextView)findViewById(R.id.reponseGauche);

        /*-------------- NIVEAU 2 --------------*/
        switch(id){
            case R.id.reponseGauche :
                question.setText(Niveau2_G.getQuestion());
                reponseGauche.setText(Niveau2_G.getReponseGauche());
                reponseDroite.setText(Niveau2_G.getReponseDroite());

            case R.id.reponseDroite :
                question.setText(Niveau2_D.getQuestion());
                reponseGauche.setText(Niveau2_D.getReponseGauche());
                reponseDroite.setText(Niveau2_D.getReponseDroite());
        }

        /*-------------- NIVEAU 3 --------------*/

        switch(id){
            case R.id.reponseGauche :
                question.setText(Niveau3_G_G.getQuestion());
                reponseGauche.setText(Niveau3_G_G.getReponseGauche());
                reponseDroite.setText(Niveau3_G_G.getReponseDroite());

            case R.id.reponseDroite :
                question.setText(Niveau3_G_D.getQuestion());
                reponseGauche.setText(Niveau3_G_D.getReponseGauche());
                reponseDroite.setText(Niveau3_G_D.getReponseDroite());
        }


    }

    @Override
    public boolean estResolue() {
        return false;
    }

    @Override
    public void resultat() {

    }
}
