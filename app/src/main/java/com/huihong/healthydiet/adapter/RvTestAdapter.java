package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 */

public class RvTestAdapter extends RecyclerView.Adapter<RvTestViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mList;

    private ItemOnClickListener mItemOnClickListener;
    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvTestAdapter(Context pContext, List<String> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
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
    public void onBindViewHolder(final RvTestViewHolder holder, int position) {
        holder.tvName.setText(mList.get(position));

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

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class  RvTestViewHolder extends RecyclerView.ViewHolder {

    TextView tvName,tvTitle;

    RvTestViewHolder(View itemView) {
        super(itemView);
//        ivHead= (SelectableRoundedImageView) itemView.findViewById(R.id.ivHead);
//        rvArticleTag= (RecyclerView) itemView.findViewById(R.id.rvArticleTag);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
//        mLinearLayout= (LinearLayout) itemView.findViewById(R.id.mLinearLayout);
    }
}