package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.model.gsonbean.MaterialInfo;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 附近餐厅 RecyclerView
 */

public class RvMaterialAdapter extends RecyclerView.Adapter<RvMaterialAdapter.RvMaterialViewHolder> {


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
    public void onBindViewHolder(final RvMaterialViewHolder holder, final int position) {

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
            //没有选
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

        //给西部喜欢设置点击事件

        //喜欢图标
        holder.iv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mList.get(holder.getAdapterPosition()).getWhetherLike() == 0) {
                    //原来没状态 点了喜欢变喜欢
                    holder.iv01.setImageResource(R.mipmap.recipes_5);
                    holder.iv02.setImageResource(R.mipmap.recipes_6);
                    mList.get(holder.getAdapterPosition()).setWhetherLike(2);
                    setLikeOrUnlike("foodlike","Insert",mList.get(position).getId()+"");
                } else if (mList.get(holder.getAdapterPosition()).getWhetherLike() == 1) {
                    //原来是不喜欢状态点了变喜欢
                    holder.iv01.setImageResource(R.mipmap.recipes_5);
                    holder.iv02.setImageResource(R.mipmap.recipes_6);
                    mList.get(holder.getAdapterPosition()).setWhetherLike(2);
                    setLikeOrUnlike("foodlike","Insert",mList.get(position).getId()+"");
                } else if (mList.get(holder.getAdapterPosition()).getWhetherLike() == 2) {
                    //原来是喜欢状态点了变没状态
                    holder.iv01.setImageResource(R.mipmap.recipes_1);
                    holder.iv02.setImageResource(R.mipmap.recipes_6);
                    mList.get(holder.getAdapterPosition()).setWhetherLike(0);
                    setLikeOrUnlike("foodlike","Delete",mList.get(position).getId()+"");
                }
            }
        });

        //不喜欢图标
        holder.iv02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mList.get(holder.getAdapterPosition()).getWhetherLike() == 0) {
                    //原来是没选的点了不喜欢
                    holder.iv01.setImageResource(R.mipmap.recipes_1);
                    holder.iv02.setImageResource(R.mipmap.recipes_2);
                    mList.get(holder.getAdapterPosition()).setWhetherLike(1);
                    setLikeOrUnlike("foodunlike","Insert",mList.get(position).getId()+"");

                } else if (mList.get(holder.getAdapterPosition()).getWhetherLike() == 1) {
                    //原来是不喜欢的点了无状态
                    holder.iv01.setImageResource(R.mipmap.recipes_1);
                    holder.iv02.setImageResource(R.mipmap.recipes_6);
                    mList.get(holder.getAdapterPosition()).setWhetherLike(0);
                    setLikeOrUnlike("foodunlike","Delete",mList.get(position).getId()+"");

                } else if (mList.get(holder.getAdapterPosition()).getWhetherLike() == 2) {
                    //原来是喜欢的点了变不喜欢
                    holder.iv01.setImageResource(R.mipmap.recipes_1);
                    holder.iv02.setImageResource(R.mipmap.recipes_2);
                    mList.get(holder.getAdapterPosition()).setWhetherLike(1);
                    setLikeOrUnlike("foodunlike","Insert",mList.get(position).getId()+"");
                }
            }
        });


    }

    private void setLikeOrUnlike(String Type_Like,String Opertion,String OtherId) {

        Map<String, String> map = new HashMap<>();
        map.put("Type_Like",Type_Like);
        map.put("OtherId",OtherId);
        map.put("Opertion",Opertion);
        map.put("UserId",  SPUtils.get(mContext,"UserId",0)+"");

        HttpUtils.sendHttpAddToken(mContext, AppUrl.CUSTOMER_LIKE_OR_NOT
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
//                        LogUtil.i("西部喜欢",response);

                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        LogUtil.i("西部喜欢",e.toString());
                    }
                });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

     class RvMaterialViewHolder extends RecyclerView.ViewHolder {
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
}

