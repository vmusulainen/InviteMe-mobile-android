package com.mva.inviteme;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterDevice {
    private static final String TAG = "RegisterDevice";

    public static void run(Context context) {
        String token = Utilites.getFirebaseToken(context);
        try {
            Log.i(TAG, "run");

            DeviceInfo deviceInfo = new DeviceInfo(context);
            String deviceId = deviceInfo.uuid();
            JSONObject json = deviceInfo.toJson();
            json.put("fcmToken", token);
            Log.i(TAG, json.toString());

            URL url = new URL(Utilites.registeringOnServerUrl(context, deviceId));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(json.toString());
            out.flush();
            out.close();
            String response = connection.getResponseMessage();
            Log.i(TAG, response);
        } catch (JSONException | IOException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e.toString());
        }
    }
}
