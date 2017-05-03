package com.alanddev.gmscall.listener;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public abstract class NwPhoneStateListener extends PhoneStateListener {
	Context mContext;
	private int dBmReturn;
	private int ecReturn;
	private int snrReturn;
	private int lteRsrp; //reference LTE signal receiver power
	private int lteRsrq; // reference LTE signal receiver Quality
	private int lteRssnr; // reference LTE RSSNR
	private int lteCqi; // channel quality indication
	private int lteSignalStrength;
	private int gsmArfcn;
    private int gsmRssi;

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
            gsmArfcn = 0;
            gsmRssi = 0;
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

		try{
			Method[] methods = android.telephony.SignalStrength.class.getMethods();
			for (Method mthd:methods){
				if (mthd.getName().equals("getLteSignalStreng")) {
					lteSignalStrength = (int)mthd.invoke(signalStrength);
				}else if(mthd.getName().equals("getLteRsrp")) {
					lteRsrp = (int)mthd.invoke(signalStrength);
				}else if(mthd.getName().equals("getLteRsrq")) {
					lteRsrq = (int)mthd.invoke(signalStrength);
				}else if (mthd.getName().equals("getLteRssnr")) {
					lteRssnr = (int)mthd.invoke(signalStrength);
				}else if (mthd.getName().equals("getLteCqi")){
					lteCqi = (int)mthd.invoke(signalStrength);
				}
			}
		} catch(SecurityException e){
			e.printStackTrace();
		} catch(IllegalAccessException e){
			e.printStackTrace();
		} catch(IllegalArgumentException e){
			e.printStackTrace();
		} catch (InvocationTargetException e){
			e.printStackTrace();
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


	public int getLteCqi() {
		return lteCqi;
	}

	public int getLteRssnr() {
		return lteRssnr;
	}

	public int getLteRsrq() {
		return lteRsrq;
	}

	public int getLteRsrp() {
		return lteRsrp;
	}

	public int getLteSignalStrength() {
		return lteSignalStrength;
	}

    public int getGsmRssi (){return gsmRssi;}

    public int getGsmArfcn() {return gsmArfcn;}
}
