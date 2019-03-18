package com.ty.fakeandroidtwo.component.rx;


import android.os.Looper;
import android.support.annotation.NonNull;

import io.reactivex.Observer;


public class FakeTwoPreconditions {

    public static void checkNotNull(Object object, @NonNull String message){
if (object == null){
    throw new NullPointerException(message);
}
    }
    public static Boolean checkMainThread(Observer<?> observer){
        if (Looper.myLooper() != Looper.getMainLooper()) {
            observer.onError(new IllegalAccessException(
                    "Expected to be called on the main thread but was " + Thread.currentThread().getName()
            ));

            return false;
        }
        return true;
    }
}
