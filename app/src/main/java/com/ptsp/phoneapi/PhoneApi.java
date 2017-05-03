package com.ptsp.phoneapi;

/**
 * Created by TLinh on 4/27/2017.
 */

@SuppressWarnings("ALL")
public class PhoneApi {
    public native void  voidInitialize_CMD(); // Load  1st (first) Hook System and print logcat
    public native void  voidP2A4G(String appid); // Load  2nd to Open SService and Back to this Activity
    public native void  voidGet_RSRP(); // Get RSRP in 4G
    public native void  voidGet_RSRQ(); // Get RSRQ in 4G
    public native void  voidGet_CSQ(); // Get extra 3G, 4G parameter (option) . Result in logcat = CSQ:<rscp>, <ecio>,<sir>,<pathloss>,<rssi>
    public native void  voidRAT_Lock(String appid); // Lock Network
    static {
        try {
            System.loadLibrary("phone-lib");
        }
        catch (Exception e) {

        }
    }
}
/* Document API
Ket qua se duoc dua ra logcat voi cac TAG : nw_radio_info va PTSP
1. TAG neighbours cell : Cung cap thong tin cho mang 2G (Current Cell va neighbours cell) &  4G (Current Cell) . Chu y :  mang 3G tag nv_radio_indo tra ket qua sai, luon =0 -> Khong Su dung
Vd : nw_radio_info: radio info = rat=3, mcc=452, mnc=1, lac=10081, ci=22302, arfcn=96, band=44, rssi=65, sinr=0, rrstatus=0,txpower=1 [3 621 67],[3 85 66],[3 99 68],  [0]
Trong do :
A. lac=10081, ci=22302, arfcn=96, band=44, rssi=65 ( Thong tin CELL hien tai)
B. [3 621 67],[3 85 66],[3 99 68] : 621  : ARFCN , 67 : RSSI -> neighbours cell
2. TAG PTSP :
Khi goi lenh Get_CSQ, GET_RSRP,GET_RSRQ, api se in ra logcat cac ket qua tuong ung.
Cau truc :
- CSQ : ( neu API goc cua Android lay duoc RSCP thi khong can dung API nay)
CSQ :-63,-2,0,0,-65
<rscp>, <ecio>,<sir>,<pathloss>,<rssi>
- RSRQ ( Note :chi co trong 4G. Trong Mode 3G / 2G tra ra ket qua trang )
 RSRQ: 257,1750,"-07.30",255,1750,"-14.40",368,1750,"-15.60",376,1750,"-16.00"
 [PCI  , EARFCN, RSRQ]
Trong do : 257,1750,"-07.30" -> Current Cell ID : [PCI  , EARFCN, RSRQ] . Cac phan sau la neighbours cell
- RSRP ( Note :chi co trong 4G. Trong Mode 3G / 2G tra ra ket qua trang )
RSRP: 257,1750,"-079.40",255,1750,"-087.70",368,1750,"-088.20"
Trong do : 257,1750,"-079.40" -> Current Cell ID : [PCI  , EARFCN, RSRP] . Cac phan sau la neighbours cell
3. SET RAT : Chon mang ( 2G / 3G / 4G)
Khi goi lenh se mo ra giao dien chon mang. Chu y : Bien String appid su dung de Back ve current Activity
vd : String appid = "com.ptsp.phoneapi/com.ptsp.phoneapi.PhoneApi";
                    voidRAT_Lock(appid);
4. Thiet Lap Ban Dau :
Khi bat dau Test (Lan dau Tien), can chay 2 API theo thu tu:
     voidInitialize_CMD(); // Load  1st (first) Hook System and print logcat.
     Chu y : function voidInitialize_CMD() phai goi trong thread moi.
     voidP2A4G(String appid); // Load  2nd to Open SService and Back to this Activity

*/
