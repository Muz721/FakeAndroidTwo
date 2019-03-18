package com.ty.fakeandroidtwo.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class FakeTwoGeneralUtils {
    /**
     * 获取控件焦点
     *
     * @param view 要获取焦点的view
     */
    public static void ganFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.requestFocusFromTouch();

    }

    /**
     * 打开软键盘
     *
     * @param editText 控件
     */
    public static void openSoftKeyboard(EditText editText) {
        InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//获取软键盘
        inputManager.showSoftInput(editText, 0);//打开软件盘   不使用自动判断打开和关闭软键盘方法（有问题，自己关闭的不认同，isActive()会一直为true）
    }

    /**
     * 关闭软键盘
     *
     * @param view 控件
     */
    public static void closeSoftKeyboard(View view) {
        InputMethodManager m = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//获取软键盘
        m.hideSoftInputFromWindow(view.getWindowToken(), 0);//关闭软件盘   不使用自动判断打开和关闭软键盘方法（有问题，自己关闭的不认同，isActive()会一直为true）
    }

    /**
     * 获取焦点并打开软键盘
     *
     * @param view 控件
     */
    public static void getFocusAndOpenSoftKeyboard(View view) {
        ganFocus(view);//获取焦点
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//获取软键盘
        inputManager.showSoftInput(view, 0);//打开软件盘   不使用自动判断打开和关闭软键盘方法（有问题，自己关闭的不认同，isActive()会一直为true）
    }

    /**
     * 失去焦点并关闭软键盘
     *
     * @param view 控件
     */
    public static void closeFocusAndCloseSoftKeyboard(View view) {
        view.setFocusable(false);
        view.setFocusableInTouchMode(false);
        InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);//获取软键盘
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);//打开软件盘   不使用自动判断打开和关闭软键盘方法（有问题，自己关闭的不认同，isActive()会一直为true）
    }
}
