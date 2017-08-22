package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.OrderDetailsActivity;
import com.huihong.healthydiet.activity.PayActivity;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.model.httpmodel.OrderDetailsInfo;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 饮食记录RecyclerView
 */

public class RvRecordListAdapter extends RecyclerView.Adapter<RvRecordViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<OrderDetailsInfo> mList;

    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvRecordListAdapter(Context pContext, List<OrderDetailsInfo> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_record_list_item, parent, false);

        return new RvRecordViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvRecordViewHolder holder, final int position) {


        if(position==mList.size()-1){
            holder.viewLine.setVisibility(View.GONE);
        }else {
            holder.viewLine.setVisibility(View.VISIBLE);
        }



        Glide
                .with(mContext)
                .load(mList.get(position).getRecipeImage())
                .asBitmap()
                .error(R.mipmap.error_photo)
                .into(holder.ivHead);


        LvOrderDetailsTypeAdapter lvOrderDetailsTypeAdapter =new LvOrderDetailsTypeAdapter(mContext,mList.get(position).getConstitution());
        holder.lvType.setAdapter(lvOrderDetailsTypeAdapter);


//        holder.tvTime.setText(mList.get(position).getTime());
//        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mItemOnClickListener!=null){
//                    mItemOnClickListener.onClick( holder.tvTitle,holder.getAdapterPosition());
//                }
//            }
//        });

        holder.tvName.setText(mList.get(position).getRecipeName());

        holder.tvRestaurantName.setText(mList.get(position).getRestaurantName());

        String time=mList.get(position).getOrderTime();
        time=time.replace("T"," ");
        holder.tvTime.setText(time);


        holder.tvPrice.setText("￥ "+mList.get(position).getOrderPrice());

        holder.tvGetAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(mContext, PayActivity.class);
                mIntent.putExtra("RecipeId", mList.get(position).getRecipeId());
                mContext.startActivity(mIntent);
            }
        });

        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent=new Intent(mContext, OrderDetailsActivity.class);
                mIntent.putExtra("OrderId",mList.get(position).getOrderId());
                mContext.startActivity(mIntent);
            }
        });

        String cailiao="";
//        mList.get(position).getFoodRecipe();
        for (int i = 0; i <mList.get(position).getFoodRecipe().size() ; i++) {
            OrderDetailsInfo.FoodRecipeBean mFoodRecipeBean=mList.get(position).getFoodRecipe().get(i);
            cailiao=cailiao+mFoodRecipeBean.getListFood()+",";
        }

        holder.tvMaterial.setText(cailiao);



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}


