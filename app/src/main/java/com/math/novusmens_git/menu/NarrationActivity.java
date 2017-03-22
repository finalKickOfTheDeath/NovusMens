package com.math.novusmens_git.menu;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.math.novusmens_git.R;

import enigme.EnigmeOrdiActivity;

public class NarrationActivity extends AppCompatActivity {
    private String[] text = new String[4];
    private int numero;
    private TypeWriter intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // bloque le téléphone en paysage
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_narration);
        Intent intent = getIntent();
        getSupportActionBar().hide();
        text[0]="Ne rentre pas docilement dans cette douce nuit";//"A l'aube de ce nouveau siècle, le monde que nous connaissons à disparu...";
        text[1]="Le vieil âge doit gronder, tempêter, au déclin du jour";//"L'Homme, en se développant, est devenu avide de pouvoir et à précipiter le monde dans une période de guerre...";
        text[2]="Hurler, s'enrager, à l'agonie de la lumière";//"Les Etats se montant les uns contre les autres, on finit par comettre l'irréparable...";
        text[3]="Le temps t'es compté, ne l'oublie pas !";//"Causant la fin de la civilisation humaine.";
        numero = 0;
        intro = (TypeWriter) findViewById(R.id.typeWriter);
        /*RelativeLayout r = (RelativeLayout) findViewById(R.id.activity_narration);
        r.setOnLongClickListener(new View.OnLongClickListener(){
            public boolean onLongClick(View v) {
                intro.setCharacterDelay(20);
                return false;
            }
        });*/
        //intro.setScaleX(1);
        //Add a character every 150ms
        intro.setCharacterDelay(100);
        intro.animateText(text[0]);

        findViewById(R.id.activity_narration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    suite(v);
                } catch (InterruptedException ie) {
                    Log.i("catch", ie.toString());
                }
            }
        });

    }

    protected void suite(View view) throws InterruptedException {
        Log.i("iut","Le texte est fini ? " + intro.isEnded());
        if(intro.isEnded()){
            numero++;
            //getRessource.getString
            ImageView img = (ImageView) findViewById(R.id.imageView);
            img.setImageResource(R.drawable.desert);
            if(numero>3) {
                //Intent intent = new Intent(this, Niveau1Activity.class);
                Intent intent = new Intent(this, EnigmeOrdiActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                intro.setCharacterDelay(100);
                intro.animateText(text[numero]);
                //t.startAnimation(AnimationUtils.loadAnimation(Level1Activity.this,android.R.anim.fade_in));
                // android.R.anim.accelerate_interpolator;
            }
        }
        else {
            intro.setCharacterDelay(20);
        }
    }
}
