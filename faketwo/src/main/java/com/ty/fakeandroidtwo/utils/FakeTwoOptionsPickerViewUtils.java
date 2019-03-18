package com.ty.fakeandroidtwo.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.ty.fakeandroidtwo.R;
import com.ty.fakeandroidtwo.bean.FakeTwoBaseBean;

import java.util.ArrayList;

public class FakeTwoOptionsPickerViewUtils {
    public static OptionsPickerView open(Context context, String title,OptionsPickerView pickerView, ArrayList<FakeTwoBaseBean> beans, OnOptionsSelectListener onOptionsSelectListener){
        pickerView = new OptionsPickerBuilder(context, onOptionsSelectListener)
                .setTitleText(title)
                .setContentTextSize(20)
                .setSelectOptions(0)
                .setDividerColor(Color.WHITE)
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(Color.GRAY)
                .setCancelColor(Color.GRAY)
                .setSubmitColor(Color.GRAY)
                .setTextColorCenter(Color.GRAY)
                .setTextColorOut(context.getResources().getColor(R.color.fakeTwoColorPickerOut))
                .setOutSideColor(0x50000000)
                .build();
        pickerView.setPicker(beans);
        return pickerView;
    }
}
