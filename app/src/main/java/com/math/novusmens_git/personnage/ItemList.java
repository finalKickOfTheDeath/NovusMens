package com.math.novusmens_git.personnage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Math on 24/03/2017.
 */

public class ItemList extends ArrayList<Item> implements Parcelable {


    public ItemList() {

    }

    public ItemList(Parcel in) {
        getFromParcel(in);
    }

    public static final Creator<ItemList> CREATOR = new Creator<ItemList>() {
        @Override
        public ItemList createFromParcel(Parcel in) {
            return new ItemList(in);
        }

        @Override
        public ItemList[] newArray(int size) {
            return new ItemList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //taille de la liste
        int size = this.size();
        dest.writeInt(size);
        for(int i=0; i < size; i++) {
            Item item = this.get(i); //on lit chaque objet item
            dest.writeLong(item.getId());
            dest.writeString(item.getNom());
        }
    }

    private void getFromParcel(Parcel in) {
        //on vide la liste avant tout remplissage
        this.clear();
        //récupération du nombre d'objet
        int size = in.readInt();
        //on repeuple la liste avec de nouveaux objets
        for(int i = 0; i < size; i++) {
            Item item  = new Item();
            item.setId(in.readLong());
            item.setNom(in.readString());
            this.add(item);
        }
    }

}
