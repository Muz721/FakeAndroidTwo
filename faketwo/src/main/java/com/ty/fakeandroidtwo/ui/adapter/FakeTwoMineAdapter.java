package com.ty.fakeandroidtwo.ui.adapter;

import android.content.Context;

import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.bean.FakeTwoMineBean;
import com.ty.fakeandroidtwo.component.recyclerview.FakeTwoCommonAdapter;
import com.ty.fakeandroidtwo.component.recyclerview.base.FakeTwoViewHolder;

import java.util.List;

public class FakeTwoMineAdapter extends FakeTwoCommonAdapter<FakeTwoMineBean> {
    public FakeTwoMineAdapter(Context context, int layoutId, List<FakeTwoMineBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(FakeTwoViewHolder holder, FakeTwoMineBean fakeTwoMineBean, int position) {
        holder.setText(R.id.fake_two_adapter_mine_tit,fakeTwoMineBean.getName())
                .setImageResource(R.id.fake_two_adapter_mine_icon,fakeTwoMineBean.getImgId());
    }
}
