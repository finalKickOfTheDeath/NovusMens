package com.math.novusmens_git.personnage;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 06/03/2017.
 */

public class Joueur implements Parcelable {

    private int timePoint; // number of time points of the player
    private ItemList itemList; // List of player's itemList

    public Joueur(int timePoint){
        this.timePoint = timePoint;
        itemList = new ItemList();
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
        return itemList;
    }

    public void move() {
        timePoint--;
    }

    public void endLevel() {
        timePoint = timePoint/2;
    }

    public void win(Item i) {
        //if(!itemList.contains(i))
        if(!has(i))
            itemList.add(i);
    }

    public boolean has(Item item) {
        //tester si le joueur possede l'item avec le nom de l'item
        for(Item i : itemList) {
            if(i.getNom().equals(item.getNom())) {
                Log.d("enigme", "on a retirer un item au joueur");
                return true;
            }
        }
        return false;
    }

    public void dismiss(Item item) {
        //enlever l'item d'un joueur par son nom
        for(int i = 0; i < itemList.size(); i++) {
            if(itemList.get(i).getNom().equals(item.getNom())) {
                itemList.remove(i);
            }
        }
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

    public void gameOver() {
        timePoint = 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(timePoint);
        dest.writeParcelable(itemList,flags); //on ecrit la liste d'item du joueur
    }

    public void getFromParcel(Parcel in) {
        timePoint = in.readInt();
        //on lit la liste d'items
        itemList = in.readParcelable(ItemList.class.getClassLoader());
    }
}
