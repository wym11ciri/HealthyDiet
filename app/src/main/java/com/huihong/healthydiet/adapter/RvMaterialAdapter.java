package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.bean.MaterialInfo;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 附近餐厅 RecyclerView
 */

public class RvMaterialAdapter extends RecyclerView.Adapter<RvMaterialViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<MaterialInfo> mList;

    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvMaterialAdapter(Context pContext, List<MaterialInfo> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvMaterialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.lv_material_item, parent, false);

        return new RvMaterialViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvMaterialViewHolder holder, int position) {

        holder.tvFoodInfo.setText(mList.get(position).getFoodInfo());

        String RecipeItemName = mList.get(position).getRecipeItemName();
        holder.tvRecipeItemName.setText(RecipeItemName);
        if (RecipeItemName.trim().equals("")) {
            holder.ivLine.setVisibility(View.INVISIBLE);
        } else {
            holder.ivLine.setVisibility(View.VISIBLE);
        }

        int like = mList.get(position).getWhetherLike();
        if (like == 0) {
            //都不喜欢
            holder.iv01.setImageResource(R.mipmap.recipes_1);
            holder.iv02.setImageResource(R.mipmap.recipes_6);
        } else if (like == 1) {
            //不喜欢
            holder.iv01.setImageResource(R.mipmap.recipes_1);
            holder.iv02.setImageResource(R.mipmap.recipes_2);
        } else if (like == 2) {
            //喜欢
            holder.iv01.setImageResource(R.mipmap.recipes_5);
            holder.iv02.setImageResource(R.mipmap.recipes_6);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class RvMaterialViewHolder extends RecyclerView.ViewHolder {

//    private String RecipeItemName;
//    private String FoodInfo;
//    private boolean WhetherLike;
//    private int id;

    TextView tvRecipeItemName, tvFoodInfo;
    ImageView iv01, iv02, ivLine;


    RvMaterialViewHolder(View itemView) {
        super(itemView);
        tvRecipeItemName = (TextView) itemView.findViewById(R.id.tvRecipeItemName);
        tvFoodInfo = (TextView) itemView.findViewById(R.id.tvFoodInfo);
        iv01 = (ImageView) itemView.findViewById(R.id.iv01);
        iv02 = (ImageView) itemView.findViewById(R.id.iv02);
        ivLine = (ImageView) itemView.findViewById(R.id.ivLine);
    }
}