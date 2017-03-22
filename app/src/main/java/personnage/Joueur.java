package personnage;

import java.util.ArrayList;

/**
 * Created by user on 06/03/2017.
 */

public class Joueur {

    private static int timePoint; // number of time points of the player
    private static ArrayList<String> object; // List of player's object

    public Joueur(int timePoint){
        this.timePoint = timePoint;
        object = new ArrayList<String>();
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

    public ArrayList<String> getItems() {
        return object;
    }

    public void win(String s) {
        if(!object.contains(s))
            object.add(s);
    }
}
