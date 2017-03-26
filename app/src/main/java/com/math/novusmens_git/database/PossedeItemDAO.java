package com.math.novusmens_git.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.math.novusmens_git.personnage.Item;

import java.util.ArrayList;


/**
 * Created by Math on 22/03/2017.
 */

public class PossedeItemDAO extends DAOBase {
    //donne la liste des items possedes pour une sauvegarde

    public PossedeItemDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(long id_sauv, long id_item) {
        //ajouter un couple sauv-item à la table possèdeItem
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.SAVE_ID, id_sauv);
        value.put(DatabaseHandler.ITEM_ID, id_item);

        //on insere une nouvelle entrée dans la base
        getDatabase().insert(DatabaseHandler.TABLE_NAME_POSSEDEITEM, null, value);
        Log.d("data", "insersion dans la table possedeitem");
    }

    public ArrayList<Item> selectionner(Sauvegarde s) {
        //obtenir la liste d'item de la sauvegarde = les items possédés par le joueur
        Cursor cursor = getDatabase().rawQuery("SELECT i1." + DatabaseHandler.ITEM_ID + " AS _id, "
                                                            + "i1." + DatabaseHandler.ITEM_NOM + " from "
                                                            + DatabaseHandler.TABLE_NAME_ITEM + " i1, "
                                                            + DatabaseHandler.TABLE_NAME_POSSEDEITEM + " i2 "
                                                            + " WHERE i2." + DatabaseHandler.SAVE_ID + "=?"
                                                            + " AND i1." + DatabaseHandler.ITEM_ID + "=i2."
                                                            + DatabaseHandler.ITEM_ID, new String[]{String.valueOf(s.getId())});

        ArrayList<Item> items = new ArrayList<Item>();

        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String nom = cursor.getString(1);
            Item i = new Item(id, nom);
            items.add(i);
        }
        cursor.close();
        Log.d("data", "selection de la liste d'item de la sauvegarde");
        return items;
    }

    public void supprimer(long id) {
        //supprimer l'entrée ou id = l'id de l'item
        getDatabase().delete(DatabaseHandler.TABLE_NAME_POSSEDEITEM, DatabaseHandler.ITEM_ID + " = ?", new String[] {String.valueOf(id)});
    }

}
