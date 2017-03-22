package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import personnage.Item;

/**
 * Created by Math on 22/03/2017.
 */

public class PossedeItemDAO extends DAOBase {

    public PossedeItemDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(long id_sauv, long id_objet) {
        //ajouter une sauvegarde à la base
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.SAVE_ID, id_sauv);
        value.put(DatabaseHandler.ITEM_ID, id_objet);

        //on insere une nouvelle entrée dans la base
        getDatabase().insert(DatabaseHandler.TABLE_NAME_POSSEDEITEM, null, value);
        Log.d("data", "insersion dans la table possedepoint");
    }

    public ArrayList<Item> selectionSave(Sauvegarde s) {
        //obtenir la liste d'item de la sauvegarde
        Cursor cursor = getDatabase().rawQuery("SELECT i1." + DatabaseHandler.ITEM_ID + " AS _id, "
                + "i1." + DatabaseHandler.ITEM_NOM + " from "
                + DatabaseHandler.TABLE_NAME_ITEM + " i1 , "
                + DatabaseHandler.TABLE_NAME_POSSEDEITEM + " i2 "
                + " WHERE i2." + DatabaseHandler.SAVE_ID + "=?", new String[]{String.valueOf(s.getId())});

        ArrayList<Item> items = new ArrayList<Item>();

        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String nom = cursor.getString(1);

            Item i = new Item(id, nom);
            items.add(i);
        }
        cursor.close();
        return items;
    }

}
