package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.ArticleDetailsActivity;
import com.huihong.healthydiet.activity.RestaurantDetailsActivity;
import com.huihong.healthydiet.bean.SearchVagueRestaurant;
import com.huihong.healthydiet.mInterface.ArticleItemOnClickListener;
import com.huihong.healthydiet.model.ArticleInfo;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.widget.HorizontalListView;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 搜索结果
 */

public class RvSearchResultLeftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;

    private final int RESTAURANT_TAG = 0;
    private final int DIET_TAG = 1;
    private final int ARTICLE_TAG = 2;
    private final int PARTING_LINE_TAG = 3;


    private List<SearchVagueRestaurant.ListDataBean> mList01;//附近餐厅
    private List<ArticleInfo> mList02;//推荐文章

    //该Adapter使用场景
    private boolean isRight = true;


    private ArticleItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ArticleItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvSearchResultLeftAdapter(Context pContext, List<SearchVagueRestaurant.ListDataBean> mList01
            , List<ArticleInfo> mList02) {

        this.mList01 = mList01;
        this.mList02 = mList02;

        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }

    //设置多种布局
    @Override
    public int getItemViewType(int position) {


        //附近餐厅列表
        if (mList01.size() > 0) {
            //附近餐厅列表有数据
            if (mList02.size() > 0) {
                //推荐文章有数据
                //此时显示附近餐厅 + 分割线 + 推荐文章
                //进行判断
                if (position < mList01.size()) {
                    //显示附近餐厅
                    return RESTAURANT_TAG;
                } else if (position == mList01.size()) {
                    //等于size的时候显示分割线
                    return PARTING_LINE_TAG;
                } else {
                    //其他显示推荐文章
                    return ARTICLE_TAG;
                }
            } else {
                return RESTAURANT_TAG;
            }
        } else {
            //附近餐厅没有数据
            if (mList02.size() > 0) {
                //推荐文章有数据
                if (position == 0) {
                    return PARTING_LINE_TAG;
                } else {
                    return ARTICLE_TAG;
                }
            } else {
                return PARTING_LINE_TAG;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case RESTAURANT_TAG://推荐餐厅
                view = LayoutInflater.from(mContext).inflate(R.layout.rv_recommend_nearby_item, parent, false);
                holder = new RvSearchNearbyViewHolder(view);
                break;
            case ARTICLE_TAG://推荐文章
                view = LayoutInflater.from(mContext).inflate(R.layout.lv_article_item, parent, false);
                holder = new RvSearchArticleViewHolder(view);
                break;
            case DIET_TAG://推荐饮食
                view = LayoutInflater.from(mContext).inflate(R.layout.rv_recommend_recommend_item, parent, false);
                holder = new RvSearchRecommendViewHolder(view);
                break;
            case PARTING_LINE_TAG://分割线
                view = LayoutInflater.from(mContext).inflate(R.layout.adapter_parting_line, parent, false);
                holder = new PartingLineViewHolder(view);
                break;
        }
        return holder;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        LogUtil.i("嘻嘻嘻" + getItemViewType(position));
        switch (getItemViewType(position)) {


            //显示推荐文章
            case RESTAURANT_TAG:
                final int restaurantPos=position;
                final RvSearchNearbyViewHolder mHolder2 = (RvSearchNearbyViewHolder) holder;
                mHolder2.name.setText(mList01.get(restaurantPos).getName());
                mHolder2.address.setText(mList01.get(restaurantPos).getAddress());
                mHolder2.sales.setText("本月销售" + mList01.get(restaurantPos).getSales() + "份");
                mHolder2.consumption.setText("人均 ￥ " + mList01.get(restaurantPos).getConsumption() + "");
                mHolder2.discount.setText(mList01.get(restaurantPos).getDiscount() + "");
                int distance = (int) mList01.get(restaurantPos).getDistance();

                if (distance > 1000) {
                    distance = distance / 1000;
                    mHolder2.distance.setText("" + distance + "km");
                } else {
                    mHolder2.distance.setText("" + distance + "m");
                }


                Glide
                        .with(mContext)
                        .load(mList01.get(restaurantPos).getTitleImage())
                        .asBitmap()
                        .error(R.mipmap.error_photo)
                        .into(mHolder2.ivHead);


                mHolder2.layoutMain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mIn = new Intent(mContext, RestaurantDetailsActivity.class);
                        mIn.putExtra("id", mList01.get(restaurantPos).getId());
                        mContext.startActivity(mIn);
                    }
                });

                mHolder2.ivPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mList01.get(restaurantPos).getPhone()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });

                break;
            case DIET_TAG:
