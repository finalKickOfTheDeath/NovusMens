package com.math.novusmens_git.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import com.math.novusmens_git.niveau.Point;


/**
 * Created by Math on 22/03/2017.
 */

public class PossedePointDAO extends DAOBase{
    //donne la liste des points resolus pour une sauvegarde

    public PossedePointDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(long id_sauv, long id_point) {
        //ajouter un couple sauv-point à la table possèdePoint
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.SAVE_ID, id_sauv);
        value.put(DatabaseHandler.POINT_ID, id_point);

        //on insere une nouvelle entrée dans la base
        getDatabase().insert(DatabaseHandler.TABLE_NAME_POSSEDEPOINT, null, value);
        Log.d("data", "insersion dans la table possedepoint");
    }

    public ArrayList<Point> selectionner(Sauvegarde s) {
        //obtenir la liste de point de la sauvegarde = les points resolu
        Cursor cursor = getDatabase().rawQuery("SELECT p1." + DatabaseHandler.POINT_ID + " AS _id, "
                                                            + "p1." + DatabaseHandler.POINT_RESOLU + " from "
                                                            + DatabaseHandler.TABLE_NAME_POINT + " p1, "
                                                            + DatabaseHandler.TABLE_NAME_POSSEDEPOINT + " p2 "
                                                            + " WHERE p2." + DatabaseHandler.SAVE_ID + "=?"
                                                            + " AND p1." + DatabaseHandler.POINT_ID + "=p2."
                                                            + DatabaseHandler.POINT_ID, new String[]{String.valueOf(s.getId())});

        ArrayList<Point> points = new ArrayList<Point>();

        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            int resolu = cursor.getInt(1);

            boolean estResolu;
            if(resolu == 1) {
                estResolu = true;
            }
            else {
                estResolu = false;
            }
            Point p = new Point(id, estResolu);
            points.add(p);
        }
        cursor.close();
        Log.d("data", "selection de la liste de points resolus");
        return points;
    }

}
