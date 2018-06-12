package com.arcturusmayer.memescreen;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Arcturus Mayer on 26.08.2017.
 */

/**
 * Copyright 2017 Mikhaylov Vladislav

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

public class LockScreenAppActivity extends FragmentActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    public static String file1 = new String("sone.txt");
    public static String file2 = "stwo.txt";
    public static String file3 = "sthree.txt";
    public static String file4 = "sfour.txt";
    public static String file5 = "sfive.txt";
    public static String name1 = "none.txt";
    public static String name2 = "ntwo.txt";
    public static String name3 = "nthree.txt";
    public static String name4 = "nfour.txt";
    public static String name5 = "nfive.txt";

    String file;

    Button opt;
    Button unlk;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    LinearLayout slideContainer;
    LinearLayout.LayoutParams layoutParams;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    int choice;
    int windowwidth;
    int start_x_cord;
    int swipe_length;
    Boolean b;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_main);

        startService(new Intent(LockScreenAppActivity.this, MyService.class));

        Point p = new Point();
        ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(p);
        float r = (p.y) / 45;

        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        swipe_length = (int) (windowwidth*0.5f);

        b = false;

        opt = (Button) findViewById(R.id.Options);
        unlk = (Button) findViewById(R.id.Unlock);
        one = (Button) findViewById(R.id.SoundOne);
        two = (Button) findViewById(R.id.SoundTwo);
        three = (Button) findViewById(R.id.SoundThree);
        four = (Button) findViewById(R.id.SoundFour);
        five = (Button) findViewById(R.id.SoundFive);
        slideContainer = findViewById(R.id.slideContainer);

        //opt.setText("Режим проигрывания");
        opt.setText("Playback mode");
        opt.setTextSize(r);


        //unlk.setText("Разблокировать");
        unlk.setText("Unlock");
        unlk.setTextSize(r);


        one.setText(readFromFile(name1));
        one.setTextSize(r);


        two.setText(readFromFile(name2));
        two.setTextSize(r);


        three.setText(readFromFile(name3));
        three.setTextSize(r);


        four.setText(readFromFile(name4));
        four.setTextSize(r);


        five.setText(readFromFile(name5));
        five.setTextSize(r);


        File isFirst = new File(getFilesDir()+"/isitfirstlaunch.txt");
        if(!isFirst.exists()){
            writeToFile("isitfirstlaunch.txt","isnotfirst",this);
           showCreateDialog();
        }


        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitisl_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        opt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    b = false;
                    //unlk.setText("Разблокировать");
                    //opt.setText("Режим проигрывания");

                    unlk.setText("Unlock");
                    opt.setText("Playback mode");
                } else {
                    b = true;
                    //unlk.setText("Отключить");
                    //opt.setText("Режим привязки");

                    unlk.setText("Turn off");
                    opt.setText("Audio binding mode");
                }

            }


        });
        unlk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    stopService(new Intent(LockScreenAppActivity.this, MyService.class));
                    overridePendingTransition(0, 0);
                    finishAndRemoveTask();
                } else {
                    overridePendingTransition(0, 0);
                    finishAndRemoveTask();
                }
            }


        });

        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    choice=1;
                    Intent intent =new Intent(LockScreenAppActivity.this,CollectionOutsideActivity.class);
                    intent.putExtra("button",choice);
                    startActivity(intent);
                    mInterstitialAd.show();
                } else {
                    playSound(file1);
                }
            }


        });
        two.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    choice=2;
                    Intent intent =new Intent(LockScreenAppActivity.this,CollectionOutsideActivity.class);
                    intent.putExtra("button",choice);
                    startActivity(intent);
                    mInterstitialAd.show();
                } else {
                    playSound(file2);
                }
            }


        });
        three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    choice=3;
                    Intent intent =new Intent(LockScreenAppActivity.this,CollectionOutsideActivity.class);
                    intent.putExtra("button",choice);
                    startActivity(intent);
                    mInterstitialAd.show();
                } else {
                    playSound(file3);
                }
            }


        });
        four.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    choice=4;
                    Intent intent =new Intent(LockScreenAppActivity.this,CollectionOutsideActivity.class);
                    intent.putExtra("button",choice);
                    startActivity(intent);
                    mInterstitialAd.show();
                } else {
                    playSound(file4);
                }
            }


        });
        five.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    choice=5;
                    Intent intent =new Intent(LockScreenAppActivity.this,CollectionOutsideActivity.class);
                    intent.putExtra("button",choice);
                    startActivity(intent);
                    mInterstitialAd.show();
                } else {
                    playSound(file5);
                }
            }


        });

        slideContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();

                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        start_x_cord = (int) motionEvent.getRawX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int x_cord = (int) motionEvent.getRawX();
                        layoutParams.rightMargin = -(x_cord-start_x_cord);
                        view.setLayoutParams(layoutParams);
                        if (Math.abs(x_cord-start_x_cord) >= swipe_length) {
                            view.setVisibility(View.GONE);
                            releaseMP();
                            finishAndRemoveTask();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        int x_cord1 = (int) motionEvent.getRawX();
                        if(Math.abs(x_cord1-start_x_cord) < swipe_length) {
                            layoutParams.rightMargin = 0;
                            view.setLayoutParams(layoutParams);
                        }
                        break;
                }

                return true;
            }
        });
    }
 
    public void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int getSoundId(String soundid){
        switch(Integer.parseInt(soundid.substring(soundid.lastIndexOf("#")+1))){
            case 1:
                return R.raw.christopherwalkenidontknow;
            case 2:
                return R.raw.hallelujahchorussoundeffect;
            case 4:
                return R.raw.mbisonofcourse;
            case 5:
                return R.raw.nogodpleaseno;
            case 7:
                return R.raw.whatastorymark;
            case 9:
                return R.raw.youshallnotpasslordofthering;
            case 10:
                return R.raw.voteto;
            case 12:
                return R.raw.applause;
            case 13:
                return R.raw.nelsonhaha;
        }
        return R.raw.hallelujahchorussoundeffect;
    }

    public void writeToFile(String fileName,String toWrite, Context ctx){
        try {
            FileOutputStream fOut = ctx.openFileOutput(fileName, ctx.MODE_PRIVATE);
            OutputStreamWriter osw=new OutputStreamWriter(fOut);
            try
            {
                osw.write(toWrite);
            }
            catch (IOException e)
            {}
            try
            {
                osw.flush();
            }
            catch (IOException e)
            {}
            try
            {
                osw.close();
            }
            catch (IOException e)
            {}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFromFile(final String s){
        String d="";
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(s)));
            try
            {
                d=br.readLine();
                return d;
            }
            catch (IOException e)
            {}
        }
        catch (FileNotFoundException e)
        {}
        return d;
    }

    public void showFileDialog() {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction fmm=fm.beginTransaction();
        FileDialog edit=new FileDialog();
        edit.show(fmm,"edit_dialog");
    }

    public void showDialogOrRequest(){
        if ((ContextCompat.checkSelfPermission(LockScreenAppActivity.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)||(ContextCompat.checkSelfPermission(LockScreenAppActivity.this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)||(ContextCompat.checkSelfPermission(LockScreenAppActivity.this,
                android.Manifest.permission.READ_PHONE_STATE
        )!=PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(LockScreenAppActivity.this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }else {
            showFileDialog();
        }
    }

    public void onClicked(String text, String filepath) {

        switch (choice) {
            case (1):
                file = file1;
                break;
            case (2):
                file = file2;
                break;
            case (3):
                file = file3;
                break;
            case (4):
                file = file4;
                break;
            case (5):
                file = file5;
                break;
        }
        writeToFile(file, filepath, LockScreenAppActivity.this);

        switch (choice) {
            case (1):
                one.setText(text);
                file = name1;
                break;
            case (2):
                two.setText(text);
                file = name2;
                break;
            case (3):
                three.setText(text);
                file = name3;
                break;
            case (4):
                four.setText(text);
                file = name4;
                break;
            case (5):
                five.setText(text);
                file = name5;
                break;
        }
        writeToFile(file, text, LockScreenAppActivity.this);
    }

    private void showCreateDialog(){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction fmm=fm.beginTransaction();
        StudyingDialog diag = new StudyingDialog();
        diag.show(fmm,"CreateCol");
    }

    private void playSound(String PathFile){
        if (readFromFile(PathFile).startsWith("#")) {
            mediaPlayer = MediaPlayer.create(LockScreenAppActivity.this,getSoundId(readFromFile(PathFile)));
            mediaPlayer.start();
        } else {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(readFromFile(PathFile));
            } catch (IllegalStateException e) {
            } catch (IOException e) {
            } catch (IllegalArgumentException e) {
            } catch (SecurityException e) {
            }
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.prepare();
            } catch (IllegalStateException e) {
            } catch (IOException e) {
            }
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        releaseMP();
        super.onDestroy();
    }
}