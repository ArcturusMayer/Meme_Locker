package com.arcturusmayer.memescreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

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
public class NewCollectionDialog extends DialogFragment {

    EditText collectionName;
    Button addAction;

    public  NewCollectionDialog(){

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.collection_dialog_layout,(ViewGroup) getActivity().findViewById(R.id.layout));
        addAction = view.findViewById(R.id.addcol);
        collectionName = view.findViewById(R.id.colname);
        builder.setView(view);
        final Dialog diag = builder.create();
        addAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ((CollectionOutsideActivity)getActivity()).addCollection((collectionName.getText()).toString());
                    diag.cancel();
            }
        });
        return diag;
    }
}
