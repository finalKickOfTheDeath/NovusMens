package database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import personnage.Item;


/**
 * Created by Math on 22/03/2017.
 */

public class ItemDAO extends DAOBase {

    public ItemDAO(Context pContext) {
        super(pContext);
    }

    public void ajouter(Item i) {
        //ajouter une sauvegarde à la base
        ContentValues value = new ContentValues();
        value.put(DatabaseHandler.SAVE_ID, i.getId());
        value.put(DatabaseHandler.ITEM_ID, i.getNom());

        //on insere une nouvelle entrée dans la base
        getDatabase().insert(DatabaseHandler.TABLE_NAME_ITEM, null, value);
        Log.d("data", "insersion dans la table item");
    }

}
