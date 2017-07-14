package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.utils.current.ImageLoderUtil;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/6/5
 */

public class LvRecordAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;

    public LvRecordAdapter(Context mContext, List<String> mList) {
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
            mView = LayoutInflater.from(mContext).inflate(R.layout.lv_record_item, null);
            mHolder = new ViewHolder(mView);
            mView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) mView.getTag();
        }
//        Temperature mTemperature = mList.get(position);
//
//        if(HomePageFragment.isC){
//            mHolder.tvTime.setText("" + mTemperature.getTemperature() + "℃");
//        }else {
//            double t=mTemperature.getTemperature()*1.8+32;
//            String a=t+"";
//            if(a.length()>5){
//                a=a.substring(0,5);
//            }
//            mHolder.tvTime.setText("" + a + "℉");
//        }
////
////
//        mHolder.tvTemperature.setText("" + mTemperature.getTime() + "");


        ImageLoderUtil.showImage(mContext,"",mHolder.ivHead);
        return mView;


    }

    private class ViewHolder {
        private TextView tvTime;
        private TextView tvTemperature;
        private SelectableRoundedImageView ivHead;

        ViewHolder(View view) {
            ivHead= (SelectableRoundedImageView) view.findViewById(R.id.ivHead);
//            tvTime = (TextView) view.findViewById(R.id.tvTime);
//            tvTemperature = (TextView) view.findViewById(R.id.tvTemperature);
        }
    }
}
