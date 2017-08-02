package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mInterface.ItemOnClickListener2;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 热门搜索列表
 */

public class RvHotSearchAdapter extends RecyclerView.Adapter<RvHotSearchViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;

    private ItemOnClickListener2 mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener2 pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvHotSearchAdapter(Context pContext, List<String> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvHotSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_hot_search_item, parent, false);

        return new RvHotSearchViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvHotSearchViewHolder holder, final int position) {

        holder.tvName.setText(mList.get(position));
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemOnClickListener!=null){
                    mItemOnClickListener.onClick(mList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}


class RvHotSearchViewHolder extends RecyclerView.ViewHolder {

    TextView tvName;

    RvHotSearchViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
    }
}