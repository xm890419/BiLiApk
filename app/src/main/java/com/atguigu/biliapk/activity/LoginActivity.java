package com.atguigu.biliapk.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.utlis.CommonUtil;
import com.atguigu.biliapk.utlis.ConstantUtil;
import com.atguigu.biliapk.utlis.PreferenceUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static com.atguigu.biliapk.R.id.et_password;
import static com.atguigu.biliapk.R.id.et_username;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_icon_left)
    ImageView ivIconLeft;
    @BindView(R.id.iv_icon_centre)
    ImageView ivIconCentre;
    @BindView(R.id.iv_icon_right)
    ImageView ivIconRight;
    @BindView(R.id.logo_ll)
    RelativeLayout logoLl;
    @BindView(R.id.delete_username)
    ImageButton deleteUsername;
    @BindView(R.id.login_ll)
    LinearLayout loginLl;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(et_username)
    EditText etUsername;
    @BindView(et_password)
    EditText etPassword;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && etUsername.getText().length() > 0) {
                    deleteUsername.setVisibility(View.VISIBLE);
                } else {
                    deleteUsername.setVisibility(View.GONE);
                }
                ivIconLeft.setImageResource(R.drawable.ic_22);
                ivIconRight.setImageResource(R.drawable.ic_33);
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                ivIconLeft.setImageResource(R.drawable.ic_22_hide);
                ivIconRight.setImageResource(R.drawable.ic_33_hide);
            }
        });
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 如果用户名清空了 清空密码 清空记住密码选项
                etPassword.setText("");
                if (s.length() > 0) {
                    // 如果用户名有内容时候 显示删除按钮
                    deleteUsername.setVisibility(View.VISIBLE);
                } else {
                    // 如果用户名有内容时候 显示删除按钮
                    deleteUsername.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initTitle() {
        toolbar.setNavigationIcon(R.drawable.ic_cancle);
        toolbar.setTitle("登录");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_login;
    }
    private void login() {

        String name = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        PreferenceUtil.putBoolean(ConstantUtil.KEY, true);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }
    @OnClick({R.id.delete_username, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_username:
                // 清空用户名以及密码
                etUsername.setText("");
                etPassword.setText("");
                deleteUsername.setVisibility(View.GONE);
                etUsername.setFocusable(true);
                etUsername.setFocusableInTouchMode(true);
                etUsername.requestFocus();
                break;
            case R.id.btn_login:
                boolean isNetConnected = CommonUtil.isNetworkAvailable(this);
                if (!isNetConnected) {
                    Toast.makeText(LoginActivity.this, "当前网络不可用,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                login();
                break;
        }
    }
}
