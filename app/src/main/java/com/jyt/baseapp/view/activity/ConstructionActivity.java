package com.jyt.baseapp.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jyt.baseapp.R;
import com.jyt.baseapp.adapter.FragmentViewPagerAdapter;
import com.jyt.baseapp.view.fragment.FinishConstructionFragment;
import com.jyt.baseapp.view.fragment.UnderConstructionFragment;
import com.jyt.baseapp.view.fragment.ViewConstructFragment;
import com.jyt.baseapp.view.widget.NoScrollViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 有权限操作对页面
 * 查看施工 施工中 施工完毕
 * Created by v7 on 2017/11/4.
 */

public class ConstructionActivity extends BaseActivity {
    @BindView(R.id.v_tabLayout)
    TabLayout vTabLayout;
    @BindView(R.id.v_viewPager)
    NoScrollViewPager vViewPager;

    FragmentViewPagerAdapter adapter;

    ViewConstructFragment viewConstructFragment;
    UnderConstructionFragment underConstructionFragment;
    FinishConstructionFragment finishConstructionFragment;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_construction;
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        vViewPager.setAdapter(adapter = new FragmentViewPagerAdapter(getSupportFragmentManager()));
        vViewPager.setOffscreenPageLimit(3);
        vTabLayout.setupWithViewPager(vViewPager);

        adapter.addFragment(viewConstructFragment = new ViewConstructFragment(),"查看施工");
        adapter.addFragment(underConstructionFragment = new UnderConstructionFragment(),"施工中");
        adapter.addFragment(finishConstructionFragment = new FinishConstructionFragment(),"施工完毕");
        adapter.notifyDataSetChanged();

        vViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTextTitle(adapter.getTitles().get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vViewPager.setCurrentItem(0);
    }

}