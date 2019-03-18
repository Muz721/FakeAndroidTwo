package com.ty.fakeandroidtwo.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.base.FakeTwoBaseActivity;
import com.ty.fakeandroidtwo.utils.FakeTwoGeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FakeTwoLoginActivity extends FakeTwoBaseActivity {

    @BindView(R.id.fake_two_et_username)
    EditText fakeTwoEtUsername;
    @BindView(R.id.fake_two_et_password)
    EditText fakeTwoEtPassword;
    @BindView(R.id.fake_two_bt_go)
    Button fakeTwoBtGo;
    @BindView(R.id.fake_two_login_txt_register)
    TextView fakeTwoLoginTxtRegister;
    @BindView(R.id.fake_two_title_img_finish)
    ImageView fakeTwoTitleImgFinish;
    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    private String name;
    private String password;
    private SharedPreferences user;

    @Override
    protected int getLayout() {
        return R.layout.fake_two_activity_login;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleTxtTitle.setText("登录");
        fakeTwoTitleImgFinish.setImageResource(R.drawable.fake_two_finish_black);
        FakeTwoGeneralUtils.openSoftKeyboard(fakeTwoEtUsername);
        inData();
    }


    private void inData() {
        user = getSharedPreferences("user", 0);
        name = user.getString("name", "未");
        password = user.getString("password", "未");
    }


    @OnClick({R.id.fake_two_title_img_finish, R.id.fake_two_bt_go, R.id.fake_two_login_txt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fake_two_title_img_finish:
                finish();
                break;
            case R.id.fake_two_bt_go:
                if (name.equals(fakeTwoEtUsername.getText().toString().trim()) && password.equals(fakeTwoEtPassword.getText().toString().trim())) {
                    SharedPreferences.Editor editor = user.edit();
                    editor.putBoolean("isLogin", true)
                            .apply();
                    finish();
                } else {
                    hint("账户或密码错误");
                }
                break;
            case R.id.fake_two_login_txt_register:
                Intent intent = new Intent(FakeTwoLoginActivity.this, FakeTwoRegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
