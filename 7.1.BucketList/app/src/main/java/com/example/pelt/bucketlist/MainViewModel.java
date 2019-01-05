package com.example.pelt.bucketlist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

public class MainViewModel extends ViewModel {

    private ItemRepository mItemListRepository;
    private LiveData<List<Item>> mItemsList;

    public MainViewModel(Context context) {
        this.mItemListRepository = new ItemRepository(context);
        this.mItemsList = mItemListRepository.getAllItems();
    }

    public LiveData<List<Item>> getAllItems() {
        return mItemsList;
    }

    public void insert(Item item) {
        mItemListRepository.insert(item);
    }

    public void update(Item item) {
        mItemListRepository.update(item);
    }

    public void delete(Item item) {
        mItemListRepository.delete(item);
    }
}
