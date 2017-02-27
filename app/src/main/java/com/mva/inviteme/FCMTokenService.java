package com.mva.inviteme;

import android.util.Log;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FCMTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "FCMTokenService";

    @Override
    public void onTokenRefresh() {
        Log.i(TAG, "Firebase token is refreshed");
        String newToken = FirebaseInstanceId.getInstance().getToken();
        Utilites.setFirebaseToken(this, newToken);
        AppService.registerOnServer(this);
    }

}
