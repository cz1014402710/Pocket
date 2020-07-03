package com.chenz.pocket.repository;

import com.chenz.pocket.repository.local.LocalRepository;
import com.chenz.pocket.ui.main.TabInfo;

import androidx.lifecycle.MutableLiveData;

/**
 * description: <DataRepository>
 *
 * @author Chenz
 * @date 2020/7/3
 */
public class DataRepository {

    private final static DataRepository S_DATA_REPOSITORY = new DataRepository();

    private DataRepository() {
    }

    public static DataRepository getInstance() {
        return S_DATA_REPOSITORY;
    }

    public void getTabInfo(MutableLiveData<TabInfo> liveData) {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        liveData.setValue(new TabInfo(LocalRepository.getTabTitles(), LocalRepository.getTabIcons()));
    }

}
