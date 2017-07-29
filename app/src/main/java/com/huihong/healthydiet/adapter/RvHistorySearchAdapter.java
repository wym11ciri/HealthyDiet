package com.huihong.healthydiet.adapter;

import android.content.Context;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huihong.healthydiet.R;
import com.huihong.healthydiet.cache.litepal.SearchHistory;
import com.huihong.healthydiet.mInterface.ItemOnClickListener;
import com.huihong.healthydiet.utils.common.DensityUtils;
import com.huihong.healthydiet.utils.common.ScreenUtils;

import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/5/16
 * 热门搜索列表
 */

public class RvHistorySearchAdapter extends RecyclerView.Adapter<RvHistorySearchAdapter.RvHistorySearchViewHolder> {


    private LayoutInflater mInflater;
    private Context mContext;
    private List<SearchHistory> mList;

    private ItemOnClickListener mItemOnClickListener;

    public void setItemOnClickListener(ItemOnClickListener pItemOnClickListener) {
        mItemOnClickListener = pItemOnClickListener;
    }


    public RvHistorySearchAdapter(Context pContext, List<SearchHistory> pList) {
        mList = pList;
        mContext = pContext;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public RvHistorySearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mInflater.inflate(R.layout.rv_history_search_item, parent, false);

        return new RvHistorySearchViewHolder(mView);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final RvHistorySearchViewHolder holder, final int position) {

        holder.tvName.setText(mList.get(position).getSearchHistory());
        holder.layoutMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                showListPopup(v,holder.getAdapterPosition());

                mList.get(holder.getAdapterPosition()).delete();
                mList.remove(holder.getAdapterPosition());
                RvHistorySearchAdapter.this.notifyDataSetChanged();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

   //显示一个删除按钮菜单
    private void showListPopup(View view, final int mPosition) {

        final ListPopupWindow listPopupWindow = new ListPopupWindow(mContext);
        //设置ListView类型的适配器
        listPopupWindow.setAdapter(new LvHistoryDeleteAdapter(mContext));
        //给每个item设置监听事件
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        //设置ListPopupWindow的锚点,也就是弹出框的位置是相对当前参数View的位置来显示，
        listPopupWindow.setAnchorView(view);
        //设置对话框的宽高
        listPopupWindow.setHorizontalOffset(ScreenUtils.getScreenWidth(mContext)/2);
        listPopupWindow.setWidth(DensityUtils.dp2px(mContext,60));
        listPopupWindow.setHeight(DensityUtils.dp2px(mContext,25));
        listPopupWindow.setModal(false);
        listPopupWindow.show();

    }

    class RvHistorySearchViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        RelativeLayout layoutMain;


        RvHistorySearchViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            layoutMain = (RelativeLayout) itemView.findViewById(R.id.layoutMain);
        }
    }
}


