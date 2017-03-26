package com.atguigu.biliapk.cfragment;

import android.app.Activity;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.activity.MainActivity;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.view.CustomEmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/25.
 */

public class LaterFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.empty_view)
    CustomEmptyView emptyView;

    public static LaterFragment newInstance() {
        return new LaterFragment();
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_later, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        toolbar.setTitle("稍后再看");
        toolbar.setNavigationIcon(R.drawable.ic_navigation_drawer);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Activity activity1 = getActivity();
                if (activity1 instanceof MainActivity) {
                    ((MainActivity) activity1).toggleDrawer();
                }

            }

        });

        emptyView.setEmptyImage(R.drawable.ic_empty_cute_girl_box);
        emptyView.setEmptyText("没有找到你的观看记录哟");
    }
}
