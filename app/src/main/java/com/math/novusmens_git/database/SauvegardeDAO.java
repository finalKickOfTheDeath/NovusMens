package com.math.novusmens_git.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by Math on 21/03/2017.
 */

public class SauvegardeDAO extends DAOBase {

    public SauvegardeDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(Sauvegarde s) {
        //ajouter une sauvegarde à la base
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.SAVE_DATE, s.getDate());
        value.put(DatabaseHandler.SAVE_PT, s.getPointTemps());
        value.put(DatabaseHandler.SAVE_NIVEAU, s.getNumNiveau());

        //on insere une nouvelle entrée dans la base
        getDatabase().insert(DatabaseHandler.TABLE_NAME_SAVE, null, value);
        Log.d("data", "insersion d'une savegarde dans la base");
    }

    public void supprimer(long id) {
        //suprimer une entrée d'utilisation de la base
        //delete renvoie le nombre de lignes supprimées
        getDatabase().delete(DatabaseHandler.TABLE_NAME_SAVE, DatabaseHandler.SAVE_ID + " = ?", new String[] {String.valueOf(id)});
    }

    public void update(Sauvegarde s) {
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.SAVE_DATE, s.getDate());
        value.put(DatabaseHandler.SAVE_PT, s.getPointTemps());
        getDatabase().update(DatabaseHandler.TABLE_NAME_SAVE, value, DatabaseHandler.SAVE_ID  + " = ?", new String[] {String.valueOf(s.getId())});
    }

    public Sauvegarde selectionSave() {
        //obtenir la dernière sauvegarde
        Cursor cursor = getDatabase().rawQuery("SELECT " + DatabaseHandler.SAVE_ID + " AS _id, "
                                                         + DatabaseHandler.SAVE_DATE + ", "
                                                         + DatabaseHandler.SAVE_PT + ", "
                                                         + DatabaseHandler.SAVE_NIVEAU + " from "
                                                         + DatabaseHandler.TABLE_NAME_SAVE
                                                         + " ORDER BY " + DatabaseHandler.SAVE_ID
                                                         + " DESC LIMIT 1", new String[]{});

        Sauvegarde s = null;

        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String date = cursor.getString(1);
            int pointTemps = cursor.getInt(2);
            int numNiveau = cursor.getInt(3);
            s = new Sauvegarde(id, date, pointTemps, numNiveau);
        }
        cursor.close();
        Log.d("data", "on a selectionner la derniere sauvegarde");
        return s;
    }

}
