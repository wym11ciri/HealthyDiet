package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/6/5
 */

public class LvHistoryDeleteAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public LvHistoryDeleteAdapter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
        mList.add("删除");
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View mView, ViewGroup parent) {

        ViewHolder mHolder;
        if (mView == null) {
            mView = LayoutInflater.from(mContext).inflate(R.layout.lv_history_delete_item, null);
            mHolder = new ViewHolder(mView);
            mView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) mView.getTag();
        }
        return mView;
    }

    private class ViewHolder {
        private TextView tvTime;
        private TextView tvTemperature;
        private SelectableRoundedImageView ivHead;

        ViewHolder(View view) {
//            ivHead= (SelectableRoundedImageView) view.findViewById(R.id.ivHead);
//            tvTime = (TextView) view.findViewById(R.id.tvTime);
//            tvTemperature = (TextView) view.findViewById(R.id.tvTemperature);
        }
    }
}
