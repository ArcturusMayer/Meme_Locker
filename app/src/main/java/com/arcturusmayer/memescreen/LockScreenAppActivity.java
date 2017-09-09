package com.arcturusmayer.memescreen;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Arcturus Mayer on 26.08.2017.
 */

/**
 * Copyright 2017 Mikhailov Vladislav

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
    public String name1 = "none.txt";
    public String name2 = "ntwo.txt";
    public String name3 = "nthree.txt";
    public String name4 = "nfour.txt";
    public String name5 = "nfive.txt";

    String file;

    Button opt;
    Button unlk;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    private AdView mAdView;

    int choice;
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
        int r = (p.y) / 32;

        b = false;

        opt = (Button) findViewById(R.id.Options);
        unlk = (Button) findViewById(R.id.Unlock);
        one = (Button) findViewById(R.id.SoundOne);
        two = (Button) findViewById(R.id.SoundTwo);
        three = (Button) findViewById(R.id.SoundThree);
        four = (Button) findViewById(R.id.SoundFour);
        five = (Button) findViewById(R.id.SoundFive);

        opt.setTextSize(r);
        opt.setText("Bind Sound Mode");

        unlk.setTextSize(r);
        unlk.setText("Unlock");

        one.setTextSize(r);
        one.setText("Sound Button One");
        one.setText(plays(name1));

        two.setTextSize(r);
        two.setText("Sound Button Two");
        two.setText(plays(name2));

        three.setTextSize(r);
        three.setText("Sound Button Three");
        three.setText(plays(name3));

        four.setTextSize(r);
        four.setText("Sound Button Four");
        four.setText(plays(name4));

        five.setTextSize(r);
        five.setText("Sound Button Five");
        five.setText(plays(name5));

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        opt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    b = false;
                    unlk.setText("Unlock");
                    opt.setText("Bind Sound Mode");
                } else {
                    b = true;
                    unlk.setText("Exit");
                    opt.setText("Play Sound Mode");
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
                    finish();
                } else {
                    overridePendingTransition(0, 0);
                    finish();
                }
            }


        });

        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    choice=1;

                    showDialogOrRequest();
                } else {
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(plays(file1));
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


        });
        two.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    choice=2;
                    showDialogOrRequest();
                } else {
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(plays(file2));
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


        });
        three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    choice=3;
                    showDialogOrRequest();
                } else {
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(plays(file3));
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


        });
        four.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    choice=4;
                    showDialogOrRequest();
                } else {
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(plays(file4));
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


        });
        five.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View p1) {
                releaseMP();
                if (b) {
                    choice=5;
                    showDialogOrRequest();
                } else {
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(plays(file5));
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

    public String plays(final String s){
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

    public void showEditDialog() {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction fmm=fm.beginTransaction();
        editDialog edit=new editDialog();
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
            showEditDialog();
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
}

