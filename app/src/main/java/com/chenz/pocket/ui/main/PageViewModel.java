package com.chenz.pocket.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private static final String TAG = "PageViewModel";

    private MutableLiveData<TabInfo> mTabs  = new MutableLiveData<>();
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    public  LiveData<String>         mText  = Transformations.map(mIndex, input -> "Hello world from section: " + input);

    public void setTabs(TabInfo tabs) {
        mTabs.setValue(tabs);
    }

    public LiveData<TabInfo> getTabs() {

        return mTabs;
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

}