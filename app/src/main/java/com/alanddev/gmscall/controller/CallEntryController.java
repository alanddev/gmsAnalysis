package com.alanddev.gmscall.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alanddev.gmscall.helper.IDataSource;
import com.alanddev.gmscall.helper.MwSQLiteHelper;
import com.alanddev.gmscall.model.CallEntry;
import com.alanddev.gmscall.model.Model;
import com.alanddev.gmscall.util.Constant;
import com.alanddev.gmscall.util.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LongTD on 21/02/2017.
 */
public class CallEntryController implements IDataSource {
    private SQLiteDatabase database;
    private MwSQLiteHelper dbHelper;
    private Context mContext;

    private String [] allColumns = {
            MwSQLiteHelper.COLUMN_CALL_ENTRY_ID,
            MwSQLiteHelper.COLUMN_CALL_ENTRY_CALLEE,
            MwSQLiteHelper.COLUMN_CALL_ENTRY_CALLER,
            MwSQLiteHelper.COLUMN_CALL_ENTRY_STARTIME,
            MwSQLiteHelper.COLUMN_CALL_ENTRY_ENDTIME,
            MwSQLiteHelper.COLUMN_CALL_ENTRY_LONGITUDE,
            MwSQLiteHelper.COLUMN_CALL_ENTRY_LATITUDE
    };

    public CallEntryController(Context context){
        dbHelper = new MwSQLiteHelper(context);
        this.mContext = context;
    }

    @Override
    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        dbHelper.close();
    }

    @Override
    public Model create(Model data) {
        ContentValues values = new ContentValues();
        CallEntry callEntry  = (CallEntry)data;
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_CALLEE, callEntry.getCalleeNumber());
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_CALLER, callEntry.getCallerNumber());
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_STARTIME, callEntry.getStartTime());
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_ENDTIME, callEntry.getEndTime());
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_LONGITUDE, callEntry.getLongitude());
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_LATITUDE, callEntry.getLatitude());
        database.insert(MwSQLiteHelper.TABLE_CALL_ENTRY, null,
                values);
        return callEntry;
    }

    @Override
    public void update(Model data) {
        ContentValues values = new ContentValues();
        CallEntry callEntry  = (CallEntry)data;
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_CALLEE, callEntry.getCalleeNumber());
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_CALLER, callEntry.getCallerNumber());
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_STARTIME, callEntry.getStartTime());
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_ENDTIME, callEntry.getEndTime());
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_LONGITUDE, callEntry.getLongitude());
        values.put(MwSQLiteHelper.COLUMN_CALL_ENTRY_LATITUDE, callEntry.getLatitude());
        database.update(MwSQLiteHelper.TABLE_CALL_ENTRY, values, MwSQLiteHelper.COLUMN_CALL_ENTRY_ID + " = ?", new String[]{callEntry.getId() + ""});
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
     public List<Model> getAll() {
        return null;
    }

    @Override
    public Model get(String query) {
       return null;
    }

    @Override
    public List<Model> getAll(String query) {
        return null;
    }

    public Boolean delete(long callEntryId){
        return database.delete(MwSQLiteHelper.TABLE_CALL_ENTRY, MwSQLiteHelper.COLUMN_CALL_ENTRY_ID + "=" + callEntryId, null) > 0;
    }

    public CallEntry getCallEntryById(long id){
//        StringBuffer sql = new StringBuffer("SELECT * FROM ").append(MwSQLiteHelper.TABLE_ENTRY).append(" s inner join ")
//                .append(MwSQLiteHelper.TABLE_CATEGORY).append(" c ON s.").append(MwSQLiteHelper.COLUMN_ENTRY_CATE_ID).append(" = c.")
//                .append(MwSQLiteHelper.COLUMN_CATE_ID)
//                .append(" WHERE s.").append(MwSQLiteHelper.COLUMN_ENTRY_WALLET_ID).append(" = ").append(Utils.getWallet_id())
//                .append(" AND s.").append(MwSQLiteHelper.COLUMN_ENTRY_ID).append(" = ?");
        StringBuffer sql = new StringBuffer("SELECT * FROM ").append(MwSQLiteHelper.COLUMN_CALL_ENTRY_ID).append(" = ?");
        String[] atts = new String[]{id+""};
        Cursor cursor = database.rawQuery(sql.toString(), atts);
        cursor.moveToFirst();
        CallEntry callEntry = (CallEntry) cursorTo(cursor);
        cursor.close();
        return callEntry;
    }

    public List<CallEntry> getAll(int type){
        List<CallEntry> callEntries = new ArrayList<CallEntry>();
        StringBuffer sql;
        sql = new StringBuffer("SELECT * FROM ").append(MwSQLiteHelper.TABLE_CALL_ENTRY);

        String strDate = Utils.changeDate2Str(new Date(), Constant.DATE_FORMAT_DB);
        String[] atts = new String[]{strDate};
        Cursor cursor = database.rawQuery(sql.toString(), atts);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CallEntry callEntry = (CallEntry) cursorTo(cursor);
            callEntries.add(callEntry);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return callEntries;
    }

    @Override
    public Model cursorTo(Cursor cursor) {
        CallEntry callEntry = new CallEntry();
        callEntry.setId(cursor.getInt(0));
        callEntry.setCalleeNumber(cursor.getString(1));
        callEntry.setCallerNumber(cursor.getString(2));
        callEntry.setStartTime(cursor.getString(3));
        callEntry.setEndTime(cursor.getString(4));
        callEntry.setLongitude(cursor.getFloat(5));
        callEntry.setLatitude(cursor.getFloat(6));
        return callEntry;
    }


}
