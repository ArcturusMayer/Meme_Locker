package com.arcturusmayer.memescreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

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
