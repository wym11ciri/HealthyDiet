package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
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
import com.huihong.healthydiet.activity.RecipesDetailsActivity;
import com.huihong.healthydiet.bean.RecipeListInfoByDRId;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 附近餐厅 RecyclerView
 */

public class RvRecipesAdapter extends RecyclerView.Adapter<RvRecipesViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<RecipeListInfoByDRId.ListDataBean> mList;

    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvRecipesAdapter(Context pContext, List<RecipeListInfoByDRId.ListDataBean> pList) {
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
    public void onBindViewHolder(final RvRecipesViewHolder holder, final int position) {


        final List<String> mImages = mList.get(position).getImages();
        if (mImages.size() > 0) {
            Glide
                    .with(mContext)
                    .load(mImages.get(0))
                    .asBitmap()
                    .error(R.mipmap.error_photo)
                    .into(holder.ivHead);
        }

        holder.tvName.setText(mList.get(position).getName());
        holder.tvPrice.setText(mList.get(position).getPrice());

        holder.tvSales.setText(mList.get(position).getSales() + "份");

//
        List<String> constitutionList = mList.get(position).getConstitution();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rvArticleTag.setLayoutManager(linearLayoutManager);
        holder.rvArticleTag.setAdapter(new RvTypeAdapter(mContext, constitutionList));


        List<RecipeListInfoByDRId.ListDataBean.FoodRecipeBean> mFoodRecipe = mList.get(position).getFoodRecipe();
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext);
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        holder.rvFoodRecipe.setLayoutManager(linearLayoutManager2);
        holder.rvFoodRecipe.setAdapter(new RvFoodRecipeAdapter(mContext, mFoodRecipe));

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
                Intent mIn = new Intent(mContext, RecipesDetailsActivity.class);
                mIn.putExtra("RecipeId",mList.get(position).getId()+"");
                mContext.startActivity(mIn);
            }
        });


        if (mList.get(position).isShow()) {
            holder.viewLine.setVisibility(View.VISIBLE);
            holder.rvFoodRecipe.setVisibility(View.VISIBLE);
            holder.ivIsShow.setImageResource(R.mipmap.restaurant_11);
        } else {
            holder.viewLine.setVisibility(View.GONE);
            holder.rvFoodRecipe.setVisibility(View.GONE);
            holder.ivIsShow.setImageResource(R.mipmap.restaurant_12);
        }

        holder.layoutIsShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mList.get(position).isShow()){
                    mList.get(position).setShow(false);
                    holder.rvFoodRecipe.setVisibility(View.GONE);
                    holder.ivIsShow.setImageResource(R.mipmap.restaurant_12);
                    holder.viewLine.setVisibility(View.GONE);
                }else {
                    mList.get(position).setShow(true);
                    holder.rvFoodRecipe.setVisibility(View.VISIBLE);
                    holder.ivIsShow.setImageResource(R.mipmap.restaurant_11);
                    holder.viewLine.setVisibility(View.VISIBLE);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class RvRecipesViewHolder extends RecyclerView.ViewHolder {

    TextView tvName, tvPrice, tvSales;
    RelativeLayout layoutMain;

    RecyclerView rvArticleTag, rvFoodRecipe;
    SelectableRoundedImageView ivHead;
    ImageView ivIsShow;
    LinearLayout layoutIsShow;
    View viewLine;

    RvRecipesViewHolder(View itemView) {
        super(itemView);
        ivHead = (SelectableRoundedImageView) itemView.findViewById(R.id.ivHead);
        rvArticleTag = (RecyclerView) itemView.findViewById(R.id.rvArticleTag);
        rvFoodRecipe = (RecyclerView) itemView.findViewById(R.id.rvFoodRecipe);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        tvSales = (TextView) itemView.findViewById(R.id.tvSales);
        layoutMain = (RelativeLayout) itemView.findViewById(R.id.layoutMain);
        viewLine = itemView.findViewById(R.id.viewLine);
        ivIsShow= (ImageView) itemView.findViewById(R.id.ivIsShow);
        layoutIsShow= (LinearLayout) itemView.findViewById(R.id.layoutIsShow);
    }
}