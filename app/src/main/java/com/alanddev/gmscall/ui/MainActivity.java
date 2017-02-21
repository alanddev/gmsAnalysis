package com.alanddev.gmscall.ui;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.alanddev.gmscall.R;
import com.alanddev.gmscall.controller.*;
import com.alanddev.gmscall.helper.*;
import com.alanddev.gmscall.util.*;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//    Thread background = new Thread() {
//        public void run() {
//            try {
//                init();
//                if (checkDB()) {
//                    //Utils.setWallet_id(Utils.getSharedPreferencesValue(getApplicationContext(), Constant.WALLET_ID));
//                    sleep(Constant.SPLASH_DISPLAY_LONG);
//                } else {
//                    initfor1st();
//                    sleep(Constant.SPLASH_DISPLAY_SHORT);
//                }
////                if (checkFirst()) {
////                    Intent i = new Intent(getBaseContext(), TransactionActivity.class);
////                    startActivity(i);
////                } else if(Utils.getCurrentLanguage(getApplicationContext()).equals("")){
////
////                    Intent intent = new Intent(MainActivity.this, SelectThemeActivity.class);
////                    intent.putExtra("SETTING_EXTRA",Constant.CHANGE_LANGUAGE_ID);
////                    intent.putExtra("SETTING_FIRST",Constant.CHANGE_LANGUAGE_ID);
////                    startActivity(intent);
////                }else{
//                    Intent intent = new Intent(getApplicationContext(), WalletAddActivity.class);
//                    startActivity(intent);
////                }
//                finish();
//            } catch (Exception e) {
//            }
//        }
//    };

    private Boolean checkDB() {
        File dbtest = new File(getResources().getString(R.string.db_path) + MwSQLiteHelper.DATABASE_NAME);
        return dbtest.exists();
    }

    private Boolean checkFirst() {
//        WalletController controller = new WalletController(this);
//        controller.open();
//        return (controller.getCount() > 0);
        return false;
    }

    private void initfor1st() {
        CurrencyController currController = new CurrencyController(getApplicationContext());
        currController.open();
        currController.init(this);
        currController.close();
        //start notification services
        Intent intent = new Intent();
        intent.setAction("com.alanddev.manwal.CUSTOM_INTENT");
        sendBroadcast(intent);
    }

    private void init() {
        String theme = Utils.getCurrentTheme(this);
        if (!theme.equals("")) {
            Utils.changeToTheme(theme);
        } else {
            Utils.changeToTheme("Default_Theme");
        }

//        String naviheader = Utils.getCurrentNavHeader(this);
//        if (naviheader.equals("")) {
//            Utils.setSharedPreferencesValue(getApplicationContext(), Constant.NAV_HEADER_CURRENT, getString(R.string.navi_header_default));
//        }
        //setContentView(R.layout.activity_man_wal);

//        if (getIntent().getExtras() != null && getIntent().getExtras().get("NOTIFICATION").toString().equals("1")) {
//            NotificationManager mNotifyMgr =
//                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            mNotifyMgr.cancel(NotifyService.GREETNG_ID);
//        }
        //Utils.createFolder(Constant.PATH_IMG);
    }

}
