package com.mva.inviteme;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "inviteMe";
    private static final String TABLE_INVITES = "invites";

    private static final String INVITES_ID = "id";
    private static final String INVITES_POINT_ID = "point_id";
    private static final String INVITES_POINT_NAME = "point_name";
    private static final String INVITES_POINT_ADDRESS = "point_address";
    private static final String INVITES_TEXT = "text";
    private static final String INVITES_POINT_LATITUDE = "point_latitude";
    private static final String INVITES_POINT_LONGITUDE = "point_longitude";
    private static final String INVITES_ACCEPTED_AT = "accepted_at";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createInviteTableStatement =
                "CREATE TABLE " + TABLE_INVITES + "("
                        + INVITES_ID + " STRING PRIMARY KEY,"
                        + INVITES_POINT_ID + " TEXT,"
                        + INVITES_POINT_NAME + " TEXT,"
                        + INVITES_POINT_ADDRESS + " TEXT,"
                        + INVITES_TEXT + " TEXT,"
                        + INVITES_POINT_LATITUDE + " REAL,"
                        + INVITES_POINT_LONGITUDE + " REAL,"
                        + INVITES_ACCEPTED_AT + " INTEGER"
                        + ")";
        db.execSQL(createInviteTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVITES);

        // Create tables again
        onCreate(db);
    }

    public void addInvite(Invite invite) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INVITES_ID, invite.getId());
        values.put(INVITES_POINT_ID, invite.getPointId());
        values.put(INVITES_POINT_NAME, invite.getPointName());
        values.put(INVITES_POINT_ADDRESS, invite.getPointAddress());
        values.put(INVITES_TEXT, invite.getText());
        values.put(INVITES_POINT_LATITUDE, invite.getPointLatitude());
        values.put(INVITES_POINT_LONGITUDE, invite.getPointLongitude());
        values.put(INVITES_ACCEPTED_AT, invite.getAcceptedAt().getTime());

        db.insert(TABLE_INVITES, null, values);
        db.close();
    }

}

