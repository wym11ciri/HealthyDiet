package com.huihong.healthydiet.activity;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihong.healthydiet.AppUrl;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.FragmentPagerAdapter;
import com.huihong.healthydiet.fragment.LikeFragment;
import com.huihong.healthydiet.mInterface.HttpUtilsListener;
import com.huihong.healthydiet.utils.common.LogUtil;
import com.huihong.healthydiet.utils.common.SPUtils;
import com.huihong.healthydiet.utils.current.HttpUtils;
import com.huihong.healthydiet.widget.MyViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zangyi_shuai_ge on 2017/7/20
 * 喜不喜欢
 */

public class PersonalPreferenceActivity extends BaseTitleActivity {

    private LinearLayout layoutLike, layoutDislike;
    private ImageView ivLike, ivDislike;
    private TextView tvLike, tvDislike;
    private MyViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mList;


    @Override
    public int setLayoutId() {
        return R.layout.activity_personal_preference;
    }

    @Override
    public void initUI() {
        setTitle("个人偏好");
        layoutLike = (LinearLayout) findViewById(R.id.layoutLike);
        layoutDislike = (LinearLayout) findViewById(R.id.layoutDislike);
        ivLike = (ImageView) findViewById(R.id.ivLike);
        ivDislike = (ImageView) findViewById(R.id.ivDislike);
        tvLike = (TextView) findViewById(R.id.tvLike);
        tvDislike = (TextView) findViewById(R.id.tvDislike);
        mViewPager = (MyViewPager) findViewById(R.id.mViewPager);

        mList = new ArrayList<>();
        mList.add(new LikeFragment());
        mList.add(new LikeFragment());

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mList);

        mViewPager.setAdapter(mAdapter);


        getInfo("foodunlike");
    }

    private void getInfo(String type) {
        Map<String, String> map = new HashMap<>();
        map.put("Type_Like",type);
        map.put("UserId",  SPUtils.get(PersonalPreferenceActivity.this,"UserId",0)+"");

        HttpUtils.sendHttpAddToken(PersonalPreferenceActivity.this, AppUrl.SELECT_USER_PREFERENCE
                , map
                , new HttpUtilsListener() {
                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.i("西部喜欢",response);

                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.i("西部喜欢",e.toString());
                    }
                });

    }

    @Override
    public void initOnClickListener() {

    }
}
