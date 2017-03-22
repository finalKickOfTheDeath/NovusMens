package database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import niveau.Point;


/**
 * Created by Math on 22/03/2017.
 */

public class PointDAO extends DAOBase {

    public PointDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(Point p) {
        //ajouter une sauvegarde à la base
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.SAVE_ID, p.getId());
        int resolu;
        if(p.isResolu()) {
            resolu = 1; //true
        }
        else {
            resolu = 0; //false
        }
        value.put(DatabaseHandler.ITEM_ID, resolu);

        //on insere une nouvelle entrée dans la base
        getDatabase().insert(DatabaseHandler.TABLE_NAME_POINT, null, value);
        Log.d("data", "insersion dans la table point");
    }

}
