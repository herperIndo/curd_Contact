package com.test.contact_jenius.Base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.test.contact_jenius.Base.ui.BaseActivity;
import com.test.contact_jenius.Base.ui.BasePresenter;

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread thread, Throwable throwable) {
//                Log.e("Alert","Why??");
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }

}