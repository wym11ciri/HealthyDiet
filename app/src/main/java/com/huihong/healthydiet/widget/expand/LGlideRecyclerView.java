package com.huihong.healthydiet.widget.expand;

import android.content.Context;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;

/**
 * Created by zangyi_shuai_ge on 2017/8/25
 * 监听RecyclerView事件控制Glide加载
 */

public class LGlideRecyclerView extends LRecyclerView {
    private Context mContext;

    public LGlideRecyclerView(Context context) {
        super(context);
        mContext = context;
    }

    public LGlideRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public LGlideRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        switch (state) {
            case SCROLL_STATE_SETTLING:
//                Log.i("Main","用户在手指离开屏幕之前，由于滑了一下，视图仍然依靠惯性继续滑动");
                Glide.with(mContext.getApplicationContext()).pauseRequests();
                break;
            case SCROLL_STATE_IDLE:
                //RecyclerView不滚动的时候
                //Log.i("Main", "视图已经停止滑动");
                Glide.with(mContext.getApplicationContext()).resumeRequests();
                break;
            case SCROLL_STATE_DRAGGING:
//                Log.i("Main","手指没有离开屏幕，视图正在滑动");
                Glide.with(mContext.getApplicationContext()).resumeRequests();
                break;
        }
    }
}
