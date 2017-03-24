package com.math.novusmens_git.personnage;

import java.util.ArrayList;

/**
 * Created by user on 06/03/2017.
 */

public class Joueur {

    private static int timePoint; // number of time points of the player
    private static ArrayList<Item> object; // List of player's object

    public Joueur(int timePoint){
        this.timePoint = timePoint;
        object = new ArrayList<Item>();
    }

    public static int getTimePoint() {
        return timePoint;
    }

    public static void move() {
        timePoint--;
    }

    public static void endLevel() {
        timePoint = timePoint/2;
    }

    public ArrayList<Item> getItems() {
        return object;
    }

    public void win(Item i) {
        if(!object.contains(i))
            object.add(i);
    }

    public void winTimePoint(int quantite) {
        timePoint += quantite;
    }

    public void looseTimePoint(int quantite) {
        timePoint -= quantite;
        if(timePoint < 0) {
            timePoint = 0;
        }
    }
}
