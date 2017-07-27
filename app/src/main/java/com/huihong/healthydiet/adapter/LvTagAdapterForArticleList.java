package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huihong.healthydiet.R;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/6/5
 * 文章列表的标签列表
 */

public class LvTagAdapterForArticleList extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public LvTagAdapterForArticleList(Context mContext, List<String> mList) {
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
            mView = LayoutInflater.from(mContext).inflate(R.layout.rv_tag_item, null);
            mHolder = new ViewHolder(mView);
            mView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) mView.getTag();
        }
        mHolder.mTextView.setText(mList.get(position));
        return mView;
    }

    private class ViewHolder {
        private TextView mTextView;

        ViewHolder(View view) {

            mTextView = (TextView) view.findViewById(R.id.tvTag);

        }
    }
}
