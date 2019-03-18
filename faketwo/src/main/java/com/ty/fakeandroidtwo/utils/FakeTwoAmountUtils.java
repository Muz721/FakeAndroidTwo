package com.ty.fakeandroidtwo.utils;

public class FakeTwoAmountUtils {
    /**
     * 输入框金额输入金额处理
     * @param amount
     * @return
     */
    public static String amount(String amount) {
        if (amount.charAt(0)=='.'){//判断输入的第一个字符是否是'.'
            amount = "0" +amount;
        }
        boolean fist = false;//记录是否进入判断'0'的方法里
        boolean otherFirst = true;
        for (int i = 0; i < amount.length(); i++) {
            if (amount.charAt(i) == '0') {//判断当前字符是'0'
                fist = true;
            } else {
                if (fist) {
                    if (otherFirst) {
                        if (amount.charAt(i) == '.') {//判断当前字符是'.'
                            if (i > 1) {//如果当前字符是'.'则删除到前一位字符
                                amount = amount.substring(i - 1);//删除开头多余的0
                            }
                        } else {
                            if (i > 0) {
                                amount = amount.substring(i);//删除开头多余的0
                            }
                        }
                    }
                }
                otherFirst = false;
            }
        }
        return amount;
    }
}
