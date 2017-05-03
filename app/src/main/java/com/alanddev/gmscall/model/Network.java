package com.alanddev.gmscall.model;


public class Network extends Model {
	private double myLongitude;
	private double myLatitude;
	private String mcc;
	private String mnc;
	private String operator;
	private int lac;
	private int cid;
	private int cellID;
	private int RNC;
	private double dBm;
	private double ecIO;
	private double SNR;
	private String time; 
	private double altitude;
	private double ground;
	private double height;
	private double nwAccuracy;
	private String data;
	private String type;
	private double speed;

	private long id;
	
	
	public Network(String data, double longitude, double latitude,String time, String mcc, String mnc, String operator, 
			 int lac, int cid, int cellID, int RNC,double dBm, double ecIO, double SNR, double altitude, double ground,
			double height, double nwAccuracy,double speed){
		
		this.data = data;
		this.myLongitude = longitude;
		this.myLatitude = latitude;
		this.time = time;
		this.mcc = mcc;
		this.mnc = mnc;
		this.operator = operator;
		this.lac = lac;
		this.cid = cid;
		this.cellID = cellID;
		this.RNC = RNC;
		this.altitude = altitude;
		this.ground = ground;
		this.height = height;
		this.nwAccuracy = nwAccuracy;
		
		this.dBm = dBm;
		this.SNR = SNR;
		this.ecIO = ecIO;
		
	}
	
	public Network(String data, double longitude, double latitude, double dBm){
		this.data = data;
		this.myLatitude = latitude;
		this.myLongitude = longitude;
		this.dBm = dBm;
	}
	
	public Network(){
		height = 0.0;
		nwAccuracy = 0.0;
		altitude = 0.0;
		ground = 0.0;
		speed = 0.0;
		type = "HSPA";
	}
	
	
	public String info(){
		String info =  "dBm: " + dBm + 
				"\n" + " Lat: " + myLatitude + "  Longitude: "+ myLongitude +
				"\n" +  " EC/NO: " + ecIO + "  SNR:  " + SNR;
		return info;
	}
	public long getId(){
		return id;
	}
	
	public void setId(long ID){
		this.id = ID;
	}
	
	public double getMyLongitude() {
		return myLongitude;
	}
	public void setMyLongitude(double myLongitude) {
		this.myLongitude = myLongitude;
	}
	public double getMyLatitude() {
		return myLatitude;
	}
	public void setMyLatitude(double myLatitude) {
		this.myLatitude = myLatitude;
	}
	public String getMcc() {
		return mcc;
	}
	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	public String getMnc() {
		return mnc;
	}
	public void setMnc(String mnc) {
		this.mnc = mnc;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getLac() {
		return lac;
	}
	public void setLac(int lac) {
		this.lac = lac;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getCellID() {
		return cellID;
	}
	public void setCellID(int cellID) {
		this.cellID = cellID;
	}
	public int getRNC() {
		return RNC;
	}
	public void setRNC(int rNC) {
		RNC = rNC;
	}
	public double getdBm() {
		return dBm;
	}
	public void setdBm(double dBm) {
		this.dBm = dBm;
	}
	public double getEcIO() {
		return ecIO;
	}
	public void setEcIO(double ecIO) {
		this.ecIO = ecIO;
	}
	public double getSNR() {
		return SNR;
	}
	public void setSNR(double sNR) {
		SNR = sNR;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	public double getGround() {
		return ground;
	}
	public void setGround(double ground) {
		this.ground = ground;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getNwAccuracy() {
		return nwAccuracy;
	}
	public void setNwAccuracy(double nwAccuracy) {
		this.nwAccuracy = nwAccuracy;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSpeed(double speed) {
		// TODO Auto-generated method stub
		this.speed = speed;
	}

	public double getSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}
	

}
