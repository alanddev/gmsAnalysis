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

    public static final String TABLE_NETWORK = "networks";
    //public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_MCC = "mcc";
    public static final String COLUMN_MNC = "mnc";
    public static final String COLUMN_OPERATOR = "operator";
    public static final String COLUMN_LAC = "lac";
    public static final String COLUMN_CID = "cid";
    public static final String COLUMN_CELLID = "cellID";
    public static final String COLUMN_RNC = "rnc";
    public static final String COLUMN_DBM = "dBm";
    public static final String COLUMN_ECIO = "ecIO";
    public static final String COLUMN_SNR = "snr";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_ALTITUDE = "altitude";
    public static final String COLUMN_GROUND = "ground";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_NWACCURACY = "nwAccuracy";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_SPEED = "speed";

    // 20 fields
    // Database creation sql statement
    private static final String TABLE_NETWORK_CREATE = "create table "
            + TABLE_NETWORK + "("
            + COLUMN_DATA + " text not null, "
            + COLUMN_LONGITUDE + " real not null, "
            + COLUMN_LATITUDE + " real not null, "
            + COLUMN_TIME + " text not null, "
            + COLUMN_MCC + " text not null, "
            + COLUMN_MNC + " text not null , "
            + COLUMN_OPERATOR + " text not null, "
            + COLUMN_LAC + " integer, "
            + COLUMN_CID + " integer, "
            + COLUMN_CELLID + " integer, "
            + COLUMN_RNC + " integer, "
            + COLUMN_DBM + " real, "
            + COLUMN_ECIO + " real, "
            + COLUMN_SNR + " real, "
            + COLUMN_ALTITUDE + " real, "
            + COLUMN_GROUND + " real, "
            + COLUMN_HEIGHT + " real, "
            + COLUMN_NWACCURACY + " real ,"
            + COLUMN_SPEED + " real , "
            + "PRIMARY KEY ( " + COLUMN_DATA + ", " + COLUMN_LONGITUDE + ", " + COLUMN_LATITUDE + ") "
            + ");";


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
        database.execSQL(TABLE_NETWORK_CREATE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            /*Log.w(MySQLiteHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");*/
        //sqLiteDatabase = db;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CALL_ENTRY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUR);
        onCreate(db);
    }

}

