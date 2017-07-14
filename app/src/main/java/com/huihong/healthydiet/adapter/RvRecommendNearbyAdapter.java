package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.RecipesActivity;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.utils.current.ImageLoderUtil;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 附近餐厅 RecyclerView
 */

public class RvRecommendNearbyAdapter extends RecyclerView.Adapter<RvRecommendNearbyViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;

    private ItemOnClickListener mItemOnClickListener;
    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvRecommendNearbyAdapter(Context pContext, List<String> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvRecommendNearbyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_recommend_nearby_item, parent, false);

        return new RvRecommendNearbyViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvRecommendNearbyViewHolder holder, int position) {
//        holder.tvName.setText(mList.get(position));

        ImageLoderUtil.showImage(mContext,"",holder.ivHead);

        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIn=new Intent(mContext, RecipesActivity.class);
                mContext.startActivity(mIn);
            }
        });
//
//      List<String> zz=new ArrayList<>();
//        for (int i = 0; i <7 ; i++) {
//            zz.add("红烧啊");
//        }
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        holder.rvArticleTag. setLayoutManager(linearLayoutManager);
//        holder.rvArticleTag.setAdapter(new RvTagAdapter(mContext,zz));

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

class RvRecommendNearbyViewHolder extends RecyclerView.ViewHolder {

    TextView tvName,tvTitle;
    LinearLayout layoutMain;

    RecyclerView rvArticleTag;
    SelectableRoundedImageView ivHead;

    RvRecommendNearbyViewHolder(View itemView) {
        super(itemView);
        ivHead= (SelectableRoundedImageView) itemView.findViewById(R.id.ivHead);
        layoutMain= (LinearLayout) itemView.findViewById(R.id.layoutMain);
//        rvArticleTag= (RecyclerView) itemView.findViewById(R.id.rvArticleTag);
//        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
//        mLinearLayout= (LinearLayout) itemView.findViewById(R.id.mLinearLayout);
    }
}