package com.ty.fakeandroidtwo.component.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;


import com.ty.fakeandroidtwo.component.recyclerview.base.FakeTwoItemViewDelegate;
import com.ty.fakeandroidtwo.component.recyclerview.base.FakeTwoViewHolder;

import java.util.List;


public abstract class FakeTwoCommonAdapter<T> extends FakeTwoMultiItemTypeAdapter<T>
{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public FakeTwoCommonAdapter(final Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new FakeTwoItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType( T item, int position)
            {
                return true;
            }

            @Override
            public void convert(FakeTwoViewHolder holder, T t, int position)
            {
                FakeTwoCommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    protected abstract void convert(FakeTwoViewHolder holder, T t, int position);


}
