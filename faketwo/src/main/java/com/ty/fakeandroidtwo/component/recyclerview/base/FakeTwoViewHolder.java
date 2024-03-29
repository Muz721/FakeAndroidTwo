package com.ty.fakeandroidtwo.component.recyclerview.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;


public class FakeTwoViewHolder extends RecyclerView.ViewHolder
{
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public FakeTwoViewHolder(Context context, View itemView)
    {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }


    public static FakeTwoViewHolder createViewHolder(Context context, View itemView)
    {
        FakeTwoViewHolder holder = new FakeTwoViewHolder(context, itemView);
        return holder;
    }

    public static FakeTwoViewHolder createViewHolder(Context context,
                                                     ViewGroup parent, int layoutId)
    {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        FakeTwoViewHolder holder = new FakeTwoViewHolder(context, itemView);
        return holder;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {
        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView()
    {
        return mConvertView;
    }




    /****以下为辅助方法*****/

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public FakeTwoViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(TextUtils.isEmpty(text) ? "" : text);//显示内容
        return this;
    }
    public FakeTwoViewHolder setText(int viewId, String text, String defaultData)
    {
        TextView tv = getView(viewId);
        tv.setText(TextUtils.isEmpty(text) ? defaultData : text);//显示内容
        return this;
    }
    public FakeTwoViewHolder setImageResource(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }


    public FakeTwoViewHolder setImageBitmap(int viewId, Bitmap bitmap)
    {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public FakeTwoViewHolder setImageDrawable(int viewId, Drawable drawable)
    {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public FakeTwoViewHolder setBackgroundColor(int viewId, int color)
    {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public FakeTwoViewHolder setBackgroundRes(int viewId, int backgroundRes)
    {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public FakeTwoViewHolder setTextColor(int viewId, int textColor)
    {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public FakeTwoViewHolder setTextColorRes(int viewId, int textColorRes)
    {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(textColorRes));
        return this;
    }

    @SuppressLint("NewApi")
    public FakeTwoViewHolder setAlpha(int viewId, float value)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            getView(viewId).setAlpha(value);
        } else
        {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public FakeTwoViewHolder setVisible(int viewId, boolean visible)
    {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }
    public FakeTwoViewHolder linkify(int viewId)
    {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public FakeTwoViewHolder setTypeface(Typeface typeface, int... viewIds)
    {
        for (int viewId : viewIds)
        {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    public FakeTwoViewHolder setProgress(int viewId, int progress)
    {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public FakeTwoViewHolder setProgress(int viewId, int progress, int max)
    {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public FakeTwoViewHolder setMax(int viewId, int max)
    {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        return this;
    }

    public FakeTwoViewHolder setRating(int viewId, float rating)
    {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    public FakeTwoViewHolder setRating(int viewId, float rating, int max)
    {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    public FakeTwoViewHolder setTag(int viewId, Object tag)
    {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public FakeTwoViewHolder setTag(int viewId, int key, Object tag)
    {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public FakeTwoViewHolder setChecked(int viewId, boolean checked)
    {
        Checkable view = (Checkable) getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 关于事件的
     */
    public FakeTwoViewHolder setOnClickListener(int viewId,
                                                View.OnClickListener listener)
    {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public FakeTwoViewHolder setOnTouchListener(int viewId,
                                                View.OnTouchListener listener)
    {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public FakeTwoViewHolder setOnLongClickListener(int viewId,
                                                    View.OnLongClickListener listener)
    {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }


}
