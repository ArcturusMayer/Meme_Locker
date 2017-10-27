package com.arcturusmayer.memescreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class CollectionOutsideActivity extends FragmentActivity implements View.OnClickListener {

    private String collectionsCountFile="collectionsCount.txt";
    int collectionsCount;
    LinearLayout.LayoutParams lParams;
    LinearLayout collectionsContainer;
    Button colbt;
    Button stdcol;
    Button addcol;
    ArrayList<Boolean> collectionState;
    public static String key = "key";
    Intent inten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_outside);

        inten = getIntent();

        lParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        collectionsContainer = (LinearLayout) findViewById(R.id.collectioncontainer);

        stdcol=findViewById(R.id.standartcollection);
        stdcol.setOnClickListener(this);



        collectionState=new ArrayList<>();

        try {
            collectionsCount = Integer.parseInt(readFromFile(collectionsCountFile));

            for (int i = 1; i <= collectionsCount; i++) {
                collectionState.add(Boolean.parseBoolean(readFromFile(Integer.toString(i) + "state")));
            }

            for (int i = 1; i <= collectionsCount; i++) {
                if (collectionState.get(i - 1)) {
                    drawColButton(collectionsContainer, i);
                }
            }

        }catch (Exception e){

        }

        addcol=new Button(this);
        addcol.setId(R.id.add_collection_id);
        addcol.setOnClickListener(this);
        addcol.setText("Add new");
        addcol.setAlpha(0.65f);
        collectionsContainer.addView(addcol,lParams);
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

    public void drawColButton(LinearLayout collectionContainer, int id){
        lParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            colbt = new Button(this);
            colbt.setId(id);
            colbt.setText(readFromFile(Integer.toString(id) + "text"));
            colbt.setOnClickListener(this);
            colbt.setAlpha(0.65f);
            collectionContainer.addView(colbt,lParams);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,CollectionInsideActivity.class);
        if((v.getId()!=R.id.standartcollection)&&(v.getId()!=R.id.add_collection_id)) {
            int i=0;
            //intent.putExtra(key,i);
            do {
                i++;
                if(v.getId()==i){
                    intent.putExtra(key, i);
                    intent.putExtra("button",inten.getIntExtra("button",1));
                    startActivity(intent);
                }
            } while ((v.getId() != i)&&(i<=collectionsCount));
        }else{
            if(v.getId()==R.id.add_collection_id){
                showCreateDialog();
            }else{
                intent.putExtra(key,0);
                intent.putExtra("button",inten.getIntExtra("button",1));
                startActivity(intent);
            }
        }
    }

    public void addCollection(String collectionName){
        Boolean isWrote = false;
        for(int i=0;i<collectionsCount;i++){
            if(!collectionState.get(i)){
                collectionState.set(i, true);
                writeToFile((Integer.toString(i+1) + "text"),collectionName,this);
                writeToFile((Integer.toString(i+1) + "state"),"true",this);
                isWrote=true;
                Intent intent = new Intent(this,CollectionInsideActivity.class);
                intent.putExtra(key, i+1);
                intent.putExtra("button",inten.getIntExtra("button",1));
                startActivity(intent);
            }
        }
        if(!isWrote){
            collectionsCount++;
            writeToFile((Integer.toString(collectionsCount))+"text", collectionName,this);
            writeToFile((Integer.toString(collectionsCount) + "state"),"true",this);
            writeToFile(collectionsCountFile,Integer.toString(collectionsCount),this);
            collectionState.add(true);
            Intent intent = new Intent(this,CollectionInsideActivity.class);
            intent.putExtra(key, collectionsCount);
            intent.putExtra("button",inten.getIntExtra("button",1));
            startActivity(intent);
        }
    }

    private void showCreateDialog(){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction fmm=fm.beginTransaction();
        NewCollectionDialog diag = new NewCollectionDialog();
        diag.show(fmm,"CreateCol");
    }
}
