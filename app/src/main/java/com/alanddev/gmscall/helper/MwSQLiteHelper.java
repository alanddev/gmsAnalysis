package com.alanddev.gmscall.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MwSQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_CALL_ENTRY = "call_entries";
    public static final String TABLE_CUR = "currency";


    // table Call Entry
    public static final String COLUMN_CALL_ENTRY_ID = "id";
    public static final String COLUMN_CALL_ENTRY_CALLEE = "callee";
    public static final String COLUMN_CALL_ENTRY_CALLER = "caller";
    public static final String COLUMN_CALL_ENTRY_LONGITUDE = "longitude";
    public static final String COLUMN_CALL_ENTRY_LATITUDE = "latitude";
    public static final String COLUMN_CALL_ENTRY_STARTIME = "start_time";
    public static final String COLUMN_CALL_ENTRY_ENDTIME = "end_time";



    // table Currency
    public static final String COLUMN_CUR_ID = "id";
    public static final String COLUMN_CUR_CODE = "code";
    public static final String COLUMN_CUR_NAME = "name";
    public static final String COLUMN_CUR_SYMBOL = "symbol";
    public static final String COLUMN_CUR_DISPLAY = "display";

    public static final String DATABASE_NAME = "gmscall.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteDatabase sqLiteDatabase;
    // 20 fields
    // Database creation sql statement
    private static final String CALL_ENTRY_CREATE = "CREATE TABLE "
            + TABLE_CALL_ENTRY + "("
            + COLUMN_CALL_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CALL_ENTRY_CALLEE + " text not null, "
            + COLUMN_CALL_ENTRY_CALLER + " text not null, "
            + COLUMN_CALL_ENTRY_STARTIME + " DATETIME not null, "
            + COLUMN_CALL_ENTRY_ENDTIME + " DATETIME not null, "
            + COLUMN_CALL_ENTRY_LONGITUDE + " float, "
            + COLUMN_CALL_ENTRY_LATITUDE + " float "
            + ");";


    private static final String CUR_CREATE = "CREATE TABLE "
            + TABLE_CUR + "("
            + COLUMN_CUR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CUR_CODE + " text not null, "
            + COLUMN_CUR_NAME + " text not null, "
            + COLUMN_CUR_SYMBOL + " text not null, "
            + COLUMN_CUR_DISPLAY + " int not null "
            + ");";


    public MwSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        sqLiteDatabase = database;
        database.execSQL(CALL_ENTRY_CREATE);
        database.execSQL(CUR_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*Log.w(MySQLiteHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");*/
        //sqLiteDatabase = db;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALL_ENTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUR);
        onCreate(db);
    }

}

