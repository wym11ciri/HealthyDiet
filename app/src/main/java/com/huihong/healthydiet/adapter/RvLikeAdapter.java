package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.bean.CustomerLikeOrNot;
import com.huihong.healthydiet.bean.SelectUserPreference;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 */

public class RvLikeAdapter extends RecyclerView.Adapter<RvLikeAdapter.RvTestViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<SelectUserPreference.ListDataBean> mList;
    private  String Type_Like;

    private ItemOnClickListener mItemOnClickListener;
    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvLikeAdapter(String Type_Like, Context pContext, List<SelectUserPreference.ListDataBean> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
        this.Type_Like=Type_Like;
    }


    @Override
    public RvTestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_islike_item, parent, false);

        return new RvTestViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvTestViewHolder holder, final int position) {
        holder.tvName.setText(mList.get(position).getFoodName());
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFood(mList.get(position).getFoodId(),position);
            }
        });

//        ImageLoderUtil.showImage(mContext,"",holder.ivHead);
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

    private void deleteFood(int foodId, final int pos) {
        Map<String, String> map = new HashMap<>();
        map.put("Type_Like",Type_Like);
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
                        if(mCustomerLikeOrNot.getHttpCode()==200){
                            mList.remove(pos);
                            RvLikeAdapter.this.notifyDataSetChanged();
                        }
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
    class  RvTestViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        Button btDelete;

        RvTestViewHolder(View itemView) {
            super(itemView);
//        ivHead= (SelectableRoundedImageView) itemView.findViewById(R.id.ivHead);
//        rvArticleTag= (RecyclerView) itemView.findViewById(R.id.rvArticleTag);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            btDelete = (Button) itemView.findViewById(R.id.btDelete);
//        mLinearLayout= (LinearLayout) itemView.findViewById(R.id.mLinearLayout);
        }
    }
}

