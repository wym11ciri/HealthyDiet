package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class RvRecommendNearbyAdapter extends RecyclerView.Adapter<RvRecommendNearbyViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<RestaurantInfo> mList;

    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvRecommendNearbyAdapter(Context pContext, List<RestaurantInfo> pList) {
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
    public void onBindViewHolder(final RvRecommendNearbyViewHolder holder, final int position) {


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


//        Glide
//                .with(mContext)
//                .load(mList.get(position).getTitleImage())
//                .asBitmap()
//                .error(R.mipmap.error_photo)
//                .into(holder.ivHead);

        GlideUtils.loadImageViewAsBitmap(mContext,mList.get(position).getTitleImage(),holder.ivHead);
//        ImageLoderUtil.showImage2(mContext, position, holder.ivHead);

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
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+mList.get(position).getPhone()));
//                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    return;
//                }
//                mContext.startActivity(intent);
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

    SelectableRoundedImageView ivHead;

    RvRecommendNearbyViewHolder(View itemView) {
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


    }
}