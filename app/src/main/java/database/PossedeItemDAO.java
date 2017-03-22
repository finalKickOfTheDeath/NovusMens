package database;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;


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

}
