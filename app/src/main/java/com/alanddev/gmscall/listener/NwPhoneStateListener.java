package com.alanddev.gmscall.listener;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;



public abstract class NwPhoneStateListener extends PhoneStateListener {
	Context mContext;
	private int dBmReturn;
	private int ecReturn;
	private int snrReturn;
	public static String LOG_TAG = "CustomPhoneStateListener";

	public NwPhoneStateListener(Context context) {
		mContext = context;
	}

	
	// GSM (do = ASU ; 2*ASU - 113 = dBm
	/**
	 * In this method Java Reflection API is being used please see link before
	 * using.
	 * 
	 * @see <a
	 *      href="http://docs.oracle.com/javase/tutorial/reflect/">http://docs.oracle.com/javase/tutorial/reflect/</a>
	 * 
	 */
	@Override
	public void onSignalStrengthsChanged(SignalStrength signalStrength) {
		super.onSignalStrengthsChanged(signalStrength);
		// Reflection code ends here
		if (signalStrength.isGsm()){
			dBmReturn = 2 * signalStrength.getGsmSignalStrength() - 113;
			ecReturn = 0;
			snrReturn = 0;
		}
		else if (signalStrength.getCdmaDbm() > 0){
			dBmReturn = signalStrength.getCdmaDbm();
			ecReturn = signalStrength.getCdmaEcio();
			snrReturn = 0;
			
		} else {
			dBmReturn = signalStrength.getEvdoDbm();
			ecReturn = signalStrength.getEvdoEcio();
			snrReturn = signalStrength.getEvdoSnr();
		}
		
		
		
		updateActivity();
	}

	protected abstract void updateActivity();
	
	
	public double getdBM(){
		return dBmReturn;
	}
	
	public double getEcIo(){
		return ecReturn;
	}	
	
	public double getSNR(){
		return snrReturn;
	}
	
}
