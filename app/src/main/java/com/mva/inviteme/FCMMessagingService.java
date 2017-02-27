package com.mva.inviteme;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FCMMessagingService extends FirebaseMessagingService {

    private static final String TAG = "InviteMeMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i(TAG, "From: " + remoteMessage.getFrom());
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.i(TAG, "Message data payload: " + remoteMessage.getData());
        }

        String id = remoteMessage.getData().get("inviteId");
        String pointId = remoteMessage.getData().get("pointId");
        String pointName = remoteMessage.getData().get("pointName");
        String pointAddress = remoteMessage.getData().get("pointAddress");
        String text = remoteMessage.getData().get("text");
        Double latitude = Double.valueOf(remoteMessage.getData().get("lat"));
        Double longitude = Double.valueOf(remoteMessage.getData().get("lon"));

        Invite invite = new Invite(id, pointId, pointName, pointAddress, text, latitude, longitude);

        DatabaseHandler db = new DatabaseHandler(this);
        db.addInvite(invite);
    }
}
