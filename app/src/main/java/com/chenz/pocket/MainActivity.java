package com.chenz.pocket;

import android.os.Bundle;

import com.chenz.pocket.base.BaseActivity;
import com.chenz.pocket.base.DataBindingConfig;
import com.chenz.pocket.databinding.ActivityMainBinding;
import com.chenz.pocket.ui.main.PageViewModel;
import com.chenz.pocket.ui.main.TabInfo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chenz.pocket.ui.main.SectionsPagerAdapter;

public class MainActivity extends BaseActivity {

    private PageViewModel        mPageViewModel;
    private SectionsPagerAdapter mAdapter;

    @Override
    protected void initViewModel() {
        mPageViewModel = getActivityViewModel(PageViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        mAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        return new DataBindingConfig(R.layout.activity_main, mPageViewModel)
                .addBindingParam(BR.adapter, mAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = (ActivityMainBinding) mBinding;
        binding.tabs.setSelectedTabIndicatorHeight(0);
        binding.tabs.setupWithViewPager(binding.viewPager);
        mPageViewModel.getTabs().observe(this, tabInfo -> {
            mAdapter.setTabInfo(tabInfo);
            for (int i = 0; i < binding.tabs.getTabCount(); i++) {
                TabLayout.Tab tab = binding.tabs.getTabAt(i);
                if (tab != null) {
                    tab.setCustomView(mAdapter.getTabView(i));
                }
            }
        });
        mPageViewModel.initTabInfo();

    }
}