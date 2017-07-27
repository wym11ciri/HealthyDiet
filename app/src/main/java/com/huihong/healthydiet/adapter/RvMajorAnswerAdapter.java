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
import com.huihong.healthydiet.mInterface.ItemOnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 附近餐厅 RecyclerView
 */

public class RvMajorAnswerAdapter extends RecyclerView.Adapter<RvMajorAnswerAdapter.RvMajorAnswerViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;

    private ItemOnClickListener mItemOnClickListener;

    private int chooseId = -1;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvMajorAnswerAdapter(Context pContext) {
        mList = new ArrayList<>();
        mList.add("完全不");
        mList.add("有一点");
        mList.add("非常");
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvMajorAnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_answer_item_major, parent, false);

        return new RvMajorAnswerViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvMajorAnswerViewHolder holder, final int position) {
        holder.tvAnswer.setText(mList.get(position));

        if (position == chooseId) {
            holder.ivAnswer.setImageResource(R.mipmap.answer_choose);
        } else {
            holder.ivAnswer.setImageResource(R.mipmap.answer_normal);
        }

        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseId = position;
                RvMajorAnswerAdapter.this.notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public int getAnswer() {
        return chooseId;
    }
    public  void resetChooseId(){
        chooseId=-1;
        this.notifyDataSetChanged();
    }
    class RvMajorAnswerViewHolder extends RecyclerView.ViewHolder {


        TextView tvAnswer;
        ImageView ivAnswer;
        RelativeLayout layoutMain;

        RvMajorAnswerViewHolder(View itemView) {
            super(itemView);
            ivAnswer = (ImageView) itemView.findViewById(R.id.ivAnswer);
            tvAnswer = (TextView) itemView.findViewById(R.id.tvAnswer);
            layoutMain = (RelativeLayout) itemView.findViewById(R.id.layoutMain);
        }
    }

}

