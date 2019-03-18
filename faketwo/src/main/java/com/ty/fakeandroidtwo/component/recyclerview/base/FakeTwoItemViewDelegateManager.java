package com.ty.fakeandroidtwo.component.recyclerview.base;

import android.support.v4.util.SparseArrayCompat;



public class FakeTwoItemViewDelegateManager<T>
{
    SparseArrayCompat<FakeTwoItemViewDelegate<T>> delegates = new SparseArrayCompat();

    public int getItemViewDelegateCount()
    {
        return delegates.size();
    }

    public FakeTwoItemViewDelegateManager<T> addDelegate(FakeTwoItemViewDelegate<T> delegate)
    {
        int viewType = delegates.size();
        if (delegate != null)
        {
            delegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    public FakeTwoItemViewDelegateManager<T> addDelegate(int viewType, FakeTwoItemViewDelegate<T> delegate)
    {
        if (delegates.get(viewType) != null)
        {
            throw new IllegalArgumentException(
                    "An FakeTwoItemViewDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered FakeTwoItemViewDelegate is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    public FakeTwoItemViewDelegateManager<T> removeDelegate(FakeTwoItemViewDelegate<T> delegate)
    {
        if (delegate == null)
        {
            throw new NullPointerException("FakeTwoItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public FakeTwoItemViewDelegateManager<T> removeDelegate(int itemType)
    {
        int indexToRemove = delegates.indexOfKey(itemType);

        if (indexToRemove >= 0)
        {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(T item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--)
        {
            FakeTwoItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType( item, position))
            {
                return delegates.keyAt(i);
            }
        }
        throw new IllegalArgumentException(
                "No FakeTwoItemViewDelegate added that matches position=" + position + " in data source");
    }

    public void convert(FakeTwoViewHolder holder, T item, int position)
    {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++)
        {
            FakeTwoItemViewDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType( item, position))
            {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No FakeTwoItemViewDelegateManager added that matches position=" + position + " in data source");
    }


    public FakeTwoItemViewDelegate getItemViewDelegate(int viewType)
    {
        return delegates.get(viewType);
    }

    public int getItemViewLayoutId(int viewType)
    {
        return getItemViewDelegate(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(FakeTwoItemViewDelegate itemViewDelegate)
    {
        return delegates.indexOfValue(itemViewDelegate);
    }
}
