package com.math.novusmens_git.enigme;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.math.novusmens_git.R;
import com.math.novusmens_git.niveau.Niveau1Activity;
import com.math.novusmens_git.personnage.Joueur;
import com.merkmod.achievementtoastlibrary.AchievementToast;

public class EnigmeBlocsActivity extends Enigme {
    private Carte carte;
    private Integer déplacements;
    private final int LONGUEUR = 9;
    private final int LARGEUR = 6;
    private final int NB_COUPS = 28;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enigme_blocs);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent intent = getIntent();
        if(intent != null){
            setJoueur((Joueur) intent.getExtras().getParcelable("joueur"));
            Log.d("intent", "joueur point temps : " + getJoueur().getTimePoint());
        }
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setNumNiveau(getResources().getInteger(R.integer.level1));
        setNumEnigme(getResources().getInteger(R.integer.level1_enigmeBloc));
        déplacements = NB_COUPS;
        ((TextView)findViewById(R.id.nbPas)).setText(déplacements.toString());
        initCarte();
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 8 il est : " + getNumEnigme());

        //font persos
        Typeface typeFaceSav = Typeface.createFromAsset(getAssets(),"fonts/savior1.ttf");
        TextView txtPas = (TextView) findViewById(R.id.tVPas);
        TextView nbPas = (TextView) findViewById(R.id.nbPas);
        TextView retry = (TextView) findViewById(R.id.buttonRetour);
        txtPas.setTypeface(typeFaceSav);
        nbPas.setTypeface(typeFaceSav);
        retry.setTypeface(typeFaceSav);
        txtPas.setTextSize(35);
        nbPas.setTextSize(35);
        retry.setTextSize(25);

    }

    protected void onResume(){
        super.onResume();
        BottomDialog bottomDialog = new BottomDialog.Builder(this)
                .setTitle("Enigme")
                .setContent("Devant vous se dresse un chemin jonché de barrils et coupé par des fleuves d'eau irradiée. L'eau est peu profonde et les barrils, une fois dans l'eau, permettent de traverser. Un orage approche, il faut vous dépêcher !")
                .setIcon(R.drawable.clickerordi)
                .setPositiveText("Fermer")
                .setCancelable(true)
                .setPositiveBackgroundColorResource(R.color.black)
                .setPositiveTextColorResource(R.color.white)
                .onPositive(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .build();
        bottomDialog.show();
    }

    @Override
    public boolean estResolue() {
        return carte.estFini();
    }

    @Override
    public void resultat() {
        //on obtient des points de temps
        int pt = giveRandomPointTemps();
        Log.d("enigme", "point de temps avant = " + getJoueur().getTimePoint());
        Log.d("enigme", "gain point temps : " + pt);
        //on met les points de temps du joueur à jour
        getJoueur().winTimePoint(pt);
        Log.d("enigme", "point de temps apres = " + getJoueur().getTimePoint());
        //on prépare l'intent de reponse
        Intent intent = getIntent();
        intent.putExtra("joueur", getJoueur());
        setResult(RESULT_OK, intent);
        showResult(pt, null, "Alors que vous venez de traverser le chemin, l'orage éclate et le chemin se retrouve sous les pluies acides. Envahit par l'adrénaline, vous décidez de poursuivre votre chemin au lieu de vous reposer. Ce sont des heures de recherche de gagnées.");
    }

    private void initCarte() {
        if (carte == null)
            carte = new Carte(LONGUEUR, LARGEUR, new Point(0,0), new Point(LARGEUR-1,LONGUEUR-2), (TableLayout) findViewById(R.id.carte));
        else{
            carte.clear();
        }
        carte.ajouter(new Personnage(this),new Point(0,0));
        carte.ajouter(new Trou(this),new Point(0,4));
        carte.ajouter(new Trou(this),new Point(0,7));
        carte.ajouter(new Trou(this),new Point(1,1));
        carte.ajouter(new Trou(this),new Point(1,2));
        carte.ajouter(new Trou(this),new Point(1,4));
        carte.ajouter(new Trou(this),new Point(2,0));
        carte.ajouter(new Trou(this),new Point(2,1));
        carte.ajouter(new Trou(this),new Point(2,3));
        carte.ajouter(new Trou(this),new Point(2,4));
        carte.ajouter(new Trou(this),new Point(2,7));
        carte.ajouter(new Trou(this),new Point(2,8));
        carte.ajouter(new Trou(this),new Point(3,0));
        carte.ajouter(new Trou(this),new Point(3,3));
        carte.ajouter(new Trou(this),new Point(3,4));
        carte.ajouter(new Trou(this),new Point(3,6));
        carte.ajouter(new Trou(this),new Point(3,8));
        carte.ajouter(new Trou(this),new Point(4,3));
        carte.ajouter(new Trou(this),new Point(5,4));
        carte.ajouter(new Trou(this),new Point(5,5));
        carte.ajouter(new Trou(this),new Point(5,6));
        carte.ajouter(new Trou(this),new Point(5,7));

        carte.ajouter(new Caisse(this.getApplicationContext()),new Point(0,6));
        carte.ajouter(new Caisse(this.getApplicationContext()),new Point(1,0));
        carte.ajouter(new Caisse(this.getApplicationContext()),new Point(1,3));
        carte.ajouter(new Caisse(this.getApplicationContext()),new Point(1,5));
        carte.ajouter(new Caisse(this.getApplicationContext()),new Point(1,7));
        carte.ajouter(new Caisse(this.getApplicationContext()),new Point(4,1));
        carte.ajouter(new Caisse(this.getApplicationContext()),new Point(4,4));
        carte.ajouter(new Caisse(this.getApplicationContext()),new Point(4,6));
        carte.ajouter(new Caisse(this.getApplicationContext()),new Point(4,8));
        carte.ajouter(new Caisse(this.getApplicationContext()),new Point(5,3));
    }

    public void onClickRetour(View view){
        badEnding();
        initCarte();
        déplacements = NB_COUPS;
        ((TextView)findViewById(R.id.nbPas)).setText(déplacements.toString());
    }

    public void onClickDéplacement(View view){
        --déplacements;
        ((TextView)findViewById(R.id.nbPas)).setText(déplacements.toString());

        int id = view.getId();
        switch(id){
            case R.id.buttonHaut :
                carte.déplacerPersonnage(-1,0);
                break;
            case R.id.buttonBas :
                carte.déplacerPersonnage(1,0);
                break;
            case R.id.buttonGauche :
                carte.déplacerPersonnage(0,-1);
                break;
            case R.id.buttonDroit:
                carte.déplacerPersonnage(0,1);
                break;
        }
        if(carte.estFini())
            goodEnding();
        else if (déplacements == 0) {
            badEnding();
            initCarte();
            déplacements = NB_COUPS;
            ((TextView)findViewById(R.id.nbPas)).setText(déplacements.toString());
        }
    }

    private void badEnding() {
        AchievementToast.makeAchievement(this, "-1 point de temps", AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(getApplicationContext(), R.drawable.clickerordi)).show();
        //Toast.makeText(this, "-1 point de temps", Toast.LENGTH_SHORT).show();
    }

    private void goodEnding() {
        resultat();
        saveState();
        //Toast.makeText(this, "gagné", Toast.LENGTH_SHORT).show();
    }
}
