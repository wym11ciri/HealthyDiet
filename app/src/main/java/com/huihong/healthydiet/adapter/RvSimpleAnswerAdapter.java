package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.bean.GetQuestionExpressList;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 附近餐厅 RecyclerView
 */

public class RvSimpleAnswerAdapter extends RecyclerView.Adapter<RvSimpleAnswerAdapter.RvSimpleAnswerViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private ItemOnClickListener mItemOnClickListener;
    private List<GetQuestionExpressList.ListDataBean.OptionsBean> OptionsBeanList;//答案

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }

    public RvSimpleAnswerAdapter(Context pContext, List<GetQuestionExpressList.ListDataBean.OptionsBean> pList) {
        OptionsBeanList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvSimpleAnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_answer_item_simple, parent, false);
        return new RvSimpleAnswerViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvSimpleAnswerViewHolder holder, final int position) {
        holder.tvAnswer.setText(OptionsBeanList.get(position).getOptionContent());

        if (OptionsBeanList.get(position).isClick()) {
            holder.ivAnswer.setImageResource(R.mipmap.simple_answer_click);
        } else {
            holder.ivAnswer.setImageResource(R.mipmap.simple_answer_normal);
        }


        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OptionsBeanList.get(position).isClick()) {
                    holder.ivAnswer.setImageResource(R.mipmap.simple_answer_normal);
                    OptionsBeanList.get(position).setClick(false);
                } else {
                    holder.ivAnswer.setImageResource(R.mipmap.simple_answer_click);
                    OptionsBeanList.get(position).setClick(true);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return OptionsBeanList.size();
    }

    public List<Integer> getAnswer() {
        List<Integer> mOptionIdList = new ArrayList<>();
        for (int i = 0; i < OptionsBeanList.size(); i++) {
            if (OptionsBeanList.get(i).isClick()) {
                mOptionIdList.add(OptionsBeanList.get(i).getOptionId());
            }
        }
        return mOptionIdList;

    }

    class RvSimpleAnswerViewHolder extends RecyclerView.ViewHolder {


        TextView tvAnswer;
        ImageView ivAnswer;
        RelativeLayout layoutMain;

        RvSimpleAnswerViewHolder(View itemView) {
            super(itemView);
            ivAnswer = (ImageView) itemView.findViewById(R.id.ivAnswer);
            tvAnswer = (TextView) itemView.findViewById(R.id.tvAnswer);
            layoutMain = (RelativeLayout) itemView.findViewById(R.id.layoutMain);
        }
    }

}

