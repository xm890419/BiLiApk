package com.atguigu.biliapk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.activity.GoodsInfoActivity;
import com.atguigu.biliapk.adapter.ComprehensiveAdapter;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.RecommendBean;
import com.atguigu.biliapk.mvp.presenter.LiveBoPresenter1;
import com.atguigu.biliapk.mvp.view.ILiveBoView1;
import com.atguigu.biliapk.utlis.Constants;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.atguigu.biliapk.fragment.FoundFragment.REQUEST_CODE;

/**
 * Created by 熊猛 on 2017/3/22.
 */

public class ComprehensiveFragment extends BaseFragment implements ILiveBoView1{
    @BindView(R.id.gv_zong)
    RecyclerView gvZong;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<RecommendBean.DataBean> datas;
    private ComprehensiveAdapter adapter;
    //private RecommendBean recommendBean;
    //private RxUtils rxUtils;

    private LiveBoPresenter1 presenter;

   // private ProgressDialog dialog;

    //private TextView textView;
    @Override
    protected View initView() {
        /*textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;*/
        View view = View.inflate(mContext, R.layout.fragment_comprehensive, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //rxUtils = new RxUtils();
        //textView.setText("综合");
        //dialog = new ProgressDialog(mContext);
        presenter = new LiveBoPresenter1(ComprehensiveFragment.this);
        //getDataFromNet();
        presenter.getDataFromNet1();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataFromNet1();
            }
        });
    }
    //private String path = Constants.RECOMMEND_URL;
    //private void getDataFromNet() {
        /*OkHttpUtils.get().url(Constants.RECOMMEND_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "" + response);
                processData(response);
                swipeRefreshLayout.setRefreshing(false);
            }
        });*/
        /*rxUtils.getNet(path).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onFailure"+e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("TAG", "onFailure"+s.toString());
                        processData(s);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });*/
    //}

    private void processData(RecommendBean recommendBean) {
        //recommendBean = JSON.parseObject(response, RecommendBean.class);
        datas = recommendBean.getData();
        Log.e("TAG", "解析数据成功==" + recommendBean.getData().get(0).getName().toString());
        adapter = new ComprehensiveAdapter(mContext, recommendBean.getData());
        gvZong.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        gvZong.setLayoutManager(manager);
        adapter.setOnItemClickListener(new ComprehensiveAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(mContext, "=="+position, Toast.LENGTH_SHORT).show();
                RecommendBean.DataBean dataBean = datas.get(position);
                String title = dataBean.getTitle();
                String desc = dataBean.getDesc();
                String cover = dataBean.getCover();
                Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("desc",desc);
                intent.putExtra("cover",cover);
                intent.putExtra("dataBean",dataBean);
                mContext.startActivity(intent);
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(mContext, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    for(int i =0 ;i<datas.size();i++){
                        if(result.equals(datas.get(i).getCover())){
                            RecommendBean.DataBean goodsBean = datas.get(i);
                            Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                            intent.putExtra("dataBean", goodsBean);
                            mContext.startActivity(intent);

                        }
                    }
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    /*@Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void showLoading() {
        dialog.show();
    }*/

    @Override
    public void hideRefreshing() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
            // 关闭的时候也使用
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }

    }

    @Override
    public void showRefreshing() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });

    }

    @Override
    public void onSuccess(RecommendBean recommendBean) {
        processData(recommendBean);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailed(Exception ex) {
        Toast.makeText(mContext, "联网请求失败", Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public String getUrl() {
        return Constants.RECOMMEND_URL;
    }
}
