package com.jyt.baseapp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jyt.baseapp.R;
import com.jyt.baseapp.adapter.FragmentViewPagerAdapter;
import com.jyt.baseapp.view.fragment.BaseFragment;
import com.jyt.baseapp.view.fragment.MapFragment;
import com.jyt.baseapp.view.fragment.MoreFragment;
import com.jyt.baseapp.view.fragment.ProjectFragment;
import com.jyt.baseapp.view.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author LinWei on 2017/10/30 14:32
 */
public class ContentActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.vp_content_container)
    NoScrollViewPager mVpContainer;
    @BindView(R.id.iv_content_map)
    ImageView mIvMap;
    @BindView(R.id.tv_content_map)
    TextView mTvMap;
    @BindView(R.id.ll_content_map)
    LinearLayout mLlMap;
    @BindView(R.id.iv_content_project)
    ImageView mIvProject;
    @BindView(R.id.tv_content_project)
    TextView mTvProject;
    @BindView(R.id.ll_content_project)
    LinearLayout mLlProject;
    @BindView(R.id.iv_content_more)
    ImageView mIvMore;
    @BindView(R.id.tv_content_more)
    TextView mTvMore;
    @BindView(R.id.ll_content_more)
    LinearLayout mLlMore;
    private List<BaseFragment> flist;
    private MapFragment mMapFragment;
    private ProjectFragment mProjectFragment;
    private MoreFragment mMoreFragment;
    private FragmentViewPagerAdapter vpAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_content;
    }

    @Override
    protected View getContentView() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideActionBar();
        init();
        initlistener();
        setOffBg(0);
    }

    private void init() {
        flist=new ArrayList<>();
        mMapFragment=new MapFragment();
        mProjectFragment=new ProjectFragment();
        mMoreFragment=new MoreFragment();
        flist.add(mMapFragment);
        flist.add(mProjectFragment);
        flist.add(mMoreFragment);
        vpAdapter=new FragmentViewPagerAdapter(getSupportFragmentManager());
        vpAdapter.setFragments(flist);
        mVpContainer.setAdapter(vpAdapter);
        mVpContainer.setOffscreenPageLimit(3);
    }


    private void initlistener(){
        mLlMap.setOnClickListener(this);
        mLlProject.setOnClickListener(this);
        mLlMore.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_content_map:
                setOffBg(0);
                break;
            case R.id.ll_content_project:
                setOffBg(1);
                break;
            case R.id.ll_content_more:
                setOffBg(2);
                break;
            default:
                break;
        }
    }

    /**
     * 下方Tab的点击处理
     * @param selecor
     */
    public void setOffBg(int selecor){
        mIvMap.setImageDrawable(getResources().getDrawable(R.mipmap.map_off));
        mIvProject.setImageDrawable(getResources().getDrawable(R.mipmap.project_off));
        mIvMore.setImageDrawable(getResources().getDrawable(R.mipmap.more_off));
        mTvMap.setTextColor(getResources().getColor(R.color.vp_text));
        mTvProject.setTextColor(getResources().getColor(R.color.vp_text));
        mTvMore.setTextColor(getResources().getColor(R.color.vp_text));
        switch (selecor) {
            case 0:
                mIvMap.setImageDrawable(getResources().getDrawable(R.mipmap.map_on));
                mTvMap.setTextColor(getResources().getColor(R.color.white));
                break;
            case 1:
                mIvProject.setImageDrawable(getResources().getDrawable(R.mipmap.project_on));
                mTvProject.setTextColor(getResources().getColor(R.color.white));
                break;
            case 2:
                mIvMore.setImageDrawable(getResources().getDrawable(R.mipmap.more_on));
                mTvMore.setTextColor(getResources().getColor(R.color.white));
                break;
            default:
                break;
        }
        mVpContainer.setCurrentItem(selecor);

    }
}