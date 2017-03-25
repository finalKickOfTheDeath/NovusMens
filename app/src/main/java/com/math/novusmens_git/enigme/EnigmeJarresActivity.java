package com.math.novusmens_git.enigme;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.math.novusmens_git.R;
import com.math.novusmens_git.exceptionEnigme.VaseDéjàPleinException;
import com.math.novusmens_git.exceptionEnigme.VaseVideException;
import com.math.novusmens_git.personnage.Joueur;


public class EnigmeJarresActivity extends Enigme {

    private View.OnClickListener onClickListenerButton10l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(donneur == null) {
                donneur = vase10l;
                arrow10l.setRotation(90); //rotation pour annuler
                arrow7l.setRotation(0);
                arrow3l.setRotation(0);
            }
            else if(receveur == null) { //si on touche le bouton mais qu'il y a déjà un donneur c'est qu'on est receveur
                receveur = vase10l;
                try {
                    verser(donneur, receveur);
                    donneur.getTextView().setText(donneur.toString());
                    receveur.getTextView().setText(receveur.toString());
                    reset();
                    if(estResolue()){
                        Toast.makeText(getApplicationContext(), "Enigme résolue", Toast.LENGTH_SHORT).show();
                        resultat();
                        //finish();
                    }
                } catch (VaseVideException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("toast", "toastvide");
                    reset();
                } catch (VaseDéjàPleinException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("toast", "toastplein");
                    reset();
                } finally {
                    arrow10l.setRotation(180);
                    arrow7l.setRotation(180);
                    arrow3l.setRotation(180);
                }
            }
        }
    };

    private View.OnClickListener onClickListenerBtn7l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(donneur == null) {
                donneur = vase7l;
                arrow10l.setRotation(0);
                arrow7l.setRotation(90);
                arrow3l.setRotation(0);
            }
            else if(receveur == null) {
                receveur = vase7l;
                try {
                    verser(donneur, receveur);
                    donneur.getTextView().setText(donneur.toString());
                    receveur.getTextView().setText(receveur.toString());
                    reset();
                    if(estResolue()){
                        Toast.makeText(getApplicationContext(), "Enigme résolue", Toast.LENGTH_SHORT).show();
                        resultat();
                        //finish();
                    }
                } catch (VaseVideException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("toast", "toastvide");
                    reset();
                } catch (VaseDéjàPleinException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("toast", "toastplein");
                    reset();
                } finally {
                    arrow10l.setRotation(180);
                    arrow7l.setRotation(180);
                    arrow3l.setRotation(180);
                }
            }
        }
    };

    private View.OnClickListener onClickListenerBtn3l = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(donneur == null) {
                donneur = vase3l;
                arrow10l.setRotation(0);
                arrow7l.setRotation(0);
                arrow3l.setRotation(90);
            }
            else if(receveur == null) {
                receveur = vase3l;
                try {
                    verser(donneur, receveur);
                    donneur.getTextView().setText(donneur.toString());
                    receveur.getTextView().setText(receveur.toString());
                    reset();
                    if(estResolue()){
                        Toast.makeText(getApplicationContext(), "Enigme résolue", Toast.LENGTH_SHORT).show();
                        resultat();
                        //finish();
                    }
                } catch (VaseVideException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("toast", "toastvide");
                    reset();
                } catch (VaseDéjàPleinException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("toast", "toastplein");
                    reset();
                } finally {
                    arrow10l.setRotation(180);
                    arrow7l.setRotation(180);
                    arrow3l.setRotation(180);
                }
            }
        }
    };

    private final static int CONTENANCE_FINALE = 5;

    // déclaré ici pour les tests
    private TextView text10l;
    private TextView text7l;
    private TextView text3l;

    // déclaré ici pour les tests
    private ImageButton arrow10l;
    private ImageButton arrow7l;
    private ImageButton arrow3l;

    private Vase vase10l;
    private Vase vase7l;
    private Vase vase3l;

    private Vase donneur;
    private Vase receveur;

    private final String EXTRA_MUSIQUE = "musique";
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enigme_jarres);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Intent intent = getIntent();
        if(intent != null){
            player = MediaPlayer.create(this, R.raw.pjs4);
            player.setVolume(100, 100);
            player.seekTo(intent.getIntExtra(EXTRA_MUSIQUE,0));
            setJoueur((Joueur) intent.getExtras().getParcelable("joueur"));
            Log.d("intent", "joueur point temps : " + getJoueur().getTimePoint());
        }
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        setNumNiveau(getResources().getInteger(R.integer.level1));
        setNumEnigme(getResources().getInteger(R.integer.level1_enigmeJarre));
        Log.d("data", "num niveau devrait être 1 il est : " + getNumNiveau());
        Log.d("data", "num enigme devrait être 3 il est : " + getNumEnigme());

        text10l = (TextView)findViewById(R.id.textViewcontenance10l);
        text7l = (TextView)findViewById(R.id.textViewcontenance7l);
        text3l = (TextView)findViewById(R.id.textViewcontenance3l);

        arrow10l = (ImageButton) findViewById(R.id.imgBtn10l);
        arrow7l = (ImageButton) findViewById(R.id.imgBnt7l);
        arrow3l = (ImageButton) findViewById(R.id.imgBtn3l);

        findViewById(R.id.imgBtn10l).setOnClickListener(onClickListenerButton10l);
        findViewById(R.id.imgBnt7l).setOnClickListener(onClickListenerBtn7l);
        findViewById(R.id.imgBtn3l).setOnClickListener(onClickListenerBtn3l);

        vase10l = new Vase(10, 10, text10l);
        vase7l = new Vase(7, 0, text7l);
        vase3l = new Vase(3, 0, text3l);
        arrow10l.setRotation(180);
        donneur = null;
        receveur = null;

    }

    //utilisé pour les tests
    public EnigmeJarresActivity() {
        vase10l = new Vase(10, 10, text10l);
        vase7l = new Vase(7, 0, text7l);
        vase3l = new Vase(3, 0, text3l);
        donneur = null;
        receveur = null;
    }

    public void verser(Vase donneur, Vase receveur) throws VaseVideException, VaseDéjàPleinException {
        donneur.verser(receveur);
    }

    public void reset() {
        donneur = null;
        receveur = null;
    }

    @Override
    public boolean estResolue() {
        return (vase10l.getContenance() == CONTENANCE_FINALE && vase7l.getContenance() == CONTENANCE_FINALE);
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
        showResult(pt);
    }

    private void showResult(int point) {
        BottomDialog bottomDialog = new BottomDialog.Builder(this)
                .setTitle("Resultats")
                .setContent("Point(s) de temps gagné(s) : " + point)
                .setIcon(R.drawable.wolf_head)
                .setPositiveText("Continuer")
                .setPositiveBackgroundColorResource(R.color.back)
                .setPositiveTextColorResource(R.color.white)
                .onPositive(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {
                        Log.d("enigme", "on va finish");
                        finish();
                    }
                })
                .build();
        bottomDialog.show();
    }

    @Override
    protected void onPause() {
        saveState();
        super.onPause();
    }

    //utilisée pour les tests
    public Vase getVase10l() {
        return vase10l;
    }

    //utilisée pour les tests
    public void setVase10l(Vase vase10l) {
        this.vase10l = vase10l;
    }

    //utilisée pour les tests
    public Vase getVase7l() {
        return vase7l;
    }

    //utilisée pour les tests
    public void setVase7l(Vase vase7l) {
        this.vase7l = vase7l;
    }

    //utilisée pour les tests
    public Vase getVase3l() {
        return vase3l;
    }

    //utilisée pour les tests
    public void setVase3l(Vase vase3l) {
        this.vase3l = vase3l;
    }

}
