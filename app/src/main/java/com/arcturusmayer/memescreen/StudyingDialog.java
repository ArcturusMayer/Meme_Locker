package com.arcturusmayer.memescreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Arcturus Mayer on 05.11.2017.
 */

public class StudyingDialog extends DialogFragment {

    Button ok;

    public StudyingDialog(){
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.study_dialog_layout, (ViewGroup) getActivity().findViewById(R.id.layout));
        builder.setView(view);
        final Dialog diag = builder.create();
        ok = view.findViewById(R.id.btnok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diag.cancel();
            }
        });
        return diag;
    }
}
