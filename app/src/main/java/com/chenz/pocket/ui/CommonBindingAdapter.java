package com.chenz.pocket.ui;

import androidx.databinding.BindingAdapter;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * description: <一句话功能简述>
 *
 * @author Chenz
 * @date 2020/7/2
 */
public class CommonBindingAdapter {

    @BindingAdapter(value = {"adapter"}, requireAll = false)
    public static void adapter(ViewPager viewPager, FragmentPagerAdapter fragmentPagerAdapter) {
        viewPager.setAdapter(fragmentPagerAdapter);

    }

}
