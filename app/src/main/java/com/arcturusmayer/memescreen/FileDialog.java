package com.arcturusmayer.memescreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arcturus Mayer on 27.08.2017.
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

public class FileDialog extends DialogFragment {

    private Button buttonUp;
    private TextView textFolder;
    private ListView dialog_ListView;
    private AlertDialog diag;

    File root;
    File curFolder;
    private List<String> fileList = new ArrayList<String>();
    private List<String> falsefilelist = new ArrayList<String>();
    public String filepath;

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
    public String ext1 = ".mp3";
    public String ext2 = ".ogg";
    public String ext3 = ".wav";
    public String ext4 = ".m4a";
    public String ext5 = ".mid";
    public String ext6 = ".flac";
    public String ext7 = ".aac";
    public String ext8 = ".xmf";
    public String ext9 = ".mxmf";
    public String ext10 = ".rtttl";
    public String ext11 = ".rtx";
    public String ext12 = ".ota";
    public String ext13 = ".imy";

    private EditText mEditText;

    public FileDialog() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialoglayout, (ViewGroup) getActivity().findViewById(R.id.layout));
        builder.setView(view);
        textFolder = (TextView) view.findViewById(R.id.folder);
        buttonUp = (Button) view.findViewById(R.id.up);
        mEditText = (EditText) view.findViewById(R.id.buttonname);
        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        curFolder = root;
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curFolder.getParentFile().exists()) {
                    ListDir(curFolder.getParentFile());
                }
            }
        });

        dialog_ListView = (ListView) view.findViewById(R.id.dialoglist);
        diag = builder.create();
        ListDir(curFolder);
        dialog_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File selected = new File(fileList.get(position));
                if (selected.isDirectory()) {
                    ListDir(selected);
                } else {
                    if(!mEditText.getText().toString().equals("")) {
                        filepath = selected.getAbsolutePath();
                        String f = mEditText.getText().toString();
                        ((CollectionInsideActivity) getActivity()).onFileChoosed(f, filepath);
                        diag.cancel();
                    }else{
                        Toast.makeText((CollectionInsideActivity)getActivity(),"Enter sound's name",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return diag;
    }

    void ListDir(File f) {
        if(f.equals(root)) {
            buttonUp.setEnabled(false);
        } else {
            buttonUp.setEnabled(true);
        }

        curFolder = f;
        textFolder.setText(f.getPath());

        File[] files = f.listFiles();
        fileList.clear();
        falsefilelist.clear();

        for(File file : files) {
            if((file.isDirectory())||(file.getAbsolutePath().endsWith(ext1))||(file.getAbsolutePath().endsWith(ext2))||(file.getAbsolutePath().endsWith(ext3))
                    ||(file.getAbsolutePath().endsWith(ext4))||(file.getAbsolutePath().endsWith(ext5))||(file.getAbsolutePath().endsWith(ext6))||(file.getAbsolutePath().endsWith(ext7))
                    ||(file.getAbsolutePath().endsWith(ext8))||(file.getAbsolutePath().endsWith(ext9))||(file.getAbsolutePath().endsWith(ext10))||(file.getAbsolutePath().endsWith(ext11))
                    ||(file.getAbsolutePath().endsWith(ext12))||(file.getAbsolutePath().endsWith(ext13))) {
                fileList.add(file.getPath());
            }
        }
        for(File file : files) {
            if((file.isDirectory())||(file.getAbsolutePath().endsWith(ext1))||(file.getAbsolutePath().endsWith(ext2))||(file.getAbsolutePath().endsWith(ext3))
                    ||(file.getAbsolutePath().endsWith(ext4))||(file.getAbsolutePath().endsWith(ext5))||(file.getAbsolutePath().endsWith(ext6))||(file.getAbsolutePath().endsWith(ext7))
                    ||(file.getAbsolutePath().endsWith(ext8))||(file.getAbsolutePath().endsWith(ext9))||(file.getAbsolutePath().endsWith(ext10))||(file.getAbsolutePath().endsWith(ext11))
                    ||(file.getAbsolutePath().endsWith(ext12))||(file.getAbsolutePath().endsWith(ext13))) {
                falsefilelist.add(file.getPath().substring(file.getAbsolutePath().lastIndexOf("/") + 1, file.getAbsolutePath().length()));
            }
        }

        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, falsefilelist);
        dialog_ListView.setAdapter(directoryList);

    }

}