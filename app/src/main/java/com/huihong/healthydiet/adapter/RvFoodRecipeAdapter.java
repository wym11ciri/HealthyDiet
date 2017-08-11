package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.model.gsonbean.RecipeListInfoByDRId;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 附近餐厅 RecyclerView
 */

public class RvFoodRecipeAdapter extends RecyclerView.Adapter<RvFoodRecipeViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<RecipeListInfoByDRId.ListDataBean.FoodRecipeBean> mList;

    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvFoodRecipeAdapter(Context pContext, List<RecipeListInfoByDRId.ListDataBean.FoodRecipeBean> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvFoodRecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_food_recipe_item, parent, false);

        return new RvFoodRecipeViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvFoodRecipeViewHolder holder,  int position) {
        holder.tvListFood.setText(mList.get(position).getListFood());
        holder.tvRecipeItemName.setText(mList.get(position).getRecipeItemName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class RvFoodRecipeViewHolder extends RecyclerView.ViewHolder {

    TextView tvRecipeItemName, tvListFood;

    RvFoodRecipeViewHolder(View itemView) {
        super(itemView);
        tvRecipeItemName = (TextView) itemView.findViewById(R.id.tvRecipeItemName);
        tvListFood = (TextView) itemView.findViewById(R.id.tvListFood);
    }
}