package com.math.novusmens_git.enigme;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.math.novusmens_git.R;
import com.math.novusmens_git.database.PossedeItemDAO;
import com.math.novusmens_git.niveau.IEnigme;
import com.math.novusmens_git.personnage.Item;
import com.math.novusmens_git.personnage.Joueur;

public class EnigmePNJActivity extends Enigme {

    private static final String ITEM_NEEDED_1 = "Morceau d'âme 1(1/2)";
    private static final String ITEM_NEEDED_2 = "Morceau d'âme 2(1/2)";
    private static final String ITEM_ENIMGE = "Heart key";

    private int etape = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_niveau1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent intent = getIntent();
        if(intent != null){
            setJoueur((Joueur) intent.getExtras().getParcelable("joueur"));
            Log.d("intent", "joueur point temps : " + getJoueur().getTimePoint());
        }
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();
        setContentView(R.layout.activity_point_pnj);

        setNumEnigme(getResources().getInteger(R.integer.level1_enigmePNJ));
        setNumNiveau(getResources().getInteger(R.integer.level1));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 2 il est : " + getNumEnigme());

        //font perso
        Typeface typeFaceSav1 = Typeface.createFromAsset(getAssets(),"fonts/savior1.ttf");
        Typeface typeFaceSav3 = Typeface.createFromAsset(getAssets(),"fonts/savior3.ttf");
        TextView pnj = (TextView) findViewById(R.id.txtViewPNJ);
        TextView repG = (TextView) findViewById(R.id.btnAgree);
        TextView repD = (TextView) findViewById(R.id.btnDisagree);
        pnj.setTypeface(typeFaceSav3);
        repG.setTypeface(typeFaceSav1);
        repD.setTypeface(typeFaceSav1);
        pnj.setTextSize(25);
        repG.setTextSize(22);
        repD.setTextSize(22);

        if(estResolue()) {
            resultat();
        }


        findViewById(R.id.btnAgree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etape == 1) {
                    //on change les textes
                    ((TextView)findViewById(R.id.txtViewPNJ)).setText(R.string.pointPNJ_dialogProposition);
                    ((Button)findViewById(R.id.btnAgree)).setText(R.string.pointPNJ_btnAgree2);
                    ((Button)findViewById(R.id.btnDisagree)).setText(R.string.pointPNJ_btnDisagree2);
                    etape++;
                }
                else if(etape == 2) {
                    ((TextView)findViewById(R.id.txtViewPNJ)).setText(R.string.pointPNJ_dialogEnd);
                    findViewById(R.id.btnAgree).setVisibility(View.INVISIBLE);
                    findViewById(R.id.btnDisagree).setVisibility(View.INVISIBLE);
                }
            }
        });

        findViewById(R.id.btnDisagree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on change le texte du pnj
                ((TextView)findViewById(R.id.txtViewPNJ)).setText(R.string.pointPNJ_dialogEnd);
                //on desactive les boutons
                findViewById(R.id.btnAgree).setVisibility(View.INVISIBLE);
                findViewById(R.id.btnDisagree).setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    public boolean estResolue() {
        Item ame1 = getItemByName(ITEM_NEEDED_1);
        Item ame2 = getItemByName(ITEM_NEEDED_2);
        return (getJoueur().has(ame1) && getJoueur().has(ame2));
    }

    @Override
    public void resultat() {
        //on retire les morceau d'ame de l'inventaire du joueur
        Item ame1 = getItemByName(ITEM_NEEDED_1);
        Item ame2 = getItemByName(ITEM_NEEDED_2);
        getJoueur().dismiss(ame1);
        getJoueur().dismiss(ame2);
        //on les retire aussi de la table possede item
        PossedeItemDAO possedeItemDAO = new PossedeItemDAO(this);
        possedeItemDAO.open();
        possedeItemDAO.supprimer(ame1.getId());
        possedeItemDAO.supprimer(ame2.getId());
        //on lui ajoute la heart key
        Item key = getItemByName(ITEM_ENIMGE);
        getJoueur().win(key);
        //on prepare l'intent de retour vers la map du niveau
        Intent intent = getIntent();
        intent.putExtra("joueur", getJoueur());
        setResult(RESULT_OK, intent);
        //on ouvre le dialog pour montrer les résultat
        showResult(0, key, "Humain, je t'ai sous-estimé. Cette vieille relique ne me sert plus depuis longtemps, elle pourra peut-être t'être utile");
    }

    @Override
    protected void onPause() {
        saveState();
        super.onPause();
    }

}
