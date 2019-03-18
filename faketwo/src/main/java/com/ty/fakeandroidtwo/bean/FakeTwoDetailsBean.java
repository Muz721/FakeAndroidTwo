package com.ty.fakeandroidtwo.bean;

/**
 * 计算结果详情数据
 */
public class FakeTwoDetailsBean extends FakeTwoBaseBean {
    private String details;

    public FakeTwoDetailsBean(int id, String name, String details) {
        super(id, name);
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
