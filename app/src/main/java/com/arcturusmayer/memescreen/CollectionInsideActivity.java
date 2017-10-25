package com.arcturusmayer.memescreen;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
    String soundsCountFile = "SoundsCount";
    ArrayList<Boolean> soundsState;
    Button deleteCollection;
    LinearLayout soundsContainer;
    LinearLayout.LayoutParams lParams;
    Button soundButton;
    Button deleteSound;
    LinearLayout soundButtonsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_inside);
        Intent intent = getIntent();
        identifier = intent.getIntExtra(CollectionOutsideActivity.key, 0);
        soundsState = new ArrayList<>();
        soundsContainer = findViewById(R.id.soundcontainer);
        deleteCollection = findViewById(R.id.deletecollection);
        deleteCollection.setOnClickListener(this);
        lParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        soundsCount=0;
        if((readFromFile(soundsCountFile+Integer.toString(identifier))==null)||(readFromFile(soundsCountFile+Integer.toString(identifier))=="")){
            writeToFile(soundsCountFile+Integer.toString(identifier),Integer.toString(soundsCount),this);
        }
        try{
        if (identifier == 0) {
            deleteCollection.setVisibility(View.INVISIBLE);
            soundsCount = Integer.parseInt(readFromFile(soundsCountFile+Integer.toString(identifier)));
            for (int i = 0; i < soundsCount; i++) {
                soundsState.add(Boolean.parseBoolean(readFromFile(Integer.toString(identifier) + "#" + Integer.toString(i) + "state")));
            }
            for (int i = 0; i < soundsCount; i++) {
                if (soundsState.get(i)) {
                    drawSoundButton(soundsContainer, i);
                }
            }
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
        }
    }catch (Exception e){
    }
        Button addSound = new Button(this);
        addSound.setId(R.id.add_sound_id);
        addSound.setText("Add new sound");
        addSound.setAlpha(0.65f);
        addSound.setOnClickListener(this);
        soundsContainer.addView(addSound, lParams);
    }

    private void drawSoundButton(LinearLayout soundContainer, int id){
        lParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        soundButton = new Button(this);
        soundButton.setId(id);
        soundButton.setText(readFromFile(Integer.toString(identifier) + "#" + Integer.toString(id) + "soundname"));
        soundButton.setAlpha(0.65f);
        soundButton.setOnClickListener(this);
        soundButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1));

        deleteSound = new Button(this);
        deleteSound.setId(soundsCount+id);
        deleteSound.setText("Delete");
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
        if(v.getId()==R.id.deletecollection){
            writeToFile((Integer.toString(identifier) + "state"),"false",this);
            for(int i=0;i<soundsCount;i++){
                writeToFile(Integer.toString(identifier)+"#"+Integer.toString(i)+"state","false",this);
            }
            startActivity(new Intent(this,CollectionOutsideActivity.class));
            finish();
        }else{
            if(v.getId()==R.id.add_sound_id){
                showDialogOrRequest();
            }else{
                boolean isBinded = false;
                int i=0;
                do {
                    if(v.getId()==i){
                        int choice = getIntent().getIntExtra("button",1);
                        String file=LockScreenAppActivity.file1;
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
                        writeToFile(file, readFromFile(Integer.toString(identifier)+"#"+Integer.toString(i)+"path"), this);

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
                        writeToFile(file, readFromFile(Integer.toString(identifier)+"#"+Integer.toString(i)+"soundname"), this);
                        isBinded = true;
                        startActivity(new Intent(this,LockScreenAppActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                    }
                    i++;
                } while ((v.getId() != i-1)&&(i<soundsCount));
                i=soundsCount;
                do {
                    if((v.getId()==i)&&(!isBinded)){
                        writeToFile(Integer.toString(identifier)+"#"+Integer.toString(i-soundsCount)+"state","false",this);
                        soundButtonsGroup = findViewById(i+soundsCount*2);
                        soundsContainer.removeView(soundButtonsGroup);
                    }
                    i++;
                } while ((v.getId() != i-1)&&(i<soundsCount*2));
            }
        }
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
}
