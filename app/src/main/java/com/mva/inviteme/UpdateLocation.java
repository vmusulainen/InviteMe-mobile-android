package com.mva.inviteme;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UpdateLocation {

    private static final String TAG = "UpdateLocation";
    private static final String localDateFormat = "yyyy-MMM-dd HH:mm:ss.SSS";
    private static final String jsonDateFormat = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'";

    public static void run(Context context, Double latitude, Double longitude) {
        try {
            Log.i(TAG, "run");

            DeviceInfo deviceInfo = new DeviceInfo(context);
            String deviceId = deviceInfo.uuid();

            Date localDate = new Date();
            Date utcDate = null;
            SimpleDateFormat dateFormatGmt = new SimpleDateFormat(localDateFormat);
            dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
            SimpleDateFormat dateFormatLocal = new SimpleDateFormat(localDateFormat);
            utcDate = dateFormatLocal.parse(dateFormatGmt.format(localDate));
            SimpleDateFormat dateFormatJson = new SimpleDateFormat(jsonDateFormat);

            JSONObject json = new JSONObject();
            json.put("uuid", deviceId);
            json.put("latitude", latitude);
            json.put("longitude", longitude);
            json.put("timestamp", dateFormatJson.format(utcDate));

            Log.i(TAG, json.toString());

            URL url = new URL(Utilites.updatingLocationOnServerUrl(context, deviceId));
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
        } catch (ParseException | JSONException | IOException e) {
            Log.e(TAG, e.toString());
            throw new RuntimeException(e.toString());
        }
    }
}
