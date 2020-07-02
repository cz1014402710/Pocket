package com.chenz.pocket.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * description: <一句话功能简述>
 *
 * @author Chenz
 * @date 2017/11/6
 */
public abstract class BaseDataBindingAdapter<T> extends RecyclerView.Adapter<BaseDataBindingAdapter.DataBindingViewHolder> {

    protected Context        mContext;
    protected int            mLayoutId;
    protected List<T>        mDatas;
    protected LayoutInflater mInflater;
    private   int            mVariableId;

    public BaseDataBindingAdapter(Context context, int layoutId, List<T> datas, int variableId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
        mVariableId = variableId;
    }


    @NonNull
    @Override
    public DataBindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(mInflater, mLayoutId, parent, false);
        DataBindingViewHolder holder = new DataBindingViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataBindingViewHolder holder, int position) {
        holder.mBinding.setVariable(mVariableId, mDatas.get(position));
        holder.mBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setDatas(List<T> datas) {
        mDatas = datas;
//        notifyDataSetChanged();
    }

    public static class DataBindingViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;

        public DataBindingViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setBinding(ViewDataBinding binding) {
            mBinding = binding;
        }
    }

}
