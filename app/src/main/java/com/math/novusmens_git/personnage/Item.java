package com.math.novusmens_git.personnage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Math on 21/03/2017.
 */

public class Item implements Parcelable {
    //item implemente parcelable = on peut le passer d'une activité à une autre à travers un bundle

    private long id;
    private String nom;

    public Item(){};

    public Item(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Item(String nom) {
        this.nom = nom;
    }

    public Item(Parcel in) {
        getFromParcel(in);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //ecrire un objet dans le parcel
        dest.writeLong(id);
        dest.writeString(nom);
    }

    public void getFromParcel(Parcel in) {
        //lire un objet dans le parcel
        id = in.readLong();
        nom = in.readString();
    }
}
