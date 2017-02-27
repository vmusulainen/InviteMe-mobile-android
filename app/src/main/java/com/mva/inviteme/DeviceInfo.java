package com.mva.inviteme;

import android.content.Context;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class DeviceInfo {

    Context context;

    public DeviceInfo(Context context) {
        this.context = context.getApplicationContext();
    }

    public String uuid() {
        //TODO: extract getting/setting/creation of intallation id to separate class?
        String value = Utilites.getInstallationId(context);
        if (value == null) {
            value = UUID.randomUUID().toString();
            Utilites.setInstallationId(this.context, value);
        }
        return value;
    }

    public String model() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String aString) {
        if (aString == null || aString.length() == 0) {
            return "";
        }
        return aString.substring(0, 1).toUpperCase() + aString.substring(1);
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("uuid", this.uuid());
            json.put("model", this.model());
            json.put("platform", "Android");
            json.put("appType", "native");
            json.put("version", Build.VERSION.RELEASE);
            return json;
        } catch (JSONException e) {
            return json;
        }
    }
}
