package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.RestaurantDetailsActivity;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.model.gsonbean.CustomerLikeOrNot;
import com.huihong.healthydiet.model.httpmodel.RestaurantInfo;
import com.huihong.healthydiet.utils.common.GlideUtils;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.joooonho.SelectableRoundedImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 餐厅收藏
 */

public class RvRestaurantLikeAdapter extends RecyclerView.Adapter<RvRestaurantLikeAdapter.mViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<RestaurantInfo> mList;

    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvRestaurantLikeAdapter(Context pContext, List<RestaurantInfo> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_restaurant_like_item, parent, false);

        return new mViewHolder(mView);
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final mViewHolder holder, final int position) {

        LogUtil.i("近来楼");

        holder.name.setText(mList.get(position).getName());
        holder.address.setText(mList.get(position).getAddress());
        holder.sales.setText("本月销售" + mList.get(position).getSales() + "份");
        holder.consumption.setText("人均 ￥ " + mList.get(position).getConsumption() + "");
        holder.discount.setText(mList.get(position).getDiscount() + "");
        int distance = (int) mList.get(position).getDistance();

        if (distance > 1000) {
            distance = distance / 1000;
            holder.distance.setText("" + distance + "km");
        } else {
            holder.distance.setText("" + distance + "m");
        }

//
//        Glide
//                .with(mContext)
//                .load(mList.get(position).getTitleImage())
//                .asBitmap()
//                .error(R.mipmap.error_photo)
//                .into(holder.ivHead);

        GlideUtils.loadImageViewAsBitmap(mContext,mList.get(position).getTitleImage(),holder.ivHead);


        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIn = new Intent(mContext, RestaurantDetailsActivity.class);
                mIn.putExtra("id", mList.get(position).getId());
                mContext.startActivity(mIn);
            }
        });

        holder.ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mList.get(position).getPhone()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFood(mList.get(position).getId(),position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class mViewHolder extends RecyclerView.ViewHolder {


        /**
         * id : 3
         * name : 餐厅2
         * images : ["img//restaurant//restaurant3-1964553.jpg"]
         * address : 拱墅区
         * phone : 18857120151
         * category : 2
         * sales : 888
         * consumption : 25
         * discount : 优惠2|非常可疑
         * distance : 1.1901423500846999E7
         */

        TextView tvName, tvTitle, name, images, address, phone, category, sales, consumption, discount, distance;
        LinearLayout layoutMain;
        ImageView ivPhone;
        Button btDelete;

        SelectableRoundedImageView ivHead;

        mViewHolder(View itemView) {
            super(itemView);
            ivHead = (SelectableRoundedImageView) itemView.findViewById(R.id.ivHead);
            layoutMain = (LinearLayout) itemView.findViewById(R.id.layoutMain);

            name = (TextView) itemView.findViewById(R.id.name);//餐厅名称
            address = (TextView) itemView.findViewById(R.id.address);//地址
            sales = (TextView) itemView.findViewById(R.id.sales);//本月已售
            consumption = (TextView) itemView.findViewById(R.id.consumption);//平均消费
            discount = (TextView) itemView.findViewById(R.id.discount);//优惠
            distance = (TextView) itemView.findViewById(R.id.distance);//距离

            ivPhone = (ImageView) itemView.findViewById(R.id.ivPhone);

            btDelete= (Button) itemView.findViewById(R.id.btDelete);
        }
    }

    private void deleteFood(int foodId, final int pos) {
        Map<String, String> map = new HashMap<>();
        map.put("Type_Like","restlike");
        map.put("OtherId",foodId+"");
        map.put("Opertion","Delete");
        map.put("UserId",  SPUtils.get(mContext,"UserId",0)+"");

        HttpUtils.sendHttpAddToken(mContext
                , AppUrl.CUSTOMER_LIKE_OR_NOT
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("删除喜欢",response);
                        Gson gson = new Gson();
                        CustomerLikeOrNot mCustomerLikeOrNot = gson.fromJson(response, CustomerLikeOrNot.class);
                        String message=mCustomerLikeOrNot.getMessage();
                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
//                        if(mCustomerLikeOrNot.getHttpCode()==200){
                            mList.remove(pos);
                            RvRestaurantLikeAdapter.this.notifyDataSetChanged();
//                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        LogUtil.i("西部喜欢",e.toString());
                    }
                });
    }

}

