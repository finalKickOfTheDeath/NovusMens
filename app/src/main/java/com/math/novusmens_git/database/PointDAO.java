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

public class PointDAO extends DAOBase {

    public PointDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(Point p) {
        //ajouter un point à la base
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.POINT_ID, p.getId());
        int resolu;
        if(p.isResolu()) {
            resolu = 1; //true
        }
        else {
            resolu = 0; //false
        }
        value.put(DatabaseHandler.POINT_RESOLU, resolu);

        //on insere une nouvelle entrée dans la base
        getDatabase().insert(DatabaseHandler.TABLE_NAME_POINT, null, value);
        Log.d("data", "insersion dans la table point");
    }

    public void updateResolu(Point p) {
        ContentValues value = new ContentValues();
        p.setResolu(true);
        int resolu = 1; //true
        value.put(DatabaseHandler.POINT_RESOLU, resolu);
        getDatabase().update(DatabaseHandler.TABLE_NAME_POINT, value, DatabaseHandler.POINT_ID  + " = ?", new String[] {String.valueOf(p.getId())});
    }

    public ArrayList<Point> selectionner() {
        //obtenir la liste de point
        Cursor cursor = getDatabase().rawQuery("SELECT " + DatabaseHandler.POINT_ID + " AS _id, "
                                                         + DatabaseHandler.POINT_RESOLU + " from "
                                                         + DatabaseHandler.TABLE_NAME_POINT, new String[]{});

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
        Log.d("data", "selection de la liste de points");
        return points;
    }

}
