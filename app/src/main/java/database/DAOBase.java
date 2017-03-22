package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Math on 21/03/2017.
 */

public abstract class DAOBase {

    private final static int VERSION = 1;
    private final static String NOM_BASE = "databaseNovusMens.db";

    private SQLiteDatabase database = null;
    private DatabaseHandler dbHandler = null;

    public DAOBase(Context pContext) {
        this.dbHandler = new DatabaseHandler(pContext, NOM_BASE, null, VERSION);
    }

    public SQLiteDatabase open() {
        database = dbHandler.getWritableDatabase();
        return database;
    }

    public void close() {
        database.close();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

}
