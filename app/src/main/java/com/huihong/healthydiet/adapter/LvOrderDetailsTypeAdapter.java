package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/6/5
 */

public class LvOrderDetailsTypeAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public LvOrderDetailsTypeAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        if (mList != null) {
            this.mList = mList;
        } else {
            this.mList = new ArrayList<>();
        }
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
            mView = LayoutInflater.from(mContext).inflate(R.layout.lv_order_details_type_item, null);
            mHolder = new ViewHolder(mView);
            mView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) mView.getTag();
        }
        MyUtils.setImageViewType(mHolder.ivType, mList.get(position));
        return mView;
    }

    private class ViewHolder {
        private ImageView ivType;

        ViewHolder(View view) {
            ivType = (ImageView) view.findViewById(R.id.ivType);
        }
    }
}
