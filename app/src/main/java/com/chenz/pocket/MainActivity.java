package com.chenz.pocket;

import android.os.Bundle;

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

public class MainActivity extends AppCompatActivity {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_3, R.string.tab_text_3};
    @DrawableRes
    private static final int[] TAB_ICONS = new int[]{R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};
    private static final String TAG         = "MainActivity";


    private PageViewModel mPageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setSelectedTabIndicatorHeight(0);
        tabs.setupWithViewPager(viewPager);
        mPageViewModel.getTabs().observe(this, tabInfo -> {
            sectionsPagerAdapter.setTabInfo(tabInfo);
            for (int i = 0; i < tabs.getTabCount(); i++) {
                TabLayout.Tab tab = tabs.getTabAt(i);
                if (tab != null) {
                    tab.setCustomView(sectionsPagerAdapter.getTabView(i));
                }
            }
        });
        TabInfo tabInfo = new TabInfo();
        tabInfo.setTabIcons(TAB_ICONS);
        tabInfo.setTabTitles(TAB_TITLES);
        mPageViewModel.setTabs(tabInfo);

    }
}