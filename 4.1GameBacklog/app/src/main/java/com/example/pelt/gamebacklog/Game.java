package com.example.pelt.gamebacklog;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.CardView;

@Entity(tableName = "game")
public class Game implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo (name = "gametext")
    private String mGameText;
    @ColumnInfo (name = "gameplatform")
    private String mGamePlatform;
    @ColumnInfo (name = "gamedate")
    private String mGameDate;
    @ColumnInfo (name = "gamestatus")
    private String mGameStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGamePlatform() {
        return mGamePlatform;
    }

    public void setGamePlatform(String mGamePlatform) {
        this.mGamePlatform = mGamePlatform;
    }

    public String getGameDate() {
        return mGameDate;
    }

    public void setGameDate(String mGameDate) {
        this.mGameDate = mGameDate;
    }

    public String getGameStatus() {
        return mGameStatus;
    }

    public void setGameStatus(String mGameStatus) {
        this.mGameStatus = mGameStatus;
    }

    public String getGameText() {
        return mGameText;
    }

    public void setGameText(String mGameText) {
        this.mGameText = mGameText;
    }

    public Game(String mGameText, String mGamePlatform, String mGameDate, String mGameStatus) {
        this.mGameText = mGameText;
        this.mGamePlatform = mGamePlatform;
        this.mGameDate = mGameDate;
        this.mGameStatus = mGameStatus;
    }

    @Override
    public String toString() {
        return mGameText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.mGameText);
        dest.writeString(this.mGamePlatform);
        dest.writeString(this.mGameDate);
        dest.writeString(this.mGameStatus);
    }

    protected Game(Parcel in) {
        this.id = in.readLong();
        this.mGameText = in.readString();
        this.mGamePlatform = in.readString();
        this.mGameDate = in.readString();
        this.mGameStatus = in.readString();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}