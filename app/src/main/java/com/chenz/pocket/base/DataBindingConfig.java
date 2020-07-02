package com.chenz.pocket.base;

import android.util.SparseArray;

import androidx.lifecycle.ViewModel;

/**
 * description: <一句话功能简述>
 *
 * @author Chenz
 * @date 2020/7/1
 */
public class DataBindingConfig {

    private int                 layout;
    private ViewModel           stateViewModel;
    private SparseArray<Object> bindingParams = new SparseArray<>();

    public DataBindingConfig(int layout, ViewModel stateViewModel) {
        this.layout = layout;
        this.stateViewModel = stateViewModel;
    }

    public int getLayout() {
        return layout;
    }

    public ViewModel getStateViewModel() {
        return stateViewModel;
    }

    public SparseArray<Object> getBindingParams() {
        return bindingParams;
    }

    public DataBindingConfig addBindingParam(int variableId, Object object) {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, object);
        }
        return this;
    }
}
