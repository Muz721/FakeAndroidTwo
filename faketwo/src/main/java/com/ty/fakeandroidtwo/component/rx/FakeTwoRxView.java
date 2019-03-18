package com.ty.fakeandroidtwo.component.rx;


import android.support.annotation.CheckResult;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


public final class FakeTwoRxView {
    @CheckResult
    @NonNull
    public static Observable<View> clicks(@NonNull View view){
        FakeTwoPreconditions.checkNotNull(view,"view == null");
        return new FakeTwoViewClickObservable(view).throttleFirst(1, TimeUnit.SECONDS);
    }
    @CheckResult
    @NonNull
    public static Consumer<? super Boolean> enabled(@NonNull final View view){
        FakeTwoPreconditions.checkNotNull(view,"view == null");
        return new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                view.setEnabled(aBoolean);
            }
        };
    }
}
