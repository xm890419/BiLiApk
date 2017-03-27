package com.atguigu.biliapk.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.biliapk.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/2/28.
 */

public class AddSubView extends LinearLayout {

    @BindView(R.id.iv_sub)
    ImageView ivSub;
    @BindView(R.id.tv_value)
    TextView tvValue;
    @BindView(R.id.iv_add)
    ImageView ivAdd;
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tvValue.setText(value+"");
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //把布局文件实例化成View,并且添加到AddSubView.this类中，成为他的子视图
        View.inflate(context, R.layout.add_sub_view, AddSubView.this);
        ButterKnife.bind(this);
        //设置点击事件
        ivSub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //不能小于最小值
                if(value > minValue) {
                    value--;
                }
                setValue(value);
                if(listener != null) {
                    listener.onNumberChanger(value);
                }
            }
        });
        ivAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value < maxValue) {
                    value++;
                }
                setValue(value);
                if(listener != null) {
                    listener.onNumberChanger(value);
                }
            }
        });
        if(attrs != null) {
            //取出属性
            TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context,attrs,R.styleable.AddSubView);
            int value = tintTypedArray.getInt(R.styleable.AddSubView_value,0);
            if(value>0) {
                setValue(value);
            }
            int minValue = tintTypedArray.getInt(R.styleable.AddSubView_minValue,0);
            if(value>0) {
                setValue(minValue);
            }
            int maxValue = tintTypedArray.getInt(R.styleable.AddSubView_maxValue,0);
            if(value>0) {
                setValue(maxValue);
            }
            Drawable addDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberAddBackground);
            if(addDrawable != null) {
                ivAdd.setImageDrawable(addDrawable);
            }
            Drawable subDrawable = tintTypedArray.getDrawable(R.styleable.AddSubView_numberSubBackground);
            if(subDrawable != null) {
                ivSub.setImageDrawable(subDrawable);
            }
        }
    }
    //定义一个接口
    public interface OnNumberChangerListener{
        public void onNumberChanger(int value);
    }
    private OnNumberChangerListener listener;

    public void setOnNumberChangerListener(OnNumberChangerListener listener) {
        this.listener = listener;
    }
}
