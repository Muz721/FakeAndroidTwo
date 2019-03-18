package com.ty.fakeandroidtwo.utils;

import android.content.Context;

/**
 * 显示信息的辅助类
 */
public class FakeTwoInfoUtils {

    private FakeTwoInfoUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 向用户显示信息
     * @param context
     * @param msg
     */
    public static void showInfo(Context context, String msg) {
        FakeTwoToastUtils.showShort(context, msg);
    }
}
