package com.mva.inviteme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InviteActivity extends FragmentActivity implements OnMapReadyCallback {

    private final String TAG = "InviteActivity";
    private GoogleMap mMap;
    private Invite invite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.invite = getIntent().getExtras().getParcelable("invite");
        setContentView(R.layout.activity_invite);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        TextView pointNameTextView = (TextView)findViewById(R.id.point_name);
        pointNameTextView.setText(invite.getPointName());
        TextView inviteTextTextView = (TextView)findViewById(R.id.invite_text);
        inviteTextTextView.setText(invite.getText());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        LocationManager mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
//
//        List<String> providers = mLocationManager.getProviders(true);
//        Location userLocation = null;
//        for (String provider : providers) {
//            Location l = mLocationManager.getLastKnownLocation(provider);
//            if (l == null) {
//                continue;
//            }
//            if (userLocation == null || l.getAccuracy() < userLocation.getAccuracy()) {
//                // Found best last known location: %s", l);
//                userLocation = l;
//            }
//        }
//
//        LatLng userPosition = new LatLng(userLocation.getPointLatitude(), userLocation.getPointLongitude());
//        Log.i(TAG, userPosition.toString());
//        mMap.addMarker(new MarkerOptions().position(userPosition).title("You"));

        LatLng hostPosition = new LatLng(this.invite.getPointLatitude(), this.invite.getPointLongitude());
        Log.i(TAG, hostPosition.toString());
        mMap.addMarker(new MarkerOptions().position(hostPosition).title(this.invite.getPointName()));

        CameraUpdate center = CameraUpdateFactory.newLatLng(hostPosition);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
    }

    public void acceptInvite(View view){
        this.invite.accepted();
        DatabaseHandler db = new DatabaseHandler(this);
        db.addInvite(this.invite);
    }

    public void showPoint(View view){
        Log.i(TAG, "showing point");
        Intent intent = new Intent(this, PointActivity.class);
        intent.putExtra("invite", invite);
        startActivity(intent);
    }

}