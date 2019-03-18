package com.ty.fakeandroidtwo.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ty.fakeandroidtwo.utils.FakeTwoInfoUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import me.yokeyword.fragmentation.SupportFragment;


/**
 * @description  fragment 基类
 */
public abstract class FakeTwoBaseFragment extends SupportFragment implements FakeTwoBaseView {
    protected Context context;
    private Unbinder unbinder;
    protected boolean isInited = false;
    @Override
    public void onAttach(Context context) {
        this.context=context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(),null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder= ButterKnife.bind(this,view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInited = true;
        initViewAndEvent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();
        }

    }
    @Override
    public void hint(String hint) {
        FakeTwoInfoUtils.showInfo(getContext(), hint);
    }

    /**
     * 绑定布局
     * @return   布局
     */
    protected abstract int getLayout();

    /**
     * 初始化布局和事件
     */
    protected abstract void initViewAndEvent();
    /**
     * 打开Activity
     *
     * @param cls 目标class
     */
    public void startActivity(Class<?> cls) {
        startActivity(new Intent(getContext(), cls));
    }
}
