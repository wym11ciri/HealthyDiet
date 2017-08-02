package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.cache.litepal.SearchHistory;
import com.huihong.healthydiet.mInterface.ItemOnClickListener2;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 热门搜索列表
 */

public class RvHistorySearchAdapter extends RecyclerView.Adapter<RvHistorySearchAdapter.RvHistorySearchViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<SearchHistory> mList;

    private ItemOnClickListener2 mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener2 pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvHistorySearchAdapter(Context pContext, List<SearchHistory> pList) {
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
    public void onBindViewHolder(final RvHistorySearchViewHolder holder, final int position) {

        //设置倒序显示
        final int max = mList.size() - 1;

        holder.tvName.setText(mList.get(max - position).getSearchHistory());


        holder.layoutMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                showListPopup(v,holder.getAdapterPosition());

                mList.get(max - position).delete();
                mList.remove(max - position);
                RvHistorySearchAdapter.this.notifyDataSetChanged();
                return false;
            }
        });

        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemOnClickListener!=null){
                    mItemOnClickListener.onClick(mList.get(max-position).getSearchHistory());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class RvHistorySearchViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        RelativeLayout layoutMain;


        RvHistorySearchViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            layoutMain = (RelativeLayout) itemView.findViewById(R.id.layoutMain);
        }
    }
}


