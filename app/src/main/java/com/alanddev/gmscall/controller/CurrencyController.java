package com.alanddev.gmscall.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alanddev.gmscall.R;
import com.alanddev.gmscall.helper.IDataSource;
import com.alanddev.gmscall.helper.MwSQLiteHelper;
import com.alanddev.gmscall.model.Currency;
import com.alanddev.gmscall.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by td.long on 21/02/2017.
 */
public class CurrencyController implements IDataSource {

    private SQLiteDatabase database;
    private MwSQLiteHelper dbHelper;

    private String [] allColumns = {
            MwSQLiteHelper.COLUMN_CUR_ID,
            MwSQLiteHelper.COLUMN_CUR_CODE,
            MwSQLiteHelper.COLUMN_CUR_NAME,
            MwSQLiteHelper.COLUMN_CUR_SYMBOL,
            MwSQLiteHelper.COLUMN_CUR_DISPLAY,
    };

    public CurrencyController(Context context){
        dbHelper = new MwSQLiteHelper(context);
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
    public Currency create(Model data) {
        ContentValues values = new ContentValues();
        //values.put(MwSQLiteHelper.COLUMN_ID, id);
        Currency currency  = (Currency)data;
        //values.put(MwSQLiteHelper.COLUMN_CUR_ID, currency.getId());
        values.put(MwSQLiteHelper.COLUMN_CUR_CODE, currency.getCode());
        values.put(MwSQLiteHelper.COLUMN_CUR_NAME, currency.getName());
        values.put(MwSQLiteHelper.COLUMN_CUR_SYMBOL, currency.getSymbol());
        values.put(MwSQLiteHelper.COLUMN_CUR_DISPLAY, currency.getDisplay());

        //String selectQuery = MwSQLiteHelper.COLUMN_CUR_CODE + " = \"" + currency.getCode() + "\"";
        database.insert(MwSQLiteHelper.TABLE_CUR, null,
                values);
        //Currency newCurrency = (Currency)get(selectQuery);
        //dbHelper.close();
        return currency;
    }

    @Override
    public void update(Model data) {
        Currency currency  = (Currency)data;
        ContentValues values = new ContentValues();
        values.put(MwSQLiteHelper.COLUMN_CUR_CODE, currency.getCode());
        values.put(MwSQLiteHelper.COLUMN_CUR_NAME, currency.getName());
        values.put(MwSQLiteHelper.COLUMN_CUR_SYMBOL, currency.getSymbol());
        values.put(MwSQLiteHelper.COLUMN_CUR_DISPLAY, currency.getDisplay());
        // updating row
        database.update(MwSQLiteHelper.TABLE_CUR, values, MwSQLiteHelper.COLUMN_CUR_CODE + " = ?",
                new String[]{String.valueOf(currency.getCode())});
    }

    @Override
    public int getCount() {
        String countQuery = "SELECT  * FROM " + MwSQLiteHelper.TABLE_CUR;
        Cursor cursor = database.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    @Override
    public List<Model> getAll() {
        List<Model> currencies = new ArrayList<Model>();
        Cursor cursor = database.query(MwSQLiteHelper.TABLE_CUR,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Currency currency = (Currency)cursorTo(cursor);
            currencies.add(currency);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return currencies;
    }

    @Override
    public Model get(String query) {
        Cursor cursor = database.query(MwSQLiteHelper.TABLE_CUR,
                allColumns, query, null,
                null, null, null);
        cursor.moveToFirst();
        Currency currency = (Currency)cursorTo(cursor);
        cursor.close();
        return currency;
    }

    @Override
    public List<Model> getAll(String query) {
        return null;
    }

    public void getChecked(){

    }


    @Override
    public Model cursorTo(Cursor cursor) {
        Currency currency = new Currency();
        currency.setId(cursor.getInt(0));
        currency.setCode(cursor.getString(1));
        currency.setName(cursor.getString(2));
        currency.setSymbol(cursor.getString(3));
        currency.setDisplay(cursor.getInt(4));
        return currency;
    }

    public void init(Context context){
        //Currency currency_vnd  = new Currency("VND","Viet Nam Dong","đ",1);
        //Currency currency_usd  = new Currency("USD","Dollar","$",0);
        String code[] =   context.getResources().getStringArray(R.array.code_currency_array);
        String name[] =   context.getResources().getStringArray(R.array.name_currency_array);
        String symbol[] =   context.getResources().getStringArray(R.array.symbol_currency_array);
        for(int i = 0; i < code.length; i++){
            Currency cur = new Currency(code[i],name[i],symbol[i],0);
            create(cur);
        }
        //create(currency_vnd);
        //create(currency_usd);
    }
}
