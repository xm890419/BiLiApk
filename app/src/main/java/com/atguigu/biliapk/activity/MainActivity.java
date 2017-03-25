package com.atguigu.biliapk.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.cfragment.AttentionPeopleFragment;
import com.atguigu.biliapk.cfragment.ConsumeHistoryFragment;
import com.atguigu.biliapk.cfragment.FavoritesFragment;
import com.atguigu.biliapk.cfragment.HistoryFragment;
import com.atguigu.biliapk.cfragment.SettingFragment;
import com.atguigu.biliapk.fragment.HomePageFragment;
import com.atguigu.biliapk.utlis.CacheUtils;
import com.atguigu.biliapk.utlis.ConstantUtil;

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

    /*@Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        (((ViewGroup) findViewById(android.R.id.content)).getChildAt(0)).setBackgroundColor(getResources().getColor(
                true ? R.color.root_view_background_color_night
                        : R.color.root_view_background_color));
    }*/

    @Override
    public void initListener() {
        SwitchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchNightMode();

            }
        });
        boolean flag = CacheUtils.getBoolean(this, ConstantUtil.SWITCH_MODE_KEY);
        if (flag) {
            SwitchMode.setImageResource(R.drawable.ic_switch_daily);
        } else {
            SwitchMode.setImageResource(R.drawable.ic_switch_night);
        }
    }


    @Override
    public void initData() {
        initFramgent();
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        SwitchMode = (ImageView) headerView.findViewById(R.id.iv_yue);

    }

    private HomePageFragment homePageFragment;

    @Override
    public void initTitle() {

    }

    private void initFramgent() {
        homePageFragment = HomePageFragment.newInstance();
        FavoritesFragment favoritesFragment = FavoritesFragment.newInstance();
        HistoryFragment historyFragment = HistoryFragment.newInstance();
        AttentionPeopleFragment attentionPeopleFragment = AttentionPeopleFragment.newInstance();
        ConsumeHistoryFragment consumeHistoryFragment = ConsumeHistoryFragment.newInstance();
        SettingFragment settingFragment = SettingFragment.newInstance();

        fragment = new Fragment[]{
                homePageFragment,
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


    @Override
    public int getLayoutid() {
        return R.layout.activity_main;
    }

    /**
     * 日夜间模式切换
     */
    private void switchNightMode() {

        boolean isNight = CacheUtils.getBoolean(this, ConstantUtil.SWITCH_MODE_KEY);
        if (isNight) {
            // 日间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            CacheUtils.putBoolean(this, ConstantUtil.SWITCH_MODE_KEY, false);
        } else {
            // 夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            CacheUtils.putBoolean(this, ConstantUtil.SWITCH_MODE_KEY, true);
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

            case R.id.item_download:
                // 离线缓存
                //startActivity(new Intent(MainActivity.this, OffLineDownloadActivity.class));
                return true;

            case R.id.item_vip:
                //大会员
                //startActivity(new Intent(MainActivity.this, VipActivity.class));
                return true;

            case R.id.item_favourite:
                // 我的收藏
                changeFragmentIndex(item, 1);
                return true;

            case R.id.item_history:
                // 历史记录
                changeFragmentIndex(item, 2);
                return true;

            case R.id.item_group:
                // 关注的人
                changeFragmentIndex(item, 3);
                return true;

            case R.id.item_tracker:
                // 我的钱包
                changeFragmentIndex(item, 4);
                return true;

            case R.id.item_theme:
                // 主题选择
                // CardPickerDialog dialog = new CardPickerDialog();
                // dialog.setClickListener(this);
                // dialog.show(getSupportFragmentManager(), CardPickerDialog.TAG);
                return true;

            case R.id.item_app:
                // 应用推荐

                return true;

            case R.id.item_settings:
                // 设置中心
                changeFragmentIndex(item, 5);
                return true;
        }

        return false;
    }
}
