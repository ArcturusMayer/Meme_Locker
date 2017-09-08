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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arcturus Mayer on 27.08.2017.
 */

public class editDialog extends DialogFragment {

    LockScreenAppActivity loc = new LockScreenAppActivity();

    Button buttonUp;
    TextView textFolder;
    ListView dialog_ListView;
    AlertDialog diag;

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

    private EditText mEditText;

    public editDialog() {

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
                    filepath = selected.getAbsolutePath();
                    String f = mEditText.getText().toString();
                    ((LockScreenAppActivity) getActivity()).onClicked(f,filepath);
                    diag.cancel();
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
            fileList.add(file.getPath());
        }
        for(File file : files) {
            falsefilelist.add(file.getPath().substring(file.getAbsolutePath().lastIndexOf("/")+1, file.getAbsolutePath().length()));
        }

        ArrayAdapter<String> directoryList = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, falsefilelist);
        dialog_ListView.setAdapter(directoryList);

    }

}