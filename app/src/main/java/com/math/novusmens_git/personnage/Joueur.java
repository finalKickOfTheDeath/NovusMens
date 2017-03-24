package com.math.novusmens_git.personnage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by user on 06/03/2017.
 */

public class Joueur implements Parcelable {

    private int timePoint; // number of time points of the player
    private ItemList object; // List of player's object

    public Joueur(int timePoint){
        this.timePoint = timePoint;
        object = new ItemList();
    }

    public Joueur(Parcel in) {
        getFromParcel(in);
    }

    public static final Creator<Joueur> CREATOR = new Creator<Joueur>() {
        @Override
        public Joueur createFromParcel(Parcel in) {
            return new Joueur(in);
        }

        @Override
        public Joueur[] newArray(int size) {
            return new Joueur[size];
        }
    };

    public int getTimePoint() {
        return timePoint;
    }

    public ArrayList<Item> getItems() {
        return object;
    }

    /*
    public int getPointTemps() {
        return timePoint;
    }
    */

    public void move() {
        timePoint--;
    }

    public void endLevel() {
        timePoint = timePoint/2;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(timePoint);
        dest.writeParcelable(object,flags); //on ecrit la liste d'item du joueur
    }

    public void getFromParcel(Parcel in) {
        timePoint = in.readInt();
        //on lit la liste d'items
        object = in.readParcelable(ItemList.class.getClassLoader());
    }
}
