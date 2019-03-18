package com.ty.fakeandroidtwo.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.base.FakeTwoBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FakeTwoSettingActivity extends FakeTwoBaseActivity {
    @BindView(R.id.fake_two_title_img_finish)
    ImageView fakeTwoTitleImgFinish;
    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    @BindView(R.id.fake_two_lay_update_psd)
    RelativeLayout fakeTwoLayUpdatePsd;
    @BindView(R.id.fake_two_btn_login_out)
    Button fakeTwoBtnLoginOut;

    @Override
    protected int getLayout() {
        return R.layout.fake_two_activity_setting;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleTxtTitle.setText("设置");
        fakeTwoTitleImgFinish.setImageResource(R.drawable.fake_two_finish_black);
    }


    @OnClick({R.id.fake_two_title_img_finish, R.id.fake_two_lay_update_psd, R.id.fake_two_btn_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fake_two_title_img_finish:
                finish();
                break;
            case R.id.fake_two_lay_update_psd:
                Intent intent = new Intent(this, FakeTwoChangePasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.fake_two_btn_login_out:
                SharedPreferences user = getSharedPreferences("user", 0);
                SharedPreferences.Editor editor = user.edit();
                editor.putBoolean("isLogin", false)
                        .apply();
                finish();
                break;
        }
    }
}
