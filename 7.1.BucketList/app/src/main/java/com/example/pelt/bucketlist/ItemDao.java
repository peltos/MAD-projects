package com.example.pelt.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM item")
    public LiveData<List<Item>> getAllItems();

    @Insert
    public void insertItem(Item reminders);

    @Delete
    public void deleteItem(Item reminders);

    @Update
    public void updateItem(Item reminders);
}
