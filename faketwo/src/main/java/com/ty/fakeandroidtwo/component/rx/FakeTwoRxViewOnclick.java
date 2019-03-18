package com.ty.fakeandroidtwo.component.rx;



import io.reactivex.observers.ResourceObserver;

/**
 * 点击事件错误
 * @param <T>         可放到CommonSubscriber
 *           这样写是为了区分，后面可能合并到一起
 */

public abstract class FakeTwoRxViewOnclick<T> extends ResourceObserver<T> {

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {

    }
}
