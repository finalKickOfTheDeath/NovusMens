package niveau;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.math.novusmens_git.R;
import com.math.novusmens_git.menu.Joueur;
import com.math.novusmens_git.menu.Menu;

import enigme.EnigmeJarresActivity;
import enigme.EnigmeOrdi;

public class Niveau1Activity extends AppCompatActivity {
    // on met le package d'où provient l'intent
    public final static String EXTRA_MESSAGE = "com.math.novusmens_git.niveau.TOWARDENIGME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // on récupère l'intent qui a lancé l'activité
        Intent intent = getIntent();
        // pour mettre l'activité en fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.niveau1);
        //forcer l'orientation en mode paysage
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // cacher l'action bar
        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        findViewById(R.id.btn4_enigmeJarres).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("iut","Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeJarresActivity.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme jarres");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn6_enigmeOrdi).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Joueur.move();
                Log.i("iut","Vous avez actuellement " + Joueur.getTimePoint() + " points de temps");
                Intent intent = new Intent(v.getContext(), EnigmeOrdi.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme ordi");
                startActivity(intent);
            }
        });
        Toast.makeText(this, "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(this, "Vous avez actuellement " + Joueur.getTimePoint() + " points de temps", Toast.LENGTH_SHORT).show();
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {

        Log.i("iut","Je suis dans onActivityResult ");
        // on récupère le statut de retour de l'activité 2 c'est à dire l'activité numéro 1000
        //if(requestCode==1000){
            // si le code de retour est égal à 1 on stoppe l'activité 1
            if(resultCode==1){
                finish();
            }
       // }
        super.onActivityResult (requestCode, resultCode, data);
    }


}
