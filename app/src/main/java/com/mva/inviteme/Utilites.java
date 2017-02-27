package com.mva.inviteme;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Utilites {
    public static final String TAG = "Utilites";

    public static void setFirebaseToken(Context context, String token) {
        Log.i(TAG, "Writting Firebase token to sharedPreferences");
        Log.i(TAG, "Written Firebase token is: " + token);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        String key = context.getString(R.string.fcm_token_key);
        editor.putString(key, token);
        editor.apply();
    }

    public static String getFirebaseToken(Context context) {
        Log.i(TAG, "Reading Firebase token from sharedPreferences");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String key = context.getString(R.string.fcm_token_key);
        String value = sharedPreferences.getString(key, null);
        Log.i(TAG, "Readed Firebase token is: " + value);
        return value;
    }

    public static void setInstallationId(Context context, String installationId) {
        Log.i(TAG, "Writing installation id to sharedPreferences");
        Log.i(TAG, "Written installation id is: " + installationId);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();
        String key = context.getString(R.string.installation_id_key);
        editor.putString(key, installationId);
        editor.apply();
    }

    public static String getInstallationId(Context context) {
        Log.i(TAG, "Reading installation id from sharedPreferences");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String key = context.getString(R.string.installation_id_key);
        String value = sharedPreferences.getString(key, null);
        Log.i(TAG, "Readed installation id is: " + value);
        return value;
    }

    public static String serverUrl(Context context){
        Log.i(TAG, "Reading server URL from sharedPreferences");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = context.getString(R.string.server_url);
        Log.i(TAG, "Readed server URL is: " + value);
        return value;
    }

    public static String registeringOnServerUrl(Context context, String deviceId){
        Log.i(TAG, "Reading registering device URL from sharedPreferences");
        String serverUrl = Utilites.serverUrl(context);
        String url = serverUrl + "/devices/" + deviceId;
        Log.i(TAG, url);
        return url;
    }

    public static String updatingLocationOnServerUrl(Context context, String deviceId){
        Log.i(TAG, "Reading registering device URL from sharedPreferences");
        String serverUrl = Utilites.serverUrl(context);
        String url = serverUrl + "/devices/" + deviceId + "/location";
        Log.i(TAG, url);
        return url;
    }
}
