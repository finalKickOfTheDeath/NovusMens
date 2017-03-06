package niveau;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.math.novusmens_git.R;

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
                Intent intent = new Intent(v.getContext(), EnigmeJarresActivity.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme jarres");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn6_enigmeOrdi).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EnigmeOrdi.class);
                intent.putExtra("EXTRA_MESSAGE", "contenu additionnel pour l'énigme ordi");
                startActivity(intent);
            }
        });

    }

}
