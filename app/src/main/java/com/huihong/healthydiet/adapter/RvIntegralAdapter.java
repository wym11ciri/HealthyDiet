package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihong.healthydiet.R;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 最近获得的积分
 */

public class RvIntegralAdapter extends RecyclerView.Adapter<RvIntegralViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;

    public RvIntegralAdapter(Context pContext, List<String> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvIntegralViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_integral_item, parent, false);

        return new RvIntegralViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvIntegralViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class RvIntegralViewHolder extends RecyclerView.ViewHolder {

    TextView tvName;

    RvIntegralViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
    }
}