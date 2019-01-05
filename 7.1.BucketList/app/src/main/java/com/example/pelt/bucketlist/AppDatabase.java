package com.example.pelt.bucketlist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ItemDao ItemDao();

    private final static String NAME_DATABASE = "Item_db";

    private static AppDatabase sInstance;

    public static AppDatabase getsInstance(Context context) {

        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, AppDatabase.class, NAME_DATABASE
            ).build();
        }
        return sInstance;
    }
}
