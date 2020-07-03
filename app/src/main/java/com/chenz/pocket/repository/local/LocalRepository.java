package com.chenz.pocket.repository.local;

import com.chenz.pocket.R;
import com.chenz.pocket.repository.DataRepository;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 * description: <一句话功能简述>
 *
 * @author Chenz
 * @date 2020/7/3
 */
public class LocalRepository {

    private final static LocalRepository S_LOCAL_REPOSITORY = new LocalRepository();

    private LocalRepository() {
    }

    public static LocalRepository getInstance() {
        return S_LOCAL_REPOSITORY;
    }

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_3, R.string.tab_text_3};
    @DrawableRes
    private static final int[] TAB_ICONS  = new int[]{R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};


    public static int[] getTabTitles() {
        return TAB_TITLES;
    }

    public static int[] getTabIcons() {
        return TAB_ICONS;
    }
}
