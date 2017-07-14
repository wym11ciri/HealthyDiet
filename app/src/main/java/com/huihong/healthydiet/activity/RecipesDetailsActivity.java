package com.huihong.healthydiet.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.eicky.ViewPagerGallery;
import com.huihong.healthydiet.R;
import com.huihong.healthydiet.activity.base.BaseTitleActivity;
import com.huihong.healthydiet.adapter.RvMaterialAdapter;
import com.huihong.healthydiet.adapter.RvTagAdapter;
import com.huihong.healthydiet.widget.PageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zangyi_shuai_ge on 2017/7/13
 */

public class RecipesDetailsActivity extends BaseTitleActivity {

    private ViewPagerGallery gallery;
    PageIndicator mPageIndicator;


    private RecyclerView rvMaterial;
    private RvMaterialAdapter mRvMaterialAdapter;
    private List<String> materialList;

    @Override
    public int setLayoutId() {
        return R.layout.activity_recipes_details;
    }

    @Override
    public void initUI() {

        setTitle("鸡蛋蔬菜沙拉");

        gallery = (ViewPagerGallery) findViewById(R.id.gallery);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
//      int id = getResources().getIdentifier("img" + i, "mipmap", getPackageName());
            list.add(i);
        }
        gallery.setImgResources(list);


        mPageIndicator = (com.huihong.healthydiet.widget.PageIndicator) findViewById(R.id.PageIndicator);
        mPageIndicator.setViewPager(gallery);//给ViewPager设置指示器
        mPageIndicator.setIndicatorType(PageIndicator.IndicatorType.CIRCLE);
//        gallery.setCurrentItem(1);
//        ViewPager vp = (ViewPager) findViewById(R.id.viewPager);
//        vp.setOffscreenPageLimit(10);
//
//        //左右都缩进一个合适的值
//        vp.setPageMargin((int) (-getResources().getDisplayMetrics().widthPixels / 3 * 2 * 0.9));
//
//        //中间大 ,两边大小渐变的动画变化,最大1.4倍
//        vp.setPageTransformer(true, new ViewPager.PageTransformer() {
//            public void transformPage(View page, float position) {
//
//                if (position < -1) {
//                    // This page is way off-screen to the left.
//                } else if (position <= 1) {
//
//                    if (position < 0) {
//                        if (position < -0.4f)
//                            position = -0.4f;
//                        page.setScaleY(1.4f + position);
//                        page.setScaleX(1.4f + position);
//                    } else {
//                        if (position > 0.4)
//                            position = 0.4f;
//                        page.setScaleY(1.4f - position);
//                        page.setScaleX(1.4f - position);
//                    }
//
//                } else {
//                    // This page is way off-screen to the right.
//                }
//
//            }
//        });
//
//        //new6个Fragment
//        final ArrayList<FragmentPage> list = new ArrayList<>();
//        for (int i = 0; i < 6; i++) {
//            list.add(new FragmentPage());
//        }
//
//        //
//        //设置Adapter
//        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            public Fragment getItem(int position) {
//                return list.get(position);
//            }
//
//            public int getCount() {
//                return list.size();
//            }
//        });
        initMaterial();


        List<String> zz = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            zz.add("红烧啊");
        }
        RecyclerView rvArticleTag = (RecyclerView) findViewById(R.id.rvArticleTag);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipesDetailsActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvArticleTag.setLayoutManager(linearLayoutManager);
        rvArticleTag.setAdapter(new RvTagAdapter(RecipesDetailsActivity.this, zz));


        ImageView ivBuy= (ImageView) findViewById(R.id.ivBuy);
        ivBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIn=new Intent(RecipesDetailsActivity.this,PayActivity.class);
                startActivity(mIn);
            }
        });
//        RecyclerView rvTag = (RecyclerView) findViewById(rvArticleTag);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RecipesDetailsActivity.this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        rvTag.setLayoutManager(linearLayoutManager);
//        rvTag.setAdapter(new RvTypeAdapter(RecipesDetailsActivity.this, zz));



    }

    private void initMaterial() {
        rvMaterial = (RecyclerView) findViewById(R.id.rvMaterial);
        materialList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            materialList.add("主食" + i);
        }
        mRvMaterialAdapter = new RvMaterialAdapter(RecipesDetailsActivity.this, materialList);

        rvMaterial.setLayoutManager(new LinearLayoutManager(RecipesDetailsActivity.this, LinearLayoutManager.VERTICAL, false));

        rvMaterial.setAdapter(mRvMaterialAdapter);
//        mLvMaterialAdapter=new LvMaterialAdapter(RecipesDetailsActivity.this,materialList);
//        rvMaterial.setAdapter(mLvMaterialAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void initOnClickListener() {

    }
}
