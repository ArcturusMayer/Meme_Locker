package com.arcturusmayer.memescreen;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

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

public class CollectionInsideActivity extends FragmentActivity implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    int soundsCount;
    int identifier;
    float r;
    String soundsCountFile = "SoundsCount";
    ArrayList<Boolean> soundsState;
    Button deleteCollection;
    LinearLayout soundsContainer;
    LinearLayout.LayoutParams lParams;
    Button soundButton;
    Button deleteSound;
    LinearLayout soundButtonsGroup;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_inside);
        Intent intent = getIntent();
        identifier = intent.getIntExtra(CollectionOutsideActivity.key, 0);
        soundsState = new ArrayList<>();
        soundsContainer = findViewById(R.id.soundcontainer);
        Point p = new Point();
        ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(p);
        r = (p.y) / 38;
        TextView sound = findViewById(R.id.sound);
        sound.setTextSize(r);
        deleteCollection = findViewById(R.id.deletecollection);
        deleteCollection.setOnClickListener(this);

        mAdView = (AdView) findViewById(R.id.adViewThree);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        lParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParams.setMargins(8,4,8,4);
        soundsCount=0;
        if((readFromFile(soundsCountFile+Integer.toString(identifier))==null)||(readFromFile(soundsCountFile+Integer.toString(identifier))=="")){
            writeToFile(soundsCountFile+Integer.toString(identifier),Integer.toString(soundsCount),this);
        }
        try{
        if (identifier == 0) {
            deleteCollection.setVisibility(View.INVISIBLE);
            Button idontknow = new Button(this);
            idontknow.setId(R.id.idontknow);
            idontknow.setText("I don't know!");
            idontknow.setTextSize(r);
            idontknow.setAlpha(0.65f);
            idontknow.setOnClickListener(this);
            soundsContainer.addView(idontknow,lParams);
            Button hallelujah = new Button(this);
            hallelujah.setId(R.id.hallelujah);
            hallelujah.setText("Hallelujah!");
            hallelujah.setTextSize(r);
            hallelujah.setAlpha(0.65f);
            hallelujah.setOnClickListener(this);
            soundsContainer.addView(hallelujah,lParams);
            Button idontgive = new Button(this);
            idontgive.setId(R.id.idontgive);
            idontgive.setText("I don't give a fuck!");
            idontgive.setTextSize(r);
            idontgive.setAlpha(0.65f);
            idontgive.setOnClickListener(this);
            soundsContainer.addView(idontgive,lParams);
            Button ofcourse = new Button(this);
            ofcourse.setId(R.id.ofcourse);
            ofcourse.setText("Of course!");
            ofcourse.setTextSize(r);
            ofcourse.setAlpha(0.65f);
            ofcourse.setOnClickListener(this);
            soundsContainer.addView(ofcourse,lParams);
            Button nogod = new Button(this);
            nogod.setId(R.id.nogod);
            nogod.setText("NO GOD! PLEASE NO!");
            nogod.setTextSize(r);
            nogod.setAlpha(0.65f);
            nogod.setOnClickListener(this);
            soundsContainer.addView(nogod,lParams);
            Button fuckyou = new Button(this);
            fuckyou.setId(R.id.fckyou);
            fuckyou.setText("Fuck you, asshole");
            fuckyou.setTextSize(r);
            fuckyou.setAlpha(0.65f);
            fuckyou.setOnClickListener(this);
            soundsContainer.addView(fuckyou,lParams);
            Button whatastory = new Button(this);
            whatastory.setId(R.id.whatastory);
            whatastory.setText("What a story, Mark!");
            whatastory.setTextSize(r);
            whatastory.setAlpha(0.65f);
            whatastory.setOnClickListener(this);
            soundsContainer.addView(whatastory,lParams);
            Button wtf = new Button(this);
            wtf.setId(R.id.wtf);
            wtf.setText("What the fuck is this?!");
            wtf.setTextSize(r);
            wtf.setAlpha(0.65f);
            wtf.setOnClickListener(this);
            soundsContainer.addView(wtf,lParams);
            Button youwontpass = new Button(this);
            youwontpass.setId(R.id.youwontpass);
            youwontpass.setText("YOU SHALL NOT PASS!");
            youwontpass.setTextSize(r);
            youwontpass.setAlpha(0.65f);
            youwontpass.setOnClickListener(this);
            soundsContainer.addView(youwontpass,lParams);
        } else {
                soundsCount = Integer.parseInt(readFromFile(soundsCountFile+Integer.toString(identifier)));
            for (int i = 0; i < soundsCount; i++) {
                    soundsState.add(Boolean.parseBoolean(readFromFile(Integer.toString(identifier) + "#" + Integer.toString(i) + "state")));
            }
            for (int i = 0; i < soundsCount; i++) {
                if (soundsState.get(i)) {
                    drawSoundButton(soundsContainer, i);
                }
            }
            Button addSound = new Button(this);
            addSound.setId(R.id.add_sound_id);
            addSound.setText("Add new sound");
            addSound.setTextSize(r);
            addSound.setAlpha(0.65f);
            addSound.setOnClickListener(this);
            soundsContainer.addView(addSound, lParams);
        }
    }catch (Exception e){
    }

    }

    private void drawSoundButton(LinearLayout soundContainer, int id){
        lParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lParams.setMargins(8,4,8,4);
        soundButton = new Button(this);
        soundButton.setId(id);
        soundButton.setText(readFromFile(Integer.toString(identifier) + "#" + Integer.toString(id) + "soundname"));
        soundButton.setTextSize(r);
        soundButton.setAlpha(0.65f);
        soundButton.setOnClickListener(this);
        soundButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1));

        deleteSound = new Button(this);
        deleteSound.setId(soundsCount+id);
        deleteSound.setText("Delete");
        deleteSound.setTextSize(r);
        deleteSound.setAlpha(0.65f);
        deleteSound.setOnClickListener(this);
        deleteSound.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,3));

        soundButtonsGroup = new LinearLayout(this);
        soundButtonsGroup.setOrientation(LinearLayout.HORIZONTAL);
        soundButtonsGroup.setId(soundsCount*2+id);
        soundButtonsGroup.addView(soundButton);
        soundButtonsGroup.addView(deleteSound);
        soundContainer.addView(soundButtonsGroup,lParams);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.idontknow:
                onSoundChoosen("I don't know!","1");
                break;
            case R.id.hallelujah:
                onSoundChoosen("Hallelujah!","2");
                break;
            case R.id.idontgive:
                onSoundChoosen("I don't give a fuck!","3");
                break;
            case R.id.ofcourse:
                onSoundChoosen("Of course!","4");
                break;
            case R.id.nogod:
                onSoundChoosen("NO GOD! PLEASE NO!","5");
                break;
            case R.id.fckyou:
                onSoundChoosen("Fuck you, asshole","6");
                break;
            case R.id.whatastory:
                onSoundChoosen("What a story, Mark!","7");
                break;
            case R.id.wtf:
                onSoundChoosen("What the fuck is this?!","8");
                break;
            case R.id.youwontpass:
                onSoundChoosen("YOU SHALL NOT PASS!","9");
                break;
        }
            if (v.getId() == R.id.deletecollection) {
                writeToFile((Integer.toString(identifier) + "state"), "false", this);
                for (int i = 0; i < soundsCount; i++) {
                    writeToFile(Integer.toString(identifier) + "#" + Integer.toString(i) + "state", "false", this);
                }
                startActivity(new Intent(this, CollectionOutsideActivity.class));
                finish();
            } else {
                if (v.getId() == R.id.add_sound_id) {
                    showDialogOrRequest();
                } else {
                    boolean isBinded = false;
                    int i = 0;
                    do {
                        if (v.getId() == i) {
                            int choice = getIntent().getIntExtra("button", 1);
                            String file = LockScreenAppActivity.file1;
                            switch (choice) {
                                case (1):
                                    file = LockScreenAppActivity.file1;
                                    break;
                                case (2):
                                    file = LockScreenAppActivity.file2;
                                    break;
                                case (3):
                                    file = LockScreenAppActivity.file3;
                                    break;
                                case (4):
                                    file = LockScreenAppActivity.file4;
                                    break;
                                case (5):
                                    file = LockScreenAppActivity.file5;
                                    break;
                            }
                            writeToFile(file, readFromFile(Integer.toString(identifier) + "#" + Integer.toString(i) + "path"), this);

                            switch (choice) {
                                case (1):
                                    file = LockScreenAppActivity.name1;
                                    break;
                                case (2):
                                    file = LockScreenAppActivity.name2;
                                    break;
                                case (3):
                                    file = LockScreenAppActivity.name3;
                                    break;
                                case (4):
                                    file = LockScreenAppActivity.name4;
                                    break;
                                case (5):
                                    file = LockScreenAppActivity.name5;
                                    break;
                            }
                            writeToFile(file, readFromFile(Integer.toString(identifier) + "#" + Integer.toString(i) + "soundname"), this);
                            isBinded = true;
                            startActivity(new Intent(this, LockScreenAppActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                            finish();
                        }
                        i++;
                    } while ((v.getId() != i - 1) && (i < soundsCount));
                    i = soundsCount;
                    do {
                        if ((v.getId() == i) && (!isBinded)) {
                            writeToFile(Integer.toString(identifier) + "#" + Integer.toString(i - soundsCount) + "state", "false", this);
                            Intent intent = getIntent();
                            intent.putExtra(CollectionOutsideActivity.key, intent.getIntExtra(CollectionOutsideActivity.key, 0));
                            intent.putExtra("button", intent.getIntExtra("button", 1));
                            overridePendingTransition(0, 0);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                            finish();
                        }
                        i++;
                    } while ((v.getId() != i - 1) && (i < soundsCount * 2));
                }
            }
    }

    private void onSoundChoosen(String soundname,String soundid){
        int choice = getIntent().getIntExtra("button", 1);
        String file = LockScreenAppActivity.file1;
        switch (choice) {
            case (1):
                file = LockScreenAppActivity.file1;
                break;
            case (2):
                file = LockScreenAppActivity.file2;
                break;
            case (3):
                file = LockScreenAppActivity.file3;
                break;
            case (4):
                file = LockScreenAppActivity.file4;
                break;
            case (5):
                file = LockScreenAppActivity.file5;
                break;
        }
        writeToFile(file, "#"+soundid, this);

        switch (choice) {
            case (1):
                file = LockScreenAppActivity.name1;
                break;
            case (2):
                file = LockScreenAppActivity.name2;
                break;
            case (3):
                file = LockScreenAppActivity.name3;
                break;
            case (4):
                file = LockScreenAppActivity.name4;
                break;
            case (5):
                file = LockScreenAppActivity.name5;
                break;
        }
        writeToFile(file, soundname, this);
        startActivity(new Intent(this, LockScreenAppActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();
    }

    public void onFileChoosed(String name, String filepath){
        soundsCount=Integer.parseInt(readFromFile(soundsCountFile+Integer.toString(identifier)));
        Boolean isWrote=false;
        for(int i=0;i<soundsCount;i++){
            if(!soundsState.get(i)){
                isWrote=true;
                writeToFile(Integer.toString(identifier)+"#"+Integer.toString(i)+"state","true",this);
                writeToFile(Integer.toString(identifier)+"#"+Integer.toString(i)+"soundname",name,this);
                writeToFile(Integer.toString(identifier)+"#"+Integer.toString(i)+"path",filepath,this);
                Intent intent = getIntent();
                intent.putExtra(CollectionOutsideActivity.key,intent.getIntExtra(CollectionOutsideActivity.key, 0));
                intent.putExtra("button",intent.getIntExtra("button",1));
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        }
        if(!isWrote) {
            soundsCount++;
            writeToFile(soundsCountFile+Integer.toString(identifier),Integer.toString(soundsCount),this);
            writeToFile(Integer.toString(identifier) + "#" + Integer.toString(soundsCount - 1) + "state", "true", this);
            writeToFile(Integer.toString(identifier) + "#" + Integer.toString(soundsCount - 1) + "soundname", name, this);
            writeToFile(Integer.toString(identifier) + "#" + Integer.toString(soundsCount - 1) + "path", filepath, this);
            Intent intent = getIntent();
            intent.putExtra(CollectionOutsideActivity.key,intent.getIntExtra(CollectionOutsideActivity.key, 0));
            intent.putExtra("button",intent.getIntExtra("button",1));
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        }
    }

    public void writeToFile(String fileName, String toWrite, Context ctx){
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

    public String readFromFile(String s){
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
        if ((ContextCompat.checkSelfPermission(CollectionInsideActivity.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)||(ContextCompat.checkSelfPermission(CollectionInsideActivity.this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)||(ContextCompat.checkSelfPermission(CollectionInsideActivity.this,
                android.Manifest.permission.READ_PHONE_STATE
        )!=PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(CollectionInsideActivity.this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_PHONE_STATE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }else {
            showFileDialog();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,CollectionOutsideActivity.class));
        finish();
    }
}
