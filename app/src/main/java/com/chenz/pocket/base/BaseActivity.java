package com.chenz.pocket.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;

import com.chenz.pocket.app.ActivityStack;
import com.chenz.pocket.app.MyApplication;
import com.chenz.pocket.base.event.SharedViewModel;
import com.chenz.pocket.utils.ToastUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


/**
 * Created by chenz on 2017/3/30.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();

    private SharedViewModel   mSharedViewModel;
    protected ViewDataBinding   mBinding;
    private ViewModelProvider mActivityProvider;

    protected abstract void initViewModel();

    protected abstract DataBindingConfig getDataBindingConfig();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(this);

        mSharedViewModel = ((MyApplication) getApplicationContext()).getAppViewModelProvider(this).get(SharedViewModel.class);
        initViewModel();
        DataBindingConfig dataBindingConfig = getDataBindingConfig();

        ViewDataBinding binding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        binding.setLifecycleOwner(this);
        binding.setVariable(BR.vm, dataBindingConfig.getStateViewModel());
        SparseArray<Object> bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0; i < bindingParams.size(); i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        mBinding = binding;

    }

    /**
     * Called when activity start-up is complete (after onStart() and onRestoreInstanceState(Bundle) have been called).
     *
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    protected void startActivity(Class<? extends BaseActivity> toActivity) {
        startActivity(toActivity, null);
    }

    protected void startActivity(Class<? extends BaseActivity> toActivity, Bundle parameters) {
        Intent intent = new Intent(this, toActivity);
        if (parameters != null) {
            intent.putExtras(parameters);
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getInstance().finishActivity(this);
    }

    protected void setToolbar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    public void showToast(final String msg) {
        runOnUiThread(() -> ToastUtils.showToast(getApplicationContext(), msg));
    }

    public void showToast(int msgid) {
        showToast(getString(msgid));
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
