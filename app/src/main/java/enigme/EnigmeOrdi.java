package enigme;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.math.novusmens_git.R;

import niveau.IEnigme;


public class EnigmeOrdi extends AppCompatActivity implements IEnigme {

    private final static String PASSWORD = "AnimusRoot12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigme_ordi);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        int i = getResources().getConfiguration().orientation;
        Log.d("orientation", "" + i);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            findViewById(R.id.txtPassword).setVisibility(View.INVISIBLE);
            final ProgressBar barDegat = (ProgressBar) findViewById(R.id.progressBarDegat);
            barDegat.setMax(100);
            final ImageButton imgBtnCrane = (ImageButton) findViewById(R.id.imgBtnCrane);

            imgBtnCrane.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(barDegat.getProgress() == barDegat.getMax()) {
                        //on enlève le crane et on affiche le mot de passe
                        imgBtnCrane.setVisibility(View.INVISIBLE);
                        displayPassword();
                    }
                    else {
                        barDegat.incrementProgressBy(5);
                    }
                }
            });
        }
        else {
            Log.d("text", "On est là dans le else");
            findViewById(R.id.imgBtnEnter).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("text", "On est là dans le onClick");
                    EditText editText = (EditText) findViewById(R.id.editTextClavier);
                    Log.d("text", String.valueOf(editText.getText()));
                    if(String.valueOf(editText.getText()).equals(PASSWORD)) {
                        Log.d("text", "On est là dans le if");
                        estResolue();
                        Toast.makeText(getApplicationContext(), "Enigme résolue !", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void displayPassword() {
        TextView password = (TextView) findViewById(R.id.txtPassword);
        password.setText(PASSWORD);
        password.setTextSize(45);
        password.setGravity(Gravity.CENTER);
        password.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Checks the orientation of the screen
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean estResolue() {
        return true;
    }
}
