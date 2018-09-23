package com.example.pelt.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

public class Portal implements Parcelable {

    private String mUrl;
    private String mTitle;

    public Portal(String mUrl, String mTitle) {

        this.mUrl = mUrl;
        this.mTitle = mTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public String toString() { return mUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUrl); dest.writeString(this.mTitle);
    }

    protected Portal(Parcel in) {
        this.mUrl = in.readString();
        this.mTitle = in.readString();
    }

    public static final Creator<Portal> CREATOR = new Creator<Portal>() {
        @Override
        public Portal createFromParcel(Parcel source) {
            return new Portal(source);
        }

        @Override
        public Portal[] newArray(int size) {
            return new Portal[size];
        }
    };
}
