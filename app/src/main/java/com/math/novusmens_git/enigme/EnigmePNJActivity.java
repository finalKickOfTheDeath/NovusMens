package com.math.novusmens_git.enigme;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.math.novusmens_git.R;
import com.math.novusmens_git.niveau.IEnigme;
import com.math.novusmens_git.personnage.Item;

public class EnigmePNJActivity extends AppCompatActivity implements IEnigme {

    private final static int NUM_ENGME = R.integer.level1_enigmePNJ;
    private static int etape = 1;
    private Item ame1;
    private Item ame2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // pour mettre l'activité en fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_niveau1);
        //forcer l'orientation en mode paysage
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // cacher l'action bar
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();
        setContentView(R.layout.activity_point_pnj);


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
        return (ame1 != null && ame2 != null);
    }
}
