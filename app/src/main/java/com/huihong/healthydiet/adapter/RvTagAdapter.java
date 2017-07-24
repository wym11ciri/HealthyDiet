package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 */

public class RvTagAdapter extends RecyclerView.Adapter<RvTagViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;

    private ItemOnClickListener mItemOnClickListener;
    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvTagAdapter(Context pContext, List<String> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvTagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_tag_item, parent, false);

        return new RvTagViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvTagViewHolder holder, int position) {
        holder.tvTag.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class RvTagViewHolder extends RecyclerView.ViewHolder {

    TextView tvTag,tvTitle;

    RvTagViewHolder(View itemView) {
        super(itemView);
        tvTag= (TextView) itemView.findViewById(R.id.tvTag);
    }
}