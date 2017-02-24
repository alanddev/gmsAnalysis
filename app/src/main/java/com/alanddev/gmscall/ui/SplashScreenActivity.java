package com.alanddev.gmscall.ui;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.alanddev.gmscall.R;
import com.alanddev.gmscall.util.Constant;
import com.alanddev.gmscall.util.Utils;

import java.io.File;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        background.start();
    }
    Thread background = new Thread() {
        public void run() {
            try {
                init();

                //userController.open();
                sleep(Constant.SPLASH_DISPLAY_SHORT);
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };




    /*private void initfor1st() {
        ExcerciseGroupController controller = new ExcerciseGroupController(getApplicationContext());
        controller.open();
        controller.init();
        controller.close();

        ExcerciseController excerciseController = new ExcerciseController(getApplicationContext());
        excerciseController.open();
        excerciseController.init();
        excerciseController.close();
        //start notification services
        Intent intent = new Intent();
        intent.setAction("com.alanddev.manwal.CUSTOM_INTENT");
        sendBroadcast(intent);

        WorkoutController workoutController = new WorkoutController(getApplicationContext());
        workoutController.open();
        workoutController.init();
        workoutController.close();

        WorkoutExerController exerController = new WorkoutExerController(getApplicationContext());
        exerController.open();
        exerController.init();
        exerController.close();


    }*/

    private void init() {
        String theme = Utils.getCurrentTheme(this);
        if (!theme.equals("")) {
            Utils.changeToTheme(theme);
        } else {
            Utils.setSharedPreferencesValue(getApplicationContext(), Constant.THEME_CURRENT, "DodgerBlue_Theme");
            Utils.changeToTheme("DodgerBlue_Theme");
        }

        String naviheader = Utils.getCurrentNavHeader(this);
        if (naviheader.equals("")) {
            Utils.setSharedPreferencesValue(getApplicationContext(), Constant.NAV_HEADER_CURRENT, getString(R.string.navi_header_default));
        }


        Utils.createFolder(Constant.PATH_IMG);
    }

}
