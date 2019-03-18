package com.ty.fakeandroidtwo.bean;

import com.contrarywind.interfaces.IPickerViewData;

/**
 * 数据基本类
 */
public class FakeTwoBaseBean implements IPickerViewData {
    private int id;
    private String name;
    public FakeTwoBaseBean(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getPickerViewText() {
        return name;
    }
}
