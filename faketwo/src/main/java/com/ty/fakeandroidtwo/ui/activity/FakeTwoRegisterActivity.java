package com.ty.fakeandroidtwo.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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


public class FakeTwoRegisterActivity extends FakeTwoBaseActivity {
    @BindView(R.id.fake_two_register_et_username)
    EditText fakeTwoRegisterEtUsername;
    @BindView(R.id.fake_two_register_et_password)
    EditText fakeTwoRegisterEtPassword;
    @BindView(R.id.fake_two_register_et_password_confirm)
    EditText fakeTwoRegisterEtPasswordConfirm;
    @BindView(R.id.fake_two_register_bt_go)
    Button fakeTwoRegisterBtGo;
    @BindView(R.id.fake_two_register_txt_register)
    TextView fakeTwoRegisterTxtRegister;
    @BindView(R.id.fake_two_title_img_finish)
    ImageView fakeTwoTitleImgFinish;
    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;


    @Override
    protected int getLayout() {
        return R.layout.fake_two_activity_register;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleTxtTitle.setText("注册");
        fakeTwoTitleImgFinish.setImageResource(R.drawable.fake_two_finish_black);
        FakeTwoGeneralUtils.openSoftKeyboard(fakeTwoRegisterEtUsername);
    }


    @OnClick({R.id.fake_two_title_img_finish, R.id.fake_two_register_bt_go, R.id.fake_two_register_txt_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fake_two_title_img_finish:
                finish();
                break;
            case R.id.fake_two_register_bt_go:
                String name = fakeTwoRegisterEtUsername.getText().toString().trim();
                String password = fakeTwoRegisterEtPassword.getText().toString().trim();
                String ConfirmPassword = fakeTwoRegisterEtPasswordConfirm.getText().toString().trim();
                if (password.equals(ConfirmPassword) && !TextUtils.isEmpty(password)) {
                    SharedPreferences user = getSharedPreferences("user", 0);
                    SharedPreferences.Editor editor = user.edit();
                    editor.putString("name", name)
                            .putString("password", password)
                            .putBoolean("isLogin", true)
                            .apply();
                    finish();
                } else {
                    Toast.makeText(FakeTwoRegisterActivity.this, "密码和确认密码不一致", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.fake_two_register_txt_register:
                Intent intent = new Intent(this,FakeTwoLoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
