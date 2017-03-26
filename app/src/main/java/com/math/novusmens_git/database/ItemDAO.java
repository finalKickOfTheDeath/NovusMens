package com.math.novusmens_git.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import com.math.novusmens_git.personnage.Item;


/**
 * Created by Math on 22/03/2017.
 */

public class ItemDAO extends DAOBase {

    public ItemDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(Item i) {
        //ajouter un item à la base
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.ITEM_NOM, i.getNom());

        //on insere une nouvelle entrée dans la base
        getDatabase().insert(DatabaseHandler.TABLE_NAME_ITEM, null, value);
        Log.d("data", "insersion dans la table item");
    }

    public ArrayList<Item> selectionner() {
        //obtenir la liste d'item
        Cursor cursor = getDatabase().rawQuery("SELECT " + DatabaseHandler.ITEM_ID + " AS _id, "
                                                         + DatabaseHandler.ITEM_NOM + " from "
                                                         + DatabaseHandler.TABLE_NAME_ITEM, new String[]{});

        ArrayList<Item> items = new ArrayList<Item>();

        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String nom = cursor.getString(1);

            Item i = new Item(id, nom);
            items.add(i);
        }
        cursor.close();
        Log.d("data", "selection de la liste d'items");
        return items;
    }

    public Item getByName(String n) {
        //obtenir l'item pour le nom donné
        Cursor cursor = getDatabase().rawQuery("SELECT " + DatabaseHandler.ITEM_ID + " AS _id, "
                                                         + DatabaseHandler.ITEM_NOM + " from "
                                                         + DatabaseHandler.TABLE_NAME_ITEM
                                                         + " WHERE " + DatabaseHandler.ITEM_NOM + "=?", new String[]{n});
        Item item = null;
        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String nom = cursor.getString(1);

            item = new Item(id, nom);
        }
        cursor.close();
        Log.d("data", "selection de la liste d'items");
        return item;
    }

}
