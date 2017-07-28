package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 */

public class RvTypeAdapter3 extends RecyclerView.Adapter<RvTypeAdapter3.RvTypeViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;

    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }

    public RvTypeAdapter3(Context pContext, List<String> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_type_item3, parent, false);

        return new RvTypeViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvTypeViewHolder holder, int position) {
        String type = mList.get(position);
        if (type.equals("平和质")) {
            holder.ivType.setImageResource(R.mipmap.temperament_1);
        } else if (type.equals("气郁质")) {
            holder.ivType.setImageResource(R.mipmap.temperament_2);
        } else if (type.equals("阴虚质")) {
            holder.ivType.setImageResource(R.mipmap.temperament_8);
        } else if (type.equals("痰湿质")) {
            holder.ivType.setImageResource(R.mipmap.temperament_5);
        } else if (type.equals("阳虚质")) {
            holder.ivType.setImageResource(R.mipmap.temperament_9);
        } else if (type.equals("特禀质")) {
            holder.ivType.setImageResource(R.mipmap.temperament_6);
        } else if (type.equals("湿热质")) {
            holder.ivType.setImageResource(R.mipmap.temperament_4);
        } else if (type.equals("气虚质")) {
            holder.ivType.setImageResource(R.mipmap.temperament_2);
        } else if (type.equals("血瘀质")) {
            holder.ivType.setImageResource(R.mipmap.temperament_7);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class RvTypeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivType;

        RvTypeViewHolder(View itemView) {
            super(itemView);
            ivType = (ImageView) itemView.findViewById(R.id.ivType);
        }
    }
}

