package com.ty.fakeandroidtwo.ui.adapter;

import android.content.Context;

import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.bean.FakeTwoBaseBean;
import com.ty.fakeandroidtwo.bean.FakeTwoDetailsBean;
import com.ty.fakeandroidtwo.component.recyclerview.FakeTwoCommonAdapter;
import com.ty.fakeandroidtwo.component.recyclerview.base.FakeTwoViewHolder;

import java.util.List;

/**
 * 商业贷款，税费计算，个人所得税 共用adapter
 */
public class FakeTwoWageAdapter extends FakeTwoCommonAdapter<FakeTwoDetailsBean> {
    public FakeTwoWageAdapter(Context context, int layoutId, List<FakeTwoDetailsBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(FakeTwoViewHolder holder, FakeTwoDetailsBean fakeTwoDetailsBean, int position) {
        holder.setText(R.id.fake_two_recycler_title, fakeTwoDetailsBean.getName())
                .setText(R.id.fake_two_recycler_details, fakeTwoDetailsBean.getDetails());
    }


}
