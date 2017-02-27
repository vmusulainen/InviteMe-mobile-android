package com.mva.inviteme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private ListView mListView;

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = new Bundle();
            Invite invite = intent.getExtras().getParcelable("invite");
            bundle.putParcelable("invite", invite);
            NewInviteDialogFragment dialogFragment = new NewInviteDialogFragment();
            dialogFragment.setArguments(bundle);
            dialogFragment.show(getFragmentManager(), "MyDF");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.invite_list_view);
        final ArrayList<Invite> inviteList = new ArrayList<Invite>();
        inviteList.add(new Invite("id", "point A id", "Point A", "Point A address", "Text A", 60.0, 30.0));
        inviteList.add(new Invite("id", "point B id", "Point B", "Point B address", "Text B", 60.0, 30.0));

        InviteAdapter adapter = new InviteAdapter(this, inviteList);
        mListView.setAdapter(adapter);


//        Intent intent = getIntent();
//        Log.i(TAG, intent.toString());
//        if (intent.getExtras() != null) {
//            Log.i(TAG, "I have got a notifiction");
//            String id = intent.getExtras().getString("id");
//            String pointId = intent.getExtras().getString("pointId");
//            String pointName = intent.getExtras().getString("pointName");
//            String pointAddress = intent.getExtras().getString("pointAddress");
//            String text = intent.getExtras().getString("text");
//            Double latitude = intent.getExtras().getDouble("latitude");
//            Double longitude = intent.getExtras().getDouble("longitude");
//            Invite invite = new Invite(id, pointId, pointName, pointAddress, text, latitude, longitude);
//            this.showInvite(invite);
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Register mMessageReceiver to receive messages.
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("newInvite"));
    }

    // handler for received Intents for the "my-event" event


    @Override
    protected void onPause() {
        // Unregister since the activity is not visible
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onPause();
    }

    public void showInvite(Invite invite) {
        Log.i(TAG, "showing invite");
        Intent intent = new Intent(this, InviteActivity.class);
        intent.putExtra("invite", invite);
        startActivity(intent);
    }

    public void dismissInvite(Invite invite) {
        Log.i(TAG, "dismissing invite");
    }


}
