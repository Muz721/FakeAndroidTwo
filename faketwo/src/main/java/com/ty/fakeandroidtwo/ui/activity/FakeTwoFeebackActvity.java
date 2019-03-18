package com.ty.fakeandroidtwo.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.base.FakeTwoBaseActivity;
import com.ty.fakeandroidtwo.component.rx.FakeTwoRxView;
import com.ty.fakeandroidtwo.component.rx.FakeTwoRxViewOnclick;

import butterknife.BindView;

import butterknife.OnClick;

public class FakeTwoFeebackActvity extends FakeTwoBaseActivity {
    @BindView(R.id.fake_two_title_img_finish)
    ImageView fakeTwoTitleImgFinish;
    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    @BindView(R.id.fake_two_feedback_text)
    EditText fakeTwoFeedbackText;
    @BindView(R.id.fake_two_txtLength)
    TextView fakeTwoTxtLength;
    @BindView(R.id.fake_two_btnSubmit)
    Button fakeTwoBtnSubmit;

    @Override
    protected int getLayout() {
        return R.layout.fake_two_activity_feeback;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleImgFinish.setImageResource(R.drawable.fake_two_finish_black);
        fakeTwoTitleTxtTitle.setText("意见反馈");
        fakeTwoFeedbackText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fakeTwoTxtLength.setText((200 - getString(fakeTwoFeedbackText).length()) + "");
            }

            @Override
            public void afterTextChanged(Editable s) {
                fakeTwoTxtLength.setText(s.length() + "/" + "200");
            }
        });
        FakeTwoRxView.clicks(fakeTwoBtnSubmit)
                .subscribe(new FakeTwoRxViewOnclick<View>() {
                    @Override
                    public void onNext(View view) {
                        if (checkInput()){
                            hint("提交成功！");
                        }
                        finish();
                    }
                });
    }

    public  String getString(EditText v) {
        return v.getText().toString().replaceAll(" ", "");
    }

    @OnClick({R.id.fake_two_title_img_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fake_two_title_img_finish:
                finish();
                break;
        }
    }
    private boolean checkInput() {
        if (TextUtils.isEmpty(fakeTwoFeedbackText.getText().toString().trim())) {
            hint("您输入内容为空！");
            return false;
        }
        return true;
    }
}
