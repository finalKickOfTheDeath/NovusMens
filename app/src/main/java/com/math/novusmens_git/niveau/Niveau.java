package com.math.novusmens_git.niveau;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.math.novusmens_git.personnage.Item;
import com.math.novusmens_git.personnage.ItemList;

import java.util.ArrayList;

/**
 * Created by Math on 22/03/2017.
 */

public class Niveau extends AppCompatActivity {

    //public static final int NB_POINT_NIVEAU1 = 10;

    int numNiveau;
    PointList points;
    ItemList items;

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