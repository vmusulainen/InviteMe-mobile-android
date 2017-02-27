package com.mva.inviteme;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

public class AppService extends IntentService {

    private static final String TAG = "AppService";
    private static final String registeringOnServerAction = "com.inviteme.action.REGISTER_ON_SERVER";
    private static final String updatingLocationOnServerAction = "com.inviteme.action.UPDATE_LOCATION_ON_SERVER";

    public AppService() {
        super(TAG);
    }

    public static void registerOnServer(Context context) {
        Intent intent = new Intent(context, AppService.class);
        intent.setAction(registeringOnServerAction);
        context.startService(intent);
    }

    public static void updateLocationOnServer(Context context, Location location) {
        Intent intent = new Intent(context, AppService.class);
        intent.setAction(updatingLocationOnServerAction);
        intent.putExtra("latitude", location.getLatitude());
        intent.putExtra("longitude", location.getLongitude());
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        final String action = intent.getAction();
        Log.i(TAG, "onHandleIntent");
        if (action.equals(registeringOnServerAction)) {
            RegisterDevice.run(this);
        }
        if (action.equals(updatingLocationOnServerAction)) {
            Double latitude = intent.getDoubleExtra("latitude", 0.0d);
            Double longitude = intent.getDoubleExtra("longitude", 0.0d);
            UpdateLocation.run(this, latitude, longitude);
        }
    }

}