//                final RvSearchRecommendViewHolder mHolder3 = (RvSearchRecommendViewHolder) holder;
//                Glide
//                        .with(mContext)
//                        .load(mList03.get(position).getTitleImage())
//                        .asBitmap()
//                        .error(R.mipmap.error_photo)
//                        .into(mHolder3.ivHead);
//
////        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
////        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
////        holder.rvArticleTag.setLayoutManager(linearLayoutManager);
////        holder.rvArticleTag.setAdapter(new RvTagAdapter(mContext, mList.get(position).getTags()));
//
//                mHolder3.mHorizontalListView.setAdapter(new LvTagAdapter(mContext, mList03.get(position).getTags()));
//
//                mHolder3.name.setText(mList03.get(position).getName());
//                mHolder3.sales.setText(mList03.get(position).getSales() + "份");
//                mHolder3.price.setText(mList03.get(position).getPrice() + "");
//                mHolder3.Restaurant_Name.setText(mList03.get(position).getRestaurant_Name());
//                mHolder3.Restaurant_Address.setText(mList03.get(position).getRestaurant_Address());
//
//                mHolder3.layoutMain.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent mIntent = new Intent(mContext, RecipesDetailsActivity.class);
//                        mIntent.putExtra("RecipeId", mList03.get(position).getId() + "");
//                        mContext.startActivity(mIntent);
//                    }
//                });
//
//
//                int percentage = mList03.get(position).getConstitutionPercentage();
//
//                if (percentage > 90) {
//                    mHolder3.tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_9));
//                } else if (percentage > 80 & percentage <= 90) {
//                    mHolder3.tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_8));
//                } else if (percentage > 70 & percentage <= 80) {
//                    mHolder3.tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_7));
//                } else if (percentage > 60 & percentage <= 70) {
//                    mHolder3.tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_6));
//                } else {
//                    mHolder3.tvConstitutionPercentage.setTextColor(mContext.getResources().getColor(R.color.percentage_color_5));
//                }
//                mHolder3.tvConstitutionPercentage.setText(percentage + "%");


                break;
            case ARTICLE_TAG:

                showArticleType(position,holder);


                break;
        }


    }

    //显示文章列表
    private void showArticleType(int position, RecyclerView.ViewHolder holder) {
        int nowPosition = -1;


        if (mList01.size() > 0) {

            if (mList02.size() > 0) {
                //此时 position=mList01.size()+1+mList02.size()
                //那么此时position 在mList02在列表中的位置为
                nowPosition = position - mList01.size() - 1;
            } else {
                //此时不显示文章列表进入这里就出错了
                LogUtil.i("搜索结果出错啦");
            }
        } else {

            if (mList02.size() > 0) {
                //此时 position=1+mList02.size()
                //那么此时position 在mList02在列表中的位置为
                nowPosition = position - 1;
            } else {
                //此时不显示文章列表进入这里就出错了
                LogUtil.i("搜索结果出错啦");
            }

        }


        final RvSearchArticleViewHolder mHolder = (RvSearchArticleViewHolder) holder;
        Glide
                .with(mContext)
                .load(mList02.get(nowPosition).getTitleImage())
                .asBitmap()
                .error(R.mipmap.error_photo)
                .into(mHolder.ivHead);

        mHolder.tvClickCount.setText(mList02.get(nowPosition).getCilckCount() + "");
        mHolder.tvLoveCount.setText(mList02.get(nowPosition).getLoveCount() + "");
        mHolder.tvTitle.setText(mList02.get(nowPosition).getTitle());
        String mTime = mList02.get(nowPosition).getATime();
        mTime = mTime.replace("T", " ");
        mHolder.tvTime.setText(mTime);

        mHolder.lvTag.setAdapter(new LvTagAdapterForArticleList(mContext, mList02.get(nowPosition).getTags()));

        final int finalNowPosition = nowPosition;
        mHolder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, ArticleDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", mList02.get(finalNowPosition));
                bundle.putInt("pos", finalNowPosition);
                mIntent.putExtras(bundle);
                mContext.startActivity(mIntent);
            }
        });

        if (mList02.get(nowPosition).isPointPraise()) {
            mHolder.ivThumbsUp.setImageResource(R.mipmap.thumbs_up);
        } else {
            mHolder.ivThumbsUp.setImageResource(R.mipmap.thumbs_up_normal);
        }
    }

    @Override
    public int getItemCount() {

        if (mList01.size() > 0) {
            if (mList02.size() > 0) {
                return mList01.size() + mList02.size() + 1;
            } else {
                return mList01.size();
            }
        } else {
            if (mList02.size() > 0) {
                return mList02.size() + 1;
            } else {
                return 0;
            }
        }
    }


    //
    //文章列表的ViewHolder
    class RvSearchArticleViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvTime;
        LinearLayout mLinearLayout;
        RelativeLayout layoutMain;
        SelectableRoundedImageView ivHead;
        HorizontalListView lvTag;
        TextView tvLoveCount, tvClickCount;
        ImageView ivThumbsUp;

        RvSearchArticleViewHolder(View itemView) {
            super(itemView);
            ivHead = (SelectableRoundedImageView) itemView.findViewById(R.id.ivHead);
            lvTag = (HorizontalListView) itemView.findViewById(R.id.lvTag);
            tvLoveCount = (TextView) itemView.findViewById(R.id.tvLoveCount);
            tvClickCount = (TextView) itemView.findViewById(R.id.tvClickCount);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            layoutMain = (RelativeLayout) itemView.findViewById(R.id.layoutMain);
            ivThumbsUp = (ImageView) itemView.findViewById(R.id.ivThumbsUp);
//        mLinearLayout= (LinearLayout) itemView.findViewById(R.id.mLinearLayout);
        }
    }

    //附近餐厅ViewHolder
    class RvSearchNearbyViewHolder extends RecyclerView.ViewHolder {


        TextView tvName, tvTitle, name, images, address, phone, category, sales, consumption, discount, distance;
        LinearLayout layoutMain;
        ImageView ivPhone;

        SelectableRoundedImageView ivHead;

        RvSearchNearbyViewHolder(View itemView) {
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

    //推荐饮食ViewHolder
    class RvSearchRecommendViewHolder extends RecyclerView.ViewHolder {

        TextView name, sales, price, Restaurant_Name, Restaurant_Address, tvConstitutionPercentage;
        LinearLayout mLinearLayout;
        RecyclerView rvArticleTag;
        SelectableRoundedImageView ivHead;
        HorizontalListView mHorizontalListView;
        RelativeLayout layoutMain;

        RvSearchRecommendViewHolder(View itemView) {
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
//
    }

    class PartingLineViewHolder extends RecyclerView.ViewHolder {
        PartingLineViewHolder(View itemView) {
            super(itemView);
        }
    }

}

