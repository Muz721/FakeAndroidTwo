package com.ty.fakeandroidtwo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.ty.fakeandroidtwo.utils.FakeTwoInfoUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @description activity 基类
 */
public abstract class FakeTwoBaseActivity extends SupportActivity implements FakeTwoBaseView {
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        onViewCreated();
        unbinder = ButterKnife.bind(this);
        initViewAndEvent();
    }

    /**
     * @param toolbar toolbar
     * @param title   标题
     */
    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressedSupport();
            }
        });
    }

    protected void onViewCreated() {

    }

    /**
     * 打开Activity
     *
     * @param cls 目标class
     */
    public void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
    /**
     * 打开Activity
     *
     * @param cls 目标class
     */
    public void startActivityFinish(Class<?> cls) {
        startActivity(new Intent(this, cls));
        finish();
    }
    /**
     * 绑定布局
     *
     * @return 布局
     */
    protected abstract int getLayout();

    /**
     * 初始化布局和事件
     */
    protected abstract void initViewAndEvent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void hint(String hint) {
        FakeTwoInfoUtils.showInfo(this, hint);
    }


}
