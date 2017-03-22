package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Math on 21/03/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //champs de la table SAVE
    public static final String SAVE_ID = "id_save";
    public static final String SAVE_DATE = "date";
    public static final String SAVE_PT = "pointTemps";
    public static final String SAVE_NIVEAU = "numNiveau";

    //champs de la table POINT
    public static final String POINT_ID = "id_point";
    public static final String POINT_RESOLU = "resolu"; //0 = false, 1 = true (stored as integer)

    //champs de la table ITEM
    public static final String ITEM_ID = "id_item";
    public static final String ITEM_NOM = "nom";

    //nom des tables
    public static final String TABLE_NAME_SAVE = "save";
    public static final String TABLE_NAME_POINT = "point";
    public static final String TABLE_NAME_ITEM = "item";
    public static final String TABLE_NAME_POSSEDEPOINT = "possedePoint";
    public static final String TABLE_NAME_POSSEDEITEM = "possedeItem";

    //create table
    private static final String CREATE_TABLE_SAVE =
        "CREATE TABLE " + TABLE_NAME_SAVE
        + " ("
        + SAVE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + SAVE_DATE + " TEXT, "
        + SAVE_PT + " INTEGER, "
        + SAVE_NIVEAU + " INTEGER "
        + ");";

    private static final String CREATE_TABLE_POINT =
        "CREATE TABLE " + TABLE_NAME_POINT
        + " ("
        + POINT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + POINT_RESOLU + " INTEGER "
        + ");";

    private static final String CREATE_TABLE_ITEM =
        "CREATE TABLE " + TABLE_NAME_ITEM
        + " ("
        + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + ITEM_NOM + " TEXT "
        + ");";

    private static final String CREATE_TABLE_POSSEDEPOINT =
        "CREATE TABLE " + TABLE_NAME_POSSEDEPOINT
        + " ("
        + SAVE_ID + " INTEGER PRIMARY KEY, "
        + POINT_ID + " INTEGER PRIMARY KEY, "
        + "FOREIGN KEY(" + SAVE_ID + ") REFERENCES " + TABLE_NAME_SAVE + "(" + SAVE_ID + ") ON DELETE CASCADE"
        + "FOREIGN KEY(" + POINT_ID + ") REFERENCES " + TABLE_NAME_POINT + "(" + POINT_ID + ") ON DELETE CASCADE"
        + ");";

    private static final String CREATE_TABLE_POSSEDEITEM =
        "CREATE TABLE " + TABLE_NAME_POSSEDEITEM
        + " ("
        + SAVE_ID + " INTEGER PRIMARY KEY, "
        + ITEM_ID + " INTEGER PRIMARY KEY, "
        + "FOREIGN KEY(" + SAVE_ID + ") REFERENCES " + TABLE_NAME_SAVE + "(" + SAVE_ID + ") ON DELETE CASCADE"
        + "FOREIGN KEY(" + ITEM_ID + ") REFERENCES " + TABLE_NAME_ITEM + "(" + ITEM_ID + ") ON DELETE CASCADE"
        + ");";

    //drop table
    private static final String DROP_TABLE_SAVE = "DROP TABLE IF EXISTS " + TABLE_NAME_SAVE + ";";
    private static final String DROP_TABLE_POINT = "DROP TABLE IF EXISTS " + TABLE_NAME_POINT + ";";
    private static final String DROP_TABLE_ITEM = "DROP TABLE IF EXISTS " + TABLE_NAME_ITEM + ";";
    private static final String DROP_TABLE_POSSEDEPOINT = "DROP TABLE IF EXISTS " + TABLE_NAME_POSSEDEPOINT + ";";
    private static final String DROP_TABLE_POSSEDEITEM = "DROP TABLE IF EXISTS " + TABLE_NAME_POSSEDEITEM + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SAVE);
        db.execSQL(CREATE_TABLE_POINT);
        db.execSQL(CREATE_TABLE_ITEM);
        db.execSQL(CREATE_TABLE_POSSEDEPOINT);
        db.execSQL(CREATE_TABLE_POSSEDEITEM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_SAVE);
        db.execSQL(DROP_TABLE_POINT);
        db.execSQL(DROP_TABLE_ITEM);
        db.execSQL(DROP_TABLE_POSSEDEPOINT);
        db.execSQL(DROP_TABLE_POSSEDEITEM);
        onCreate(db);
    }
}
