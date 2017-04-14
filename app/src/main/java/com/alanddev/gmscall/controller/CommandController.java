package com.alanddev.gmscall.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alanddev.gmscall.helper.MwSQLiteHelper;
import com.alanddev.gmscall.model.Command;
import com.alanddev.gmscall.model.Model;

import java.util.ArrayList;
import java.util.List;

// 19 fields
// miss fields ul, dl
public class CommandController {
	private SQLiteDatabase database;
	private MwSQLiteHelper dbHelper;
	private String [] allColumns = {
            MwSQLiteHelper.COLUMN_COMMAND_ID,
            MwSQLiteHelper.COLUMN_COMMAND_SERVER,
            MwSQLiteHelper.COLUMN_COMMAND_CMD,
			MwSQLiteHelper.COLUMN_COMMAND_TIME,
			MwSQLiteHelper.COLUMN_COMMAND_STREAM,
			MwSQLiteHelper.COLUMN_COMMAND_NUMBER_RUN,
			MwSQLiteHelper.COLUMN_COMMAND_TIME_NEXT,
			MwSQLiteHelper.COLUMN_COMMAND_USER,
			MwSQLiteHelper.COLUMN_COMMAND_PASS,
			MwSQLiteHelper.COLUMN_COMMAND_STATUS
	};

	public CommandController(Context context) {
		dbHelper = new MwSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close(){
		dbHelper.close();
	}

	// create Command
	//ID, Server, Cmd, time, stream, numberrun,timeNext,user,pass, status
	public Command createCommand(Model data ) {
		ContentValues values = new ContentValues();
		Command command  = (Command)data;
		values.put(MwSQLiteHelper.COLUMN_COMMAND_ID, command.getId());
		values.put(MwSQLiteHelper.COLUMN_COMMAND_SERVER, command.getServer());
		values.put(MwSQLiteHelper.COLUMN_COMMAND_CMD, command.getCmd());
		values.put(MwSQLiteHelper.COLUMN_COMMAND_TIME, command.getTimeTest());
		values.put(MwSQLiteHelper.COLUMN_COMMAND_STREAM, command.getStream());
		values.put(MwSQLiteHelper.COLUMN_COMMAND_NUMBER_RUN, command.getNumber());
		values.put(MwSQLiteHelper.COLUMN_COMMAND_TIME_NEXT, command.getTimeToNext());
		values.put(MwSQLiteHelper.COLUMN_COMMAND_USER, command.getUser());
		values.put(MwSQLiteHelper.COLUMN_COMMAND_PASS, command.getPass());
		values.put(MwSQLiteHelper.COLUMN_COMMAND_STATUS, command.getStatus());
	    //long insertId;
		database.insert(MwSQLiteHelper.TABLE_COMMAND, null,
		        values);
	    return command;
	  }

	
	public void deleteCommand(Command command) {
		long id = command.getId();
		System.out.println("Command deleted with id: " + id);
		database.delete(MwSQLiteHelper.TABLE_COMMAND, MwSQLiteHelper.COLUMN_COMMAND_ID
			        + " = " + id, null);
	}



    public int getCount() {
        String countQuery = "SELECT  * FROM " + MwSQLiteHelper.TABLE_CUR;
        Cursor cursor = database.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

	public List<Command> getAllCommands() {
		List<Command> commands = new ArrayList<Command>();

		Cursor cursor = database.query(MwSQLiteHelper.TABLE_COMMAND,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Command command = cursorToCommand(cursor);
			commands.add(command);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return commands;
	}

	private Command cursorToCommand(Cursor cursor) {
		Command command = new Command();
		command.setId(cursor.getInt(1));
		command.setServer(cursor.getString(2));
		command.setCmd(cursor.getString(3));
		command.setTimeTest(cursor.getString(4));
		command.setStream(cursor.getString(5));
		command.setNumber(cursor.getString(6));
		command.setTimeToNext(cursor.getString(7));
		command.setUser(cursor.getString(8));
		command.setPass(cursor.getString(9));
		command.setStatus(cursor.getString(10));
		return command;

	}



	
}
