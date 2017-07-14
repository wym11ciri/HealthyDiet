package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.RecipesDetailsActivity;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.utils.current.ImageLoderUtil;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 附近餐厅 RecyclerView
 */

public class RvRecipesAdapter extends RecyclerView.Adapter<RvRecipesViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;

    private ItemOnClickListener mItemOnClickListener;
    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvRecipesAdapter(Context pContext, List<String> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvRecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_recipes_item, parent, false);

        return new RvRecipesViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvRecipesViewHolder holder, int position) {
//        holder.tvName.setText(mList.get(position));


        ImageLoderUtil.showImage(mContext,"",holder.ivHead);
//
      List<String> zz=new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
            zz.add("红烧啊");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rvArticleTag. setLayoutManager(linearLayoutManager);
        holder.rvArticleTag.setAdapter(new RvTypeAdapter(mContext,zz));

//        holder.tvTime.setText(mList.get(position).getTime());
//        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mItemOnClickListener!=null){
//                    mItemOnClickListener.onClick( holder.tvTitle,holder.getAdapterPosition());
//                }
//            }
//        });

        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIn=new Intent(mContext, RecipesDetailsActivity.class);
                mContext.startActivity(mIn);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class RvRecipesViewHolder extends RecyclerView.ViewHolder {

    TextView tvName,tvTitle;
    RelativeLayout layoutMain;

    RecyclerView rvArticleTag;
    SelectableRoundedImageView ivHead;

    RvRecipesViewHolder(View itemView) {
        super(itemView);
        ivHead= (SelectableRoundedImageView) itemView.findViewById(R.id.ivHead);
        rvArticleTag= (RecyclerView) itemView.findViewById(R.id.rvArticleTag);
//        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        layoutMain= (RelativeLayout) itemView.findViewById(R.id.layoutMain);
    }
}