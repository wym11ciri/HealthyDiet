package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.model.mybean.Type;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/6/5
 */

public class LvPopTypeAdapter extends BaseAdapter {
    private Context mContext;
    private List<Type> mList;

    public LvPopTypeAdapter(Context mContext, List<Type> mList) {
        this.mContext = mContext;
        this.mList = mList;
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
            mView = LayoutInflater.from(mContext).inflate(R.layout.lv_pop_type_item, null);
            mHolder = new ViewHolder(mView);
            mView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) mView.getTag();
        }
        mHolder.mTextView.setText(mList.get(position).getTypeValue());
        return mView;
    }

    private class ViewHolder {
        private TextView mTextView;

        ViewHolder(View view) {

            mTextView = (TextView) view.findViewById(R.id.mTextView);

        }
    }
}
