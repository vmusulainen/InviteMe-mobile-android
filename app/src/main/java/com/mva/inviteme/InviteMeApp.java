package com.mva.inviteme;

import android.app.Application;
import android.content.Intent;
import android.util.Log;


public class InviteMeApp extends Application {

    private static final String TAG = "InviteMeApp";

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");
        super.onCreate();
        //Start tracking location
        Intent intent = new Intent(this, LocationService.class);
        this.startService(intent);
    }
}
