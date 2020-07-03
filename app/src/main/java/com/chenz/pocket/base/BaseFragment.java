package com.chenz.pocket.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenz.pocket.BR;
import com.chenz.pocket.app.MyApplication;
import com.chenz.pocket.base.event.SharedViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by chenz on 2017/3/30.
 */
public abstract class BaseFragment extends Fragment {

    private static final Handler           HANDLER = new Handler();
    protected            AppCompatActivity mActivity;
    private              SharedViewModel   mSharedViewModel;
    private              ViewModelProvider mActivityProvider;
    private              ViewModelProvider mFragmentProvider;
    protected               ViewDataBinding mBinding;

    protected abstract void initViewModel();

    protected abstract DataBindingConfig getDataBindingConfig();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedViewModel = ((MyApplication) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity).get(SharedViewModel.class);
        initViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DataBindingConfig dataBindingConfig = getDataBindingConfig();
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, dataBindingConfig.getLayout(), container, false);
        binding.setLifecycleOwner(this);
        binding.setVariable(BR.vm, dataBindingConfig.getStateViewModel());
        SparseArray<Object> bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0; i < bindingParams.size(); i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        mBinding = binding;
        return binding.getRoot();
    }

    protected void startActivity(Class<? extends BaseActivity> toActivity) {
        startActivity(toActivity, null);
    }

    protected void startActivity(Class<? extends BaseActivity> toActivity, Bundle parameters) {
        Intent intent = new Intent(getActivity(), toActivity);
        if (parameters != null) {
            intent.putExtras(parameters);
        }
        startActivity(intent);
    }

    protected <T extends ViewModel> T getFragmentViewModel(Class<T> classModel) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this);
        }
        return mFragmentProvider.get(classModel);
    }

    protected <T extends ViewModel> T getActivityViewModel(Class<T> classModel) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this);
        }
        return mActivityProvider.get(classModel);
    }

    public SharedViewModel getSharedViewModel() {
        return mSharedViewModel;
    }
}
