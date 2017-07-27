package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.ArticleDetailsActivity;
import com.huihong.healthydiet.bean.GetArticleListInfo;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.widget.HorizontalListView;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 文章列表
 */

public class RvArticleAdapter extends RecyclerView.Adapter<RvArticleAdapter.RvArticleViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<GetArticleListInfo.ListDataBean> mList;

    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvArticleAdapter(Context pContext, List<GetArticleListInfo.ListDataBean> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.lv_article_item, parent, false);

        return new RvArticleViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvArticleViewHolder holder, final int position) {


        Glide
                .with(mContext)
                .load(mList.get(position).getTitleImage())
                .asBitmap()
                .error(R.mipmap.error_photo)
                .into(holder.ivHead);

        holder.tvClickCount.setText(mList.get(position).getCilckCount()+"");
        holder.tvLoveCount.setText(mList.get(position).getLoveCount()+"");
        holder.tvTitle.setText(mList.get(position).getTitle());
        String mTime=mList.get(position).getATime();
        mTime=mTime.replace("T", " ");
        holder.tvTime.setText(mTime);

        holder.lvTag.setAdapter(new LvTagAdapterForArticleList(mContext,mList.get(position).getTags()));

        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(mContext, ArticleDetailsActivity.class);
                Bundle bundle = new Bundle();
//                mIntent.putExtra("url",mList.get(position).getUrl());
                bundle.putSerializable("info",mList.get(position));
                mIntent.putExtras(bundle);
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RvArticleViewHolder extends RecyclerView.ViewHolder {
        TextView  tvTitle,tvTime;
        LinearLayout mLinearLayout;
        RelativeLayout layoutMain;
        SelectableRoundedImageView ivHead;
        HorizontalListView lvTag;
        TextView tvLoveCount,tvClickCount;
        RvArticleViewHolder(View itemView) {
            super(itemView);
            ivHead = (SelectableRoundedImageView) itemView.findViewById(R.id.ivHead);
            lvTag = (HorizontalListView) itemView.findViewById(R.id.lvTag);
            tvLoveCount = (TextView) itemView.findViewById(R.id.tvLoveCount);
            tvClickCount = (TextView) itemView.findViewById(R.id.tvClickCount);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            layoutMain = (RelativeLayout) itemView.findViewById(R.id.layoutMain);
//        mLinearLayout= (LinearLayout) itemView.findViewById(R.id.mLinearLayout);
        }
    }
}


