package enigme;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import exceptionEnigme.VaseDéjàPleinException;
import exceptionEnigme.VaseVideException;
import niveau.IEnigme;

import com.math.novusmens_git.R;


public class EnigmeJarresActivity extends AppCompatActivity implements IEnigme {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigme_jarres);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

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
