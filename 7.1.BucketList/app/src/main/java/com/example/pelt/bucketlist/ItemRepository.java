package com.example.pelt.bucketlist;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ItemRepository {

    private ItemDao mItemListDao;
    private LiveData<List<Item>> mItemsList;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    public ItemRepository(Context context) {
        mItemListDao = AppDatabase.getsInstance(context).ItemDao();
        mItemsList = mItemListDao.getAllItems();
    }

    public LiveData<List<Item>> getAllItems() {
        return mItemsList;
    }

    public void insert(final Item item) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mItemListDao.insertItem(item);
            }
        });
    }

    public void update(final Item item) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mItemListDao.updateItem(item);
            }
        });
    }

    public void delete(final Item item) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mItemListDao.deleteItem(item);
            }
        });
    }
}
