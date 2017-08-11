package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.RecipesDetailsActivity;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.model.httpmodel.RecipeInfo;
import com.huihong.healthydiet.widget.HorizontalListView;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 附近餐厅 RecyclerView
 */

public class RvRecommendRecommendAdapter extends RecyclerView.Adapter<RvRecommendRecommendViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<RecipeInfo> mList;

    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvRecommendRecommendAdapter(Context pContext, List<RecipeInfo> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvRecommendRecommendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_recommend_recommend_item, parent, false);

        return new RvRecommendRecommendViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvRecommendRecommendViewHolder holder, final int position) {
//        holder.tvName.setText(mList.get(position));
        Glide
                .with(mContext)
                .load(mList.get(position).getTitleImage())
                .asBitmap()
                .error(R.mipmap.error_photo)
                .into(holder.ivHead);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        holder.rvArticleTag.setLayoutManager(linearLayoutManager);
//        holder.rvArticleTag.setAdapter(new RvTagAdapter(mContext, mList.get(position).getTags()));

        holder.mHorizontalListView.setAdapter(new LvTagAdapter(mContext, mList.get(position).getTags()));

        holder.name.setText(mList.get(position).getName());
        holder.sales.setText(mList.get(position).getSales() + "份");
        holder.price.setText(mList.get(position).getPrice() + "");
        holder.Restaurant_Name.setText(mList.get(position).getRestaurant_Name());
        holder.Restaurant_Address.setText(mList.get(position).getRestaurant_Address());

        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, RecipesDetailsActivity.class);
                mIntent.putExtra("RecipeId", mList.get(position).getId() + "");
                mContext.startActivity(mIntent);
            }
        });


        int percentage = mList.get(position).getConstitutionPercentage();

        if (percentage > 90) {
            holder.tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_9));
        } else if (percentage > 80 & percentage <= 90) {
            holder.tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_8));
        } else if (percentage > 70 & percentage <= 80) {
            holder.tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_7));
        } else if (percentage > 60 & percentage <= 70) {
            holder.tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_6));
        } else {
            holder.tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_5));
        }
        holder.tvConstitutionPercentage.setText(percentage + "%");


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class RvRecommendRecommendViewHolder extends RecyclerView.ViewHolder {

    /**
     * id : 1
     * name : 食谱1
     * images : http://106.14.218.31:8081/img//recipe//recipe1-1986270.jpg
     * ConstitutionPercentage : -2147483648
     * sales : 12
     * price : 66.00
     * Tags : ["清蒸","爆炒"]
     * Restaurant_Name : 餐厅2
     * Restaurant_Address : 杭州市拱墅区拱墅区
     * distance : 8949
     * category : 3
     */


    TextView name, sales, price, Restaurant_Name, Restaurant_Address, tvConstitutionPercentage;
    LinearLayout mLinearLayout;

    RecyclerView rvArticleTag;
    SelectableRoundedImageView ivHead;
    HorizontalListView mHorizontalListView;
    RelativeLayout layoutMain;

    RvRecommendRecommendViewHolder(View itemView) {
        super(itemView);
        ivHead = (SelectableRoundedImageView) itemView.findViewById(R.id.ivHead);
//        rvArticleTag = (RecyclerView) itemView.findViewById(R.id.rvArticleTag);
        mHorizontalListView = (HorizontalListView) itemView.findViewById(R.id.mHorizontalListView);

        name = (TextView) itemView.findViewById(R.id.name);
        tvConstitutionPercentage = (TextView) itemView.findViewById(R.id.tvConstitutionPercentage);
        sales = (TextView) itemView.findViewById(R.id.sales);
        price = (TextView) itemView.findViewById(R.id.price);
        Restaurant_Name = (TextView) itemView.findViewById(R.id.Restaurant_Name);
        Restaurant_Address = (TextView) itemView.findViewById(R.id.Restaurant_Address);


        layoutMain = (RelativeLayout) itemView.findViewById(R.id.layoutMain);
    }
}