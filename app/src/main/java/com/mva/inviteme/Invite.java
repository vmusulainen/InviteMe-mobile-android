package com.mva.inviteme;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Invite implements Parcelable {
    private String id;
    private String pointId;
    private String pointName;
    private String pointAddress;
    private String text;
    private Double pointLatitude;
    private Double pointLongitude;
    private Date acceptedAt;

    public Invite(String id, String pointId, String pointName, String pointAddress, String text, Double lat, Double lon) {
        this.id = id;
        this.pointId = pointId;
        this.pointName = pointName;
        this.pointAddress = pointAddress;
        this.text = text;
        this.pointLatitude = lat;
        this.pointLongitude = lon;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.id);
        out.writeString(this.pointId);
        out.writeString(this.pointName);
        out.writeString(this.pointAddress);
        out.writeString(this.text);
        out.writeDouble(this.pointLatitude);
        out.writeDouble(this.pointLongitude);
    }

    public static final Parcelable.Creator<Invite> CREATOR
            = new Parcelable.Creator<Invite>() {
        public Invite createFromParcel(Parcel in) {
            return new Invite(in);
        }

        public Invite[] newArray(int size) {
            return new Invite[size];
        }
    };

    private Invite(Parcel in) {
        this.id = in.readString();
        this.pointId = in.readString();
        this.pointName = in.readString();
        this.pointAddress = in.readString();
        this.text = in.readString();
        this.pointLatitude = in.readDouble();
        this.pointLongitude = in.readDouble();
    }

    public void setPointName(String aString) {
        this.pointName = aString;
    }

    public String getPointName() {
        return this.pointName;
    }

    public void setText(String aString) {
        this.text = aString;
    }

    public String getText() {
        return this.text;
    }

    public void setPointLatitude(Double lat) {
        this.pointLatitude = lat;
    }

    public Double getPointLatitude() {
        return this.pointLatitude;
    }

    public void setPointLongitude(Double lon) {
        this.pointLongitude = lon;
    }

    public Double getPointLongitude() {
        return this.pointLongitude;
    }

    public String getPointAddress() {
        return pointAddress;
    }

    public void setPointAddress(String pointAddress) {
        this.pointAddress = pointAddress;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getAcceptedAt() {
        return acceptedAt;
    }

    public void accepted() {
        this.acceptedAt = new Date();
    }
}
