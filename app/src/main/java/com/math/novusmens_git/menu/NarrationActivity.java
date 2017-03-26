package com.math.novusmens_git.menu;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.math.novusmens_git.R;
import com.math.novusmens_git.enigme.EnigmeOrdiActivity;
import com.math.novusmens_git.niveau.Niveau1Activity;
import com.math.novusmens_git.personnage.Joueur;


public class NarrationActivity extends AppCompatActivity {
    private String[] text = new String[4];
    private int[] image = new int[4];
    private String[] gameOver = new String[4];
    private int[] imageOver = new int[4];
    private int numero;
    private TypeWriter intro;
    private final String EXTRA_MUSIQUE = "musique";
    private MediaPlayer player;
    private Joueur joueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); // bloque le téléphone en paysage
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_narration);
        Intent intent = getIntent();
        if(intent != null){
            player = MediaPlayer.create(this, R.raw.pjs4_menu);
            player.setVolume(100, 100);
            player.seekTo(intent.getIntExtra(EXTRA_MUSIQUE,0));
            Bundle bundle = intent.getExtras();
            if(bundle != null && bundle.containsKey("joueur")) {
                joueur = intent.getExtras().getParcelable("joueur");
                Log.d("intent", "joueur point temps : " + joueur.getTimePoint());
            }
            else {
                Log.d("intent", "le joueur est null narration");
            }

        }
        getSupportActionBar().hide();

        //font perso
        TextView narration = (TextView) findViewById(R.id.typeWriter);
        TextView tapToContinue = (TextView) findViewById(R.id.indication);
        Typeface typeFaceSav1 = Typeface.createFromAsset(getAssets(),"fonts/savior1.ttf");
        Typeface typeFaceSav2 = Typeface.createFromAsset(getAssets(),"fonts/savior2.ttf");
        narration.setTypeface(typeFaceSav1);
        narration.setTextSize(25);
        tapToContinue.setTypeface(typeFaceSav2);
        tapToContinue.setTextSize(20);

        text[0]="N'entre pas docilement dans cette douce nuit";//"A l'aube de ce nouveau siècle, le monde que nous connaissons à disparu...";
        text[1]="Le vieil âge doit gronder, tempêter, au déclin du jour";//"L'Homme, en se développant, est devenu avide de pouvoir et à précipiter le monde dans une période de guerre...";
        text[2]="Hurler, s'enrager, à l'agonie de la lumière";//"Les Etats se montant les uns contre les autres, on finit par comettre l'irréparable...";
        text[3]="Ton temps est compté, ne l'oublie pas !";//"Causant la fin de la civilisation humaine.";
        image[0] = R.drawable.tube_dimension_resize;
        image[1] = R.drawable.cote_devaste_resize;
        image[2] = R.drawable.bombe_h_resize;
        image[3] = R.drawable.bombe_h_resize;
        gameOver[0]="Alors que tu approchais du but, tu n'as pas été assez fort";
        gameOver[1]="Tu as contemplé le déclin de ce nouveau jour";
        gameOver[2]="Et chassé la lumière pour embrasser les ténèbres";
        gameOver[3]="Game Over";
        imageOver[0]=R.drawable.vers_lumiere_resize;
        imageOver[1]=R.drawable.cote_devaste_resize;
        imageOver[2]=R.drawable.limbes_resize;
        imageOver[3]=R.drawable.bombe_h_resize;
        numero = 0;
        intro = (TypeWriter) findViewById(R.id.typeWriter);
        ImageView img = (ImageView) findViewById(R.id.imageView);
        if(joueur != null && joueur.getTimePoint() > 0) {
            img.setImageResource(image[numero]);
            intro.setCharacterDelay(100);
            intro.animateText(text[0]);
        }
        else{
            img.setImageResource(imageOver[numero]);
            intro.setCharacterDelay(100);
            intro.animateText(gameOver[0]);
        }
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

    protected void onResume(){
        super.onResume();
        if(player == null){
            player = MediaPlayer.create(this, R.raw.pjs4);
            player.setVolume(100, 100);
        }
        player.start();
        player.setLooping(true);
    }

    protected void onPause(){
        super.onPause();
        player.stop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(player != null){
            player.release();
            player=null;
        }
        Log.i("iut","je suis dans onDestroy");
    }

    protected void suite(View view) throws InterruptedException {
        Log.i("iut","Le texte est fini ? " + intro.isEnded());
        if(intro.isEnded()) {
            numero++;
            //getRessource.getString
            ImageView img = (ImageView) findViewById(R.id.imageView);
            //img.setImageResource(R.drawable.desert);
            if(numero > 3) {
                //Intent intent = new Intent(this, Niveau1Activity.class);
                Log.d("intent", "on es a la derniere etape de narration activity");
                if(joueur != null && joueur.getTimePoint() > 0) {
                    Log.d("intent", "on commence une partie");
                    Intent intent = new Intent(this, Niveau1Activity.class);
                    intent.putExtra(EXTRA_MUSIQUE, player.getCurrentPosition()); //sauvegarde la position courrante de la musique
                    intent.putExtra("joueur", joueur);
                    startActivity(intent);
                }
                else {
                    Log.d("intent", "est mort");
                }
                finish();
            }
            else {
                if(joueur != null && joueur.getTimePoint() > 0) {
                    img.setImageResource(image[numero]);
                    intro.setCharacterDelay(100);
                    intro.animateText(text[numero]);
                    //t.startAnimation(AnimationUtils.loadAnimation(Level1Activity.this,android.R.anim.fade_in));
                    // android.R.anim.accelerate_interpolator;
                }
                else{
                    img.setImageResource(imageOver[numero]);
                    intro.setCharacterDelay(100);
                    intro.animateText(gameOver[numero]);
                }
            }
        }
        else {
            intro.setCharacterDelay(20);
        }
    }
}
