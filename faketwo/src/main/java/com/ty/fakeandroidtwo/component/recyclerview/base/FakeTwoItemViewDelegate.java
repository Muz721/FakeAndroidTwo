package com.ty.fakeandroidtwo.component.recyclerview.base;



public interface FakeTwoItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(FakeTwoViewHolder holder, T t, int position);

}
