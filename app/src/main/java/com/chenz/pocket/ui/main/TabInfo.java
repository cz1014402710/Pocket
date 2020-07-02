package com.chenz.pocket.ui.main;

/**
 * description: <一句话功能简述>
 *
 * @author Chenz
 * @date 2020/6/22
 */
public class TabInfo {

    private int[] mTabTitles;
    private int[] mTabIcons;

    public int[] getTabTitles() {
        return mTabTitles;
    }

    public void setTabTitles(int[] tabTitles) {
        mTabTitles = tabTitles;
    }

    public int[] getTabIcons() {
        return mTabIcons;
    }

    public void setTabIcons(int[] tabIcons) {
        mTabIcons = tabIcons;
    }

    public int length() {
        if (mTabTitles == null || mTabIcons == null) {
            return 0;
        } else if (mTabTitles.length != mTabIcons.length) {
            return Math.min(mTabTitles.length, mTabIcons.length);
        } else {
            return mTabTitles.length;
        }
    }
}
