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
 * 热门搜索列表
 */

public class RvHistorySearchAdapter extends RecyclerView.Adapter<RvHistorySearchViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;

    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvHistorySearchAdapter(Context pContext, List<String> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvHistorySearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_history_search_item, parent, false);

        return new RvHistorySearchViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvHistorySearchViewHolder holder, int position) {

        holder.tvName.setText(mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class  RvHistorySearchViewHolder extends RecyclerView.ViewHolder {

    TextView tvName;

    RvHistorySearchViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
    }
}
