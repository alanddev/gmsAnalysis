package com.alanddev.gmscall.controller;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.alanddev.gmscall.helper.MwSQLiteHelper;
import com.alanddev.gmscall.model.Network;

// 19 fields
// miss fields ul, dl
public class NetworkController {
	private SQLiteDatabase database;
	private MwSQLiteHelper dbHelper;
	private String [] allColumns = {
			MwSQLiteHelper.COLUMN_DATA,
			MwSQLiteHelper.COLUMN_TIME,
			MwSQLiteHelper.COLUMN_LONGITUDE,
			MwSQLiteHelper.COLUMN_LATITUDE,
			MwSQLiteHelper.COLUMN_MCC,
			MwSQLiteHelper.COLUMN_MNC,
			MwSQLiteHelper.COLUMN_OPERATOR,
			MwSQLiteHelper.COLUMN_LAC,
			MwSQLiteHelper.COLUMN_CID,
			MwSQLiteHelper.COLUMN_CELLID,
			MwSQLiteHelper.COLUMN_RNC,
			MwSQLiteHelper.COLUMN_DBM,
			MwSQLiteHelper.COLUMN_ECIO,
			MwSQLiteHelper.COLUMN_SNR,
			MwSQLiteHelper.COLUMN_ALTITUDE,
			MwSQLiteHelper.COLUMN_GROUND,
			MwSQLiteHelper.COLUMN_HEIGHT,
			MwSQLiteHelper.COLUMN_NWACCURACY,
			MwSQLiteHelper.COLUMN_SPEED
	};
	
	public NetworkController(Context context) {
		dbHelper = new MwSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		dbHelper.close();
	}
	
	// createNetwork
	public Network createNetwork(String data,double latitude, double longitude, String time, String mcc, String mnc, String operator,
			int lac, int cid, int cellID, int rnc, double dBm, double ecIO, double snr, double altitude, double ground, double height, double nwAccuracy,double speed ) {
		ContentValues values = new ContentValues();
		values.put(MwSQLiteHelper.COLUMN_DATA, data);
		values.put(MwSQLiteHelper.COLUMN_LONGITUDE, longitude);
		values.put(MwSQLiteHelper.COLUMN_LATITUDE, latitude);
		values.put(MwSQLiteHelper.COLUMN_TIME, time);
		values.put(MwSQLiteHelper.COLUMN_MCC, mcc);
		values.put(MwSQLiteHelper.COLUMN_MNC, mnc);
		values.put(MwSQLiteHelper.COLUMN_OPERATOR, operator);
		values.put(MwSQLiteHelper.COLUMN_LAC, lac);
		values.put(MwSQLiteHelper.COLUMN_CID, cid);
		values.put(MwSQLiteHelper.COLUMN_CELLID, cellID);
		values.put(MwSQLiteHelper.COLUMN_RNC, rnc);
		values.put(MwSQLiteHelper.COLUMN_DBM, dBm);
		values.put(MwSQLiteHelper.COLUMN_ECIO, ecIO);
		values.put(MwSQLiteHelper.COLUMN_SNR, snr);
		values.put(MwSQLiteHelper.COLUMN_ALTITUDE, altitude);
		values.put(MwSQLiteHelper.COLUMN_GROUND, ground);
		values.put(MwSQLiteHelper.COLUMN_HEIGHT, height);
		values.put(MwSQLiteHelper.COLUMN_NWACCURACY, nwAccuracy);
		values.put(MwSQLiteHelper.COLUMN_SPEED, speed);
	    //long insertId;
	    String selectQuery = MwSQLiteHelper.COLUMN_DATA + " = '" + data + "' AND " +MwSQLiteHelper.COLUMN_LONGITUDE +" = " + longitude + " AND " +MwSQLiteHelper.COLUMN_LATITUDE +" = " + latitude;
	    Cursor cursor = database.query(MwSQLiteHelper.TABLE_NETWORK,
	        allColumns, selectQuery, null,
	        null, null, null);
	    if (!cursor.moveToFirst()){
	    	// Can assign insertId = ...
	    	database.insert(MwSQLiteHelper.TABLE_NETWORK, null,
		        values);
	    	cursor = database.query(MwSQLiteHelper.TABLE_NETWORK,
	    	        allColumns, selectQuery, null,
	    	        null, null, null);	    	    
	    }else {
	    	database.update(MwSQLiteHelper.TABLE_NETWORK, values, selectQuery, null);
	    }
	    
	    cursor.moveToFirst();
	    Network newNetwork = cursorToNetwork(cursor);
	    cursor.close();
	    return newNetwork;
	  }

	public Network createNetwork(Network net){
		Network newNetwork = createNetwork(net.getData(), net.getMyLatitude(), net.getMyLongitude(), net.getTime(), net.getMcc(), net.getMnc(), net.getOperator(),
			net.getLac(), net.getCid(), net.getCellID(), net.getRNC(), net.getdBm(), net.getEcIO(), net.getSNR(), net.getAltitude(), 
			net.getGround(), net.getHeight(), net.getNwAccuracy(),net.getSpeed() );
		return newNetwork;
	}
	
	public void deleteNetwork(Network network) {
		long id = network.getId();
		System.out.println("Network deleted with id: " + id);
		//	    database.delete(NwSQLiteHelper.TABLE_NETWORK, NwSQLiteHelper.COLUMN_ID
		//	        + " = " + id, null);
	}

	public List<Network> getAllNetworks() {
		List<Network> networks = new ArrayList<Network>();

		Cursor cursor = database.query(MwSQLiteHelper.TABLE_NETWORK,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Network network = cursorToNetwork(cursor);
			networks.add(network);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return networks;
	}

	private Network cursorToNetwork(Cursor cursor) {
		Network network = new Network();
		network.setData(cursor.getString(1));
		network.setMyLongitude(cursor.getDouble(2));
		network.setMyLatitude(cursor.getDouble(3));
		network.setTime(cursor.getString(4));
		network.setMcc(cursor.getString(5));
		network.setMnc(cursor.getString(6));
		network.setOperator(cursor.getString(7));
		network.setLac(cursor.getInt(8));
		network.setCid(cursor.getInt(9));	
		network.setCellID(cursor.getInt(10));
		network.setRNC(cursor.getInt(11));
		network.setdBm(cursor.getDouble(12));
		network.setEcIO(cursor.getDouble(13));
		network.setSNR(cursor.getDouble(14));
		network.setAltitude(cursor.getDouble(15));
		network.setGround(cursor.getDouble(16));
		network.setHeight(cursor.getDouble(17));
		network.setNwAccuracy(cursor.getDouble(18));
		network.setSpeed(cursor.getDouble(19));
		return network;

	}



	
}
