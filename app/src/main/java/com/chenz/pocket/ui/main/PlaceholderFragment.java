package com.chenz.pocket.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chenz.pocket.BR;
import com.chenz.pocket.R;
import com.chenz.pocket.base.BaseFragment;
import com.chenz.pocket.base.DataBindingConfig;
import com.chenz.pocket.bean.GoodsBean;
import com.chenz.pocket.databinding.FragmentMainBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends BaseFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initViewModel() {
        pageViewModel = getFragmentViewModel(PageViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_main,pageViewModel)
                .addBindingParam(BR.vm, pageViewModel)
                .addBindingParam(BR.activity, getActivity());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentMainBinding binding = (FragmentMainBinding) mBinding;
        binding.button.setOnClickListener(v -> pageViewModel.getGoodsDetail("6902538005141"));

        pageViewModel.getGoodsBean().observe(getActivity(), goodsBean -> binding.text.setText(goodsBean.toString()));

    }
}