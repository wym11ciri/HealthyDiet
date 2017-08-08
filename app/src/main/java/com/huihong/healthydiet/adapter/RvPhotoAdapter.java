package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 支付详情页面图片集合
 */

public class RvPhotoAdapter extends RecyclerView.Adapter<RvDetailRecipesViewHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;
    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }

    public RvPhotoAdapter(Context pContext, List<String> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RvDetailRecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_photo_item2, parent, false);
        return new RvDetailRecipesViewHolder(mView);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvDetailRecipesViewHolder holder, int position) {
        Glide
                .with(mContext)
                .load(mList.get(position))
                .asBitmap()
                .error(R.mipmap.error_photo)
                .into(holder.ivHead);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

