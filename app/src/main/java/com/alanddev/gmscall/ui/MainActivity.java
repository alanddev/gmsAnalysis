package com.alanddev.gmscall.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alanddev.gmscall.R;
import com.alanddev.gmscall.adapter.CellAdapter;
import com.alanddev.gmscall.adapter.TransSectionPagerAdapter;
import com.alanddev.gmscall.controller.*;
import com.alanddev.gmscall.fragment.CallFragment;
import com.alanddev.gmscall.fragment.CellFragment;
import com.alanddev.gmscall.fragment.CommandFragment;
import com.alanddev.gmscall.fragment.GMapFragment;
import com.alanddev.gmscall.fragment.TransactionFragment;
import com.alanddev.gmscall.helper.*;
import com.alanddev.gmscall.model.Cell;
import com.alanddev.gmscall.util.*;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TransSectionPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private final int REQUEST_SETTING = 100;
    private final int REQUEST_USER_EDIT = 101;
    private SharedPreferences mShaPref;
    private NavigationView navigationView;
    private TabLayout tabLayout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_main));


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        List<Transactions> transactionses = new ArrayList<Transactions>();
        //transactionses.add(new Transactions());
        //transactionses.add(new Transactions());
        //transactionses.add(new Transactions());
//        mSectionsPagerAdapter = new TransSectionPagerAdapter(getSupportFragmentManager(),transactionses);

        NwConst.getInstance(this);
        // Set up the ViewPager with the sections adapter.
        mSectionsPagerAdapter = new TransSectionPagerAdapter(getSupportFragmentManager());
        mSectionsPagerAdapter.addFrag(TransactionFragment.newInstance(mSectionsPagerAdapter.getCount() + 1),"CELL");
        mSectionsPagerAdapter.addFrag(new CommandFragment(),"COMMAND");
        mSectionsPagerAdapter.addFrag(new CellFragment(),"INFO");
        mSectionsPagerAdapter.addFrag(new CallFragment(),"CALL");
        mSectionsPagerAdapter.addFrag(new GMapFragment(), "MAP");
        mViewPager = (ViewPager) findViewById(R.id.container);

        mViewPager.setAdapter(mSectionsPagerAdapter);
        /*if(transactionses.size()>0) {
            mViewPager.setCurrentItem(transactionses.size() - 2);
        }*/

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);



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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_primary_sim) {
            // Handle the camera action
        } else if (id == R.id.nav_second_sim) {

        } else if (id == R.id.nav_load_logfile) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void setNavHeader(NavigationView navigationView){
        View header = navigationView.getHeaderView(0);
       /* UserController controller = new UserController(this);
        controller.open();
        User user = controller.getId(1);
        controller.close();*/

        TextView txtWallet = (TextView) header.findViewById(R.id.txtWallet);
        imageView = (ImageView)header.findViewById(R.id.imageView);
        txtWallet.setText("anld");

        /*if (!user.getImg().equals("")){
            imageView.setImageBitmap(BitmapFactory.decodeFile(Constant.PATH_IMG + "/" + user.getImg()));
        }else {
            imageView.setImageResource(R.mipmap.avatar);
        }*/
        imageView.setImageResource(R.mipmap.avatar);

        //String naviheader = Utils.getCurrentNavHeader(this);

        //header.setBackgroundResource(getResources().getIdentifier(naviheader, "mipmap", getPackageName()));



       /* imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, Constant.GALLERY_USER_REQUEST);
            }
        } );*/
    }
}
