package com.mva.inviteme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PointActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);

        Invite invite = getIntent().getExtras().getParcelable("invite");
        TextView pointNameTextView = (TextView)findViewById(R.id.point_name);
        pointNameTextView.setText(invite.getPointName());
        TextView addressTextView = (TextView)findViewById(R.id.point_address);
        addressTextView.setText(invite.getPointAddress());
    }
}
