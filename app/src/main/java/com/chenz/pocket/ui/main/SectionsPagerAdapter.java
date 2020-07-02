package com.chenz.pocket.ui.main;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;

import com.chenz.pocket.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG         = "SectionsPagerAdapter";

    private final Context mContext;

    private TabInfo mTabInfo = new TabInfo();

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(mTabInfo.getTabTitles()[position]);
    }

    @Override
    public int getCount() {
        return mTabInfo.length();
    }

    public void setTabInfo(TabInfo tabInfo) {
        mTabInfo = tabInfo;
        Log.d(TAG, "setTabInfo: PageViewModel"+mTabInfo.getTabTitles().length);
        notifyDataSetChanged();
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tab_layout, null);
        ((ImageView) view.findViewById(R.id.iv)).setImageResource(mTabInfo.getTabIcons()[position]);
        ((TextView) view.findViewById(R.id.tv)).setText(mTabInfo.getTabTitles()[position]);
        return view;
    }
}