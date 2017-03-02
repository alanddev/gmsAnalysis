package com.alanddev.gmscall.util;

import java.util.ArrayList;

import android.app.Activity;

import com.alanddev.gmscall.controller.NetworkController;
import com.alanddev.gmscall.model.Network;


public  class NwConst {
	public static NwConst m_Context;
	public static ArrayList<Network> sharedListNetwork;
	public static NetworkController sharedDataSource;
	
	public static NwConst getInstance(){
		if (m_Context == null){
			m_Context = new NwConst();
		}
		if (sharedListNetwork == null){
			sharedListNetwork = new ArrayList<Network>();
		}
		
		return m_Context;
	}
	
	
	public static NwConst getInstance(Activity activity){
		if (m_Context == null){
			m_Context = new NwConst();
		}
		if (sharedListNetwork == null){
			sharedListNetwork = new ArrayList<Network>();
		}
		if (sharedDataSource == null){
			sharedDataSource = new NetworkController(activity);
			sharedDataSource.open();
		}
		return m_Context;
	}	

	
//	public static void openDatabase(Activity activity){
//		sharedDataSource = new NwDataSource(activity);
//		sharedDataSource.open();
//	}
	
	
}
