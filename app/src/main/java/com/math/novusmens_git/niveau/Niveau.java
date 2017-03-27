package com.math.novusmens_git.niveau;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.math.novusmens_git.R;
import com.math.novusmens_git.menu.NarrationActivity;
import com.math.novusmens_git.personnage.Item;
import com.math.novusmens_git.personnage.ItemList;
import com.math.novusmens_git.personnage.Joueur;
import com.merkmod.achievementtoastlibrary.AchievementToast;

import java.util.ArrayList;

/**
 * Created by Math on 22/03/2017.
 */

public class Niveau extends AppCompatActivity {

    //public static final int NB_POINT_NIVEAU1 = 10;

    int numNiveau;
    PointList points;
    ItemList items;
    ArrayList<Point> points;
    ArrayList<Item> items;
    private MediaPlayer player;

   /* protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //on récupère l'intent qui a lancé l'activité
        Intent intent = getIntent();
        if (intent != null) {
            player = MediaPlayer.create(this, R.raw.pjs4_menu);
            player.setVolume(100, 100);
            joueur = intent.getExtras().getParcelable("joueur");
            Log.d("intent", "joueur point temps : " + joueur.getTimePoint());
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(joueur.getTimePoint()<12){
            if (joueur.getTimePoint()==0){ // si le joueur gagne mais n'a plus de point de temps, on lui en rajoute un pour qu'il n'est pas un game over
                //joueur.winTimePoint(1);
            }
            Intent intent = new Intent(this, NarrationActivity.class);
            intent.putExtra("joueur", joueur);
            startActivity(intent);
            finish();
        }
        else {
            if (joueur.getTimePoint() <= 0) {
                //Toast.makeText(this, "Vous avez perdu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, NarrationActivity.class);
                intent.putExtra("joueur", joueur);
                startActivity(intent);
                finish();
            } else {
                if (player == null) {
                    player = MediaPlayer.create(this, R.raw.pjs4_menu);
                    player.setVolume(100, 100);
                }
                player.start();
                player.setLooping(true);
                //Toast.makeText(this, "Vous avez actuellement " + joueur.getTimePoint() + " points de temps", Toast.LENGTH_SHORT).show();
                AchievementToast.makeAchievement(this, "Point de temps : " + joueur.getTimePoint(), AchievementToast.LENGTH_SHORT, ContextCompat.getDrawable(this, R.drawable.clickerordi)).show();
            }
        }
    }*/
    /*
    public Niveau(int numNiveau) {
        this.numNiveau = numNiveau;
        this.points = new ArrayList<Point>();
        if(numNiveau == 1) {
            setArrayListNiveau1();
        }
    }*/

    /*
    private void setArrayListNiveau1() {
        for(int i = 1; i <= NB_POINT_NIVEAU1; i++) {
            points.add(new Point(i, false));
        }
        Log.d("data", "on a set l'arrayList du niveau 1");
    }*/

    public int getNumNiveau() {
        return numNiveau;
    }

    public PointList getPoints() {
        return points;
    }

    public ItemList getItems() {
        return items;
    }

    public void setNumNiveau(int numNiveau) {
        this.numNiveau = numNiveau;
    }

    public void setPoints(PointList points) {
        this.points = points;
    }

    public void setItems(ItemList items) {
        this.items = items;
    }
}