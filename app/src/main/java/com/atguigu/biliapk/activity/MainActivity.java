package com.atguigu.biliapk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.cfragment.AttentionPeopleFragment;
import com.atguigu.biliapk.cfragment.ConsumeHistoryFragment;
import com.atguigu.biliapk.cfragment.FavoritesFragment;
import com.atguigu.biliapk.cfragment.HistoryFragment;
import com.atguigu.biliapk.cfragment.LaterFragment;
import com.atguigu.biliapk.cfragment.SettingFragment;
import com.atguigu.biliapk.fragment.HomePageFragment;
import com.atguigu.biliapk.utlis.ConstantUtil;
import com.atguigu.biliapk.utlis.PreferenceUtil;
import com.atguigu.biliapk.view.CircleImageView;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.dl_left)
    DrawerLayout dlLeft;
    private List<BaseFragment> fragments;

    private int position;
    private int index;
    private ImageView SwitchMode;

    private Fragment[] fragment;

    private long exitTime;

    /*@Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        (((ViewGroup) findViewById(android.R.id.content)).getChildAt(0)).setBackgroundColor(getResources().getColor(
                true ? R.color.root_view_background_color_night
                        : R.color.root_view_background_color));
    }*/

    @Override
    public void initListener() {
    }


    @Override
    public void initData() {
        initFramgent();
        //初始化侧滑菜单
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        SwitchMode = (ImageView) headerView.findViewById(R.id.iv_head_switch_mode);
        initNavigationView();

    }

    private HomePageFragment homePageFragment;

    @Override
    public void initTitle() {

    }

    private void initFramgent() {
        homePageFragment = HomePageFragment.newInstance();
        LaterFragment laterFragment = LaterFragment.newInstance();
        FavoritesFragment favoritesFragment = FavoritesFragment.newInstance();
        HistoryFragment historyFragment = HistoryFragment.newInstance();
        AttentionPeopleFragment attentionPeopleFragment = AttentionPeopleFragment.newInstance();
        ConsumeHistoryFragment consumeHistoryFragment = ConsumeHistoryFragment.newInstance();
        SettingFragment settingFragment = SettingFragment.newInstance();

        fragment = new Fragment[]{
                homePageFragment,
                laterFragment,
                favoritesFragment,
                historyFragment,
                attentionPeopleFragment,
                consumeHistoryFragment,
                settingFragment
        };

        // 添加显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, homePageFragment)
                .show(homePageFragment).commit();
    }
    /**
     * 初始化NavigationView
     */
    private void initNavigationView() {

        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        CircleImageView mUserAvatarView = (CircleImageView) headerView.findViewById(
                R.id.user_avatar_view);
        TextView mUserName = (TextView) headerView.findViewById(R.id.user_name);
        TextView mUserSign = (TextView) headerView.findViewById(R.id.user_other_info);
        ImageView mSwitchMode = (ImageView) headerView.findViewById(R.id.iv_head_switch_mode);
        //设置头像
        mUserAvatarView.setImageResource(R.drawable.ico_user_default);
        //设置用户名 签名
        mUserName.setText("哔哩哔哩");
        mUserSign.setText("硬币；1000");
        //设置日夜间模式切换
        SwitchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchNightMode();

            }
        });

        boolean flag = PreferenceUtil.getBoolean(ConstantUtil.SWITCH_MODE_KEY, false);
        if (flag) {
            mSwitchMode.setImageResource(R.drawable.ic_switch_daily);
        } else {
            mSwitchMode.setImageResource(R.drawable.ic_switch_night);
        }
    }



    @Override
    public int getLayoutid() {
        return R.layout.activity_main;
    }

    /**
     * 日夜间模式切换
     */
    private void switchNightMode() {

        boolean isNight = PreferenceUtil.getBoolean(ConstantUtil.SWITCH_MODE_KEY, false);
        if (isNight) {
            // 日间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            PreferenceUtil.putBoolean(ConstantUtil.SWITCH_MODE_KEY, false);
        } else {
            // 夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            PreferenceUtil.putBoolean(ConstantUtil.SWITCH_MODE_KEY, true);
        }

        recreate();
    }

    /**
     * Fragment切换
     */
    private void switchFragment() {

        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        trx.hide(fragment[position]);
        if (!fragment[index].isAdded()) {
            trx.add(R.id.container, fragment[index]);
        }
        trx.show(fragment[index]).commit();
        position = index;
    }


    /**
     * 切换Fragment的下标
     */
    private void changeFragmentIndex(MenuItem item, int position) {

        index = position;
        switchFragment();
        item.setChecked(true);
    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    public void toggleDrawer() {

        if (dlLeft.isDrawerOpen(GravityCompat.START)) {
            dlLeft.closeDrawer(GravityCompat.START);
        } else {
            dlLeft.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        dlLeft.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.item_home:
                // 主页
                changeFragmentIndex(item, 0);
                return true;
            case R.id.item_vip:
                //大会员
                startActivity(new Intent(MainActivity.this, VipActivity.class));
                return true;
            case R.id.item_download:
                // 离线缓存
                startActivity(new Intent(MainActivity.this,DownloadActivity.class));
                return true;
            case R.id.item_jifen:
                // 会员积分
                Toast.makeText(MainActivity.this, " 会员积分", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item_later:
                // 稍后再看
                Toast.makeText(MainActivity.this, "稍后再看", Toast.LENGTH_SHORT).show();
                //changeFragmentIndex(item, 1);
                return true;

            case R.id.item_favourite:
                // 我的收藏
                changeFragmentIndex(item, 2);
                return true;

            case R.id.item_history:
                // 历史记录
                changeFragmentIndex(item, 3);
                return true;

            case R.id.item_group:
                // 我的关注
                changeFragmentIndex(item, 4);
                return true;

            case R.id.item_tracker:
                // B币钱包
                changeFragmentIndex(item, 5);
                return true;

            case R.id.item_theme:
                // 主题选择
                Toast.makeText(MainActivity.this, "主题选择", Toast.LENGTH_SHORT).show();
                // CardPickerDialog dialog = new CardPickerDialog();
                // dialog.setClickListener(this);
                // dialog.show(getSupportFragmentManager(), CardPickerDialog.TAG);
                return true;

            case R.id.item_settings:
                // 设置中心
                changeFragmentIndex(item,6);
                return true;
        }

        return false;
    }
    /**
     * 解决App重启后导致Fragment重叠的问题
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }
    /**
     * 监听back键处理DrawerLayout和SearchView
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (dlLeft.isDrawerOpen(dlLeft.getChildAt(1))) {
                dlLeft.closeDrawers();
            } else {
                if (homePageFragment != null) {
                    if (homePageFragment.isOpenSearchView()) {
                        homePageFragment.closeSearchView();
                    } else {
                        exitApp();
                    }
                } else {
                    exitApp();
                }
            }
        }

        return true;
    }
    /**
     * 双击退出App
     */
    private void exitApp() {

        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            PreferenceUtil.remove(ConstantUtil.SWITCH_MODE_KEY);
            finish();
        }
    }

}
