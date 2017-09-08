package com.arcturusmayer.memescreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Arcturus Mayer on 26.08.2017.
 */

public class lockScreenReciver extends BroadcastReceiver {

    public static boolean wasScreenOn = true;



    @Override
    public void onReceive(Context context, Intent intent) {


        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            wasScreenOn = false;
            Intent intent11 = new Intent(context, LockScreenAppActivity.class);
            intent11.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(intent11);
        }
    }
}
