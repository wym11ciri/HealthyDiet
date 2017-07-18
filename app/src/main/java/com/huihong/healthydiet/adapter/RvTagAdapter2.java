package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 */

public class RvTagAdapter2 extends RecyclerView.Adapter<RvTagViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;

    private ItemOnClickListener mItemOnClickListener;
    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvTagAdapter2(Context pContext, List<String> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvTagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_tag_item, parent, false);

        return new RvTagViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvTagViewHolder holder, int position) {
//        holder.tvName.setText(mList.get(position));

//        Glide
//                .with(mContext)
//                .load("http://www.qiwen007.com/images/image/2016/1212/6361714777668259239190221.jpg")
//                .asBitmap()
//                .into( holder.ivHead);
        holder.tvTag.setText(mList.get(position));



//        holder.tvTime.setText(mList.get(position).getTime());
//        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mItemOnClickListener!=null){
//                    mItemOnClickListener.onClick( holder.tvTitle,holder.getAdapterPosition());
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

