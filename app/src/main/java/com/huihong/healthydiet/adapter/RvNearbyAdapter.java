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
import com.huihong.healthydiet.activity.RestaurantDetailsActivity;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.model.httpmodel.RestaurantInfo;
import com.huihong.healthydiet.utils.common.GlideUtils;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 附近餐厅 RecyclerView
 */

public class RvNearbyAdapter extends RecyclerView.Adapter<RvNearbyViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<RestaurantInfo> mList;

    private ItemOnClickListener mItemOnClickListener;
    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvNearbyAdapter(Context pContext, List<RestaurantInfo> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvNearbyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_nearby_item, parent, false);

        return new RvNearbyViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvNearbyViewHolder holder, final int position) {

        holder.tvName.setText(mList.get(position).getName());

        holder.ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIn=new Intent(mContext, RestaurantDetailsActivity.class);
                mIn.putExtra("id",mList.get(position).getId());

                mContext.startActivity(mIn);
            }
        });

//        Glide
//                .with(mContext)
//                .load(mList.get(position).getTitleImage())
//                .asBitmap()
//                .error(R.mipmap.error_photo)
//                .into(holder.ivHead);
        GlideUtils.loadImageViewAsBitmap(mContext,mList.get(position).getTitleImage(),holder.ivHead);

        if(mList.get(position).getDistance()>1000){
            int a=(mList.get(position).getDistance()/1000);
            holder.tvDistance.setText(a+"km");
        }else {
            holder.tvDistance.setText(mList.get(position).getDistance()+"m");
        }




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


class RvNearbyViewHolder extends RecyclerView.ViewHolder {

    TextView tvName,tvTitle,tvDistance;
    LinearLayout mLinearLayout;
    SelectableRoundedImageView ivHead;

    RvNearbyViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        ivHead= (SelectableRoundedImageView) itemView.findViewById(R.id.ivHead);
        tvDistance = (TextView) itemView.findViewById(R.id.tvDistance);
//        mLinearLayout= (LinearLayout) itemView.findViewById(R.id.mLinearLayout);
    }
}