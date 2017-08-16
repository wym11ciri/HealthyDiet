package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.model.mybean.IntegralDate;
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
    public void onBindViewHolder(ViewHolder holder, int position) {


        holder.tvWeek.setText(mList.get(position).getWeek());
        holder.tvTime.setText(mList.get(position).getMdate());
        holder.tvContent.setText(mList.get(position).getEvent());
        double num = mList.get(position).getNum();
        if (num < 0) {
            holder.tvNum.setText("" + mList.get(position).getNum());
        } else {
            holder.tvNum.setText("+" + mList.get(position).getNum());

        }

        String type = mList.get(position).getIconAdd();

        if (type.equals("sleep")) {
            holder.ivIcon.setImageResource(R.mipmap.integral_1);
        } else if (type.equals("sport")) {
            holder.ivIcon.setImageResource(R.mipmap.integral_5);
        } else if (type.equals("eat")) {
            holder.ivIcon.setImageResource(R.mipmap.integral_2);
        } else if (type.equals("System")) {
            holder.ivIcon.setImageResource(R.mipmap.integral_4);
        }


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
        if (mList.size() > 0) {
            position = position - 1;
            if (position < 0) {
                return mList.get(0).getKey();
            } else if (position == mList.size()) {
                return mList.get(position - 1).getKey();
            } else {
                return mList.get(position).getKey();
            }
        } else {
            return -1;
        }
    }


    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.rv_integral_header_item, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewHolder, int position) {

        long key = getHeaderId(position);
        if (key < 13&key>0) {
            viewHolder.tvKey.setText(key + "月");
        }else  if(key<=0){
            viewHolder.tvKey.setText("");
        } else {
            viewHolder.tvKey.setText(key + "年");
        }
    }


    //列表的ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime, tvWeek, tvContent, tvNum;
        ImageView ivIcon;

        ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvNum = (TextView) itemView.findViewById(R.id.tvNum);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvWeek = (TextView) itemView.findViewById(R.id.tvWeek);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
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
