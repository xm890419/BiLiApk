package com.atguigu.biliapk.video;

import android.os.Bundle;
import android.view.View;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.utlis.ConstantUtil;

/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p/>
 * 视频评论界面
 */
public class VideoCommentFragment extends BaseFragment {


  @Override
  protected View initView() {
    View view = View.inflate(mContext, R.layout.fragment_video_comment,null);
    return view;
  }

  public static VideoCommentFragment newInstance(int aid) {
    VideoCommentFragment fragment = new VideoCommentFragment();
    Bundle bundle = new Bundle();
    bundle.putInt(ConstantUtil.AID, aid);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void initData() {
    super.initData();
  }
}

