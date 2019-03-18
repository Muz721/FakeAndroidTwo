package com.ty.fakeandroidtwo.ui.activity;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FakeTwoChangePasswordActivity extends FakeTwoBaseActivity {


    @BindView(R.id.fake_two_title_img_finish)
    ImageView fakeTwoTitleImgFinish;
    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    @BindView(R.id.fake_two_change_password_et_older_password)
    EditText fakeTwoChangePasswordEtOlderPassword;
    @BindView(R.id.fake_two_change_password_et_new_password)
    EditText fakeTwoChangePasswordEtNewPassword;
    @BindView(R.id.fake_two_change_password_et_password_confirm)
    EditText fakeTwoChangePasswordEtPasswordConfirm;
    @BindView(R.id.fake_two_change_password_bt_go)
    Button fakeTwoChangePasswordBtGo;

    @Override
    protected int getLayout() {
        return R.layout.fake_two_activity_change_password;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleTxtTitle.setText("修改密码");
        fakeTwoTitleImgFinish.setImageResource(R.drawable.fake_two_finish_black);

    }


    @OnClick({R.id.fake_two_title_img_finish, R.id.fake_two_change_password_bt_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fake_two_title_img_finish:
                finish();
                break;
            case R.id.fake_two_change_password_bt_go:
                String oldPassword = fakeTwoChangePasswordEtOlderPassword.getText().toString().trim();
                String newPassword = fakeTwoChangePasswordEtNewPassword.getText().toString().trim();
                String ConfirmPassword = fakeTwoChangePasswordEtPasswordConfirm.getText().toString().trim();
                SharedPreferences user = getSharedPreferences("user", 0);
                if (!TextUtils.isEmpty(oldPassword) && oldPassword.equals(user.getString("password", "未"))) {
                    if (newPassword.equals(ConfirmPassword) && !TextUtils.isEmpty(newPassword)) {
                        SharedPreferences.Editor editor = user.edit();
                        editor.putString("password", newPassword)
                                .putBoolean("isLogin", true)
                                .apply();
                        finish();
                    } else {
                        hint("密码和确认密码不一致");
                    }
                } else {
                    hint("原密码错误");
                }
                break;
        }
    }

}
