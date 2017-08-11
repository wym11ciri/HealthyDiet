package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.model.mybean.IntegralDate;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.widget.StickyHeaderAdapter;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/27
 */
public class StickyTestAdapter extends RecyclerView.Adapter<StickyTestAdapter.ViewHolder>
        implements StickyHeaderAdapter<StickyTestAdapter.HeaderHolder> {

    private LayoutInflater mInflater;
    private Context mContext;


    private List<IntegralDate> mList;

    public StickyTestAdapter(Context context, List<IntegralDate> pList) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = pList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = mInflater.inflate(R.layout.rv_integral_detail_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
//        viewHolder.item.setText("Item " + mList.get(position).getMdate());
//
//        LogUtil.i("item222" + position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public long getHeaderId(int position) {
        //他会去判断上次得到的id和本次是否一样
        //不一样则绘制Header

        LogUtil.i("zangyiiii", position + "");

//        if (position < mList.size()) {
//            return position;

//        } else {
//            return 0;
//        }
        //这里表示在列表第几项目时候需要创建Id


//        if(position>=0&&position<5){
//            return 1;
//        }else  if(position>=5&&position<8){
//            return 2;
//        }else  if(position>=9&&position<15){
//            return 3;
//        }else  if(position>=15&&position<18){
//            return 4;
//        }else {
//            return  5;
//        }



        if(mList.size()>0){
            position = position - 1;
            if (position < 0) {
                return mList.get(0).getKey();
            } else if (position == mList.size()) {
                return mList.get(position - 1).getKey();
            } else {
                return mList.get(position).getKey();
            }
        }else {
            return -1;
        }
        //当position==0的时候绘制最顶部的header




    }


    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.rv_integral_header_item, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewHolder, int position) {

        long key=getHeaderId(position);
        if(key<13){
            viewHolder.tvKey.setText(key+"月");
        }else {
            viewHolder.tvKey.setText(key+"年");
        }
    }


    //列表的ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {
//        TextView item;

        ViewHolder(View itemView) {
            super(itemView);
//            item = (TextView) itemView;
        }
    }

    //头布局的ViewHolder
    class HeaderHolder extends RecyclerView.ViewHolder {
        TextView tvKey;

        HeaderHolder(View itemView) {
            super(itemView);
            tvKey = (TextView) itemView.findViewById(R.id.tvKey);
        }
    }
}
