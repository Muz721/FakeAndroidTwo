package com.ty.fakeandroidtwo.bean;

public class FakeTwoMineBean extends FakeTwoBaseBean{
    private int imgId;

    public FakeTwoMineBean(int id, String name, int imgId) {
        super(id, name);
        this.imgId = imgId;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
