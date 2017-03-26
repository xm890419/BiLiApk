package com.atguigu.biliapk.cfragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.activity.LoginActivity;
import com.atguigu.biliapk.activity.MainActivity;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.utlis.ConstantUtil;
import com.atguigu.biliapk.utlis.PreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 熊猛 on 2017/3/25.
 */

public class SettingFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.layout_about_me)
    RelativeLayout layoutAboutMe;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.layout_about_app1)
    RelativeLayout layoutAboutApp1;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.layout_about_app2)
    RelativeLayout layoutAboutApp2;
    @BindView(R.id.layout_about_me1)
    RelativeLayout layoutAboutMe1;
    @BindView(R.id.layout_about_app3)
    RelativeLayout layoutAboutApp3;
    @BindView(R.id.layout_about_app4)
    RelativeLayout layoutAboutApp4;
    @BindView(R.id.layout_about_me2)
    RelativeLayout layoutAboutMe2;
    @BindView(R.id.layout_about_app5)
    RelativeLayout layoutAboutApp5;
    @BindView(R.id.layout_about_app6)
    RelativeLayout layoutAboutApp6;
    @BindView(R.id.layout_about_app7)
    RelativeLayout layoutAboutApp7;
    @BindView(R.id.layout_about_app8)
    RelativeLayout layoutAboutApp8;
    @BindView(R.id.app_version_code)
    TextView appVersionCode;
    @BindView(R.id.layout_update)
    RelativeLayout layoutUpdate;
    @BindView(R.id.btn_logout)
    Button btnLogout;

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_setting, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        toolbar.setTitle("设置与帮助");
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
        appVersionCode.setText("v" + getVersionCode());
    }

    public String getVersionCode() {

        PackageInfo packageInfo = null;
        try {
            packageInfo = getActivity().getPackageManager()
                    .getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        assert packageInfo != null;
        return packageInfo.versionName;
    }

    @OnClick(R.id.btn_logout)
    public void onClick() {
        //退出登录
        PreferenceUtil.putBoolean(ConstantUtil.KEY, false);
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}
