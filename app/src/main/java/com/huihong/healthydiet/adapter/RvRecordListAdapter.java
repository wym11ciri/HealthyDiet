package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.OrderDetailsActivity;
import com.huihong.healthydiet.activity.PayActivity;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.model.httpmodel.OrderDetailsInfo;
import com.huihong.healthydiet.utils.common.GlideUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.huihong.healthydiet.widget.expand.HorizontalListView;
import com.joooonho.SelectableRoundedImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 饮食记录RecyclerView
 */

public class RvRecordListAdapter extends RecyclerView.Adapter<RvRecordListAdapter.MyViewHolder> {


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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_record_list_item, parent, false);

        return new MyViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        if(position==mList.size()-1){
            holder.viewLine.setVisibility(View.GONE);
        }else {
            holder.viewLine.setVisibility(View.VISIBLE);
        }


//        GlideUtils.loadImageView(mContext,mList.get(position).getRecipeImage());

//        Glide
//                .with(mContext)
//                .load(mList.get(position).getRecipeImage())
//                .asBitmap()
//                .error(R.mipmap.error_photo)
//                .into(holder.ivHead);
        GlideUtils.loadImageViewAsBitmap(mContext,mList.get(position).getRecipeImage(),holder.ivHead);


        LvOrderDetailsTypeAdapter lvOrderDetailsTypeAdapter =new LvOrderDetailsTypeAdapter(mContext,mList.get(position).getConstitution());
        holder.lvType.setAdapter(lvOrderDetailsTypeAdapter);


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
        if( mList.get(position).getFoodRecipe()!=null){
            for (int i = 0; i <mList.get(position).getFoodRecipe().size() ; i++) {
                OrderDetailsInfo.FoodRecipeBean mFoodRecipeBean=mList.get(position).getFoodRecipe().get(i);
                cailiao=cailiao+mFoodRecipeBean.getListFood()+",";
            }
        }
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("OrderId",mList.get(position).getOrderId());
                map.put("UserId",  SPUtils.get(mContext,"UserId",0)+"");
                HttpUtils.sendHttpAddToken(mContext, AppUrl.DELETE_ORDER
                        , map
                        , new HttpUtilsListener() {
                            @Override
                            public void onResponse(String response, int id) {
                                LogUtil.i("删除订单",response);
                                mList.remove(position);
                                RvRecordListAdapter.this.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                LogUtil.i("删除订单",e.toString());
                            }
                        });


            }
        });

        holder.tvMaterial.setText(cailiao);



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvTitle, tvRestaurantName, tvTime, tvPrice, tvMaterial;
        LinearLayout mLinearLayout;
        SelectableRoundedImageView ivHead;
        TextView tvGetAgain;
        View viewLine;
        LinearLayout layoutMain;
        HorizontalListView lvType;
        Button btDelete;

        MyViewHolder(View itemView) {
            super(itemView);
            ivHead = (SelectableRoundedImageView) itemView.findViewById(R.id.ivHead);
            viewLine = itemView.findViewById(R.id.viewLine);
            tvGetAgain = (TextView) itemView.findViewById(R.id.tvGetAgain);
            layoutMain = (LinearLayout) itemView.findViewById(R.id.layoutMain);
            lvType = (HorizontalListView) itemView.findViewById(R.id.lvType);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvRestaurantName = (TextView) itemView.findViewById(R.id.tvRestaurantName);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvMaterial = (TextView) itemView.findViewById(R.id.tvMaterial);
            btDelete= (Button) itemView.findViewById(R.id.btDelete);

        }
    }
}


