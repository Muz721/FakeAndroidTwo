package com.ty.fakeandroidtwo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.base.FakeTwoBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FakeTwoWebViewActivity extends FakeTwoBaseActivity {
    @BindView(R.id.fake_two_title_img_finish)
    ImageView fakeTwoTitleImgFinish;
    @BindView(R.id.fake_two_title_txt_title)
    TextView fakeTwoTitleTxtTitle;
    @BindView(R.id.fake_two_webView)
    WebView fakeTwoWebView;

    @Override
    protected int getLayout() {
        return R.layout.fake_two_webview;
    }

    @Override
    protected void initViewAndEvent() {
        fakeTwoTitleImgFinish.setImageResource(R.drawable.fake_two_finish_black);
        Intent intent = getIntent();
        fakeTwoTitleTxtTitle.setText(intent.getStringExtra("title"));
        fakeTwoWebView.loadUrl(intent.getStringExtra("url"));
    }


    @OnClick({R.id.fake_two_title_img_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fake_two_title_img_finish:
                finish();
                break;
        }
    }
}
