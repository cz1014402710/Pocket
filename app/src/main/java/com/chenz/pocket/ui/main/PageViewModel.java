package com.chenz.pocket.ui.main;

import android.util.Log;

import com.chenz.pocket.bean.GoodsBean;
import com.chenz.pocket.repository.DataRepository;
import com.chenz.pocket.repository.remote.RemoteRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void initTabInfo() {
        DataRepository.getInstance().getTabInfo(mTabs);
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }


    public MutableLiveData<GoodsBean> mGoodsBeanMutableLiveData = new MutableLiveData<>();

    public LiveData<GoodsBean> getGoodsBean() {

        return mGoodsBeanMutableLiveData;
    }

    public void getGoodsDetail(String barcode) {
        RemoteRepository.getInstance().getGoodsDetail(barcode).enqueue(new Callback<GoodsBean>() {
            @Override
            public void onResponse(Call<GoodsBean> call, Response<GoodsBean> response) {
                GoodsBean goodsBean = response.body();
                if (goodsBean.data != null) {
                    mGoodsBeanMutableLiveData.setValue(goodsBean);
                } else {
                    Log.d(TAG, "onResponse: body is null");
                }
            }

            @Override
            public void onFailure(Call<GoodsBean> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}