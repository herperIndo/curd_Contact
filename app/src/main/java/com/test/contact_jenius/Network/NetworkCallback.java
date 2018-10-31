package com.test.contact_jenius.Network;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class NetworkCallback<M> extends Subscriber<M> {
    private static final String TAG = NetworkCallback.class.getName();

    public abstract void onSuccess(M model);

    public abstract void onFailure(String message);

    public abstract void onFinish();

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String message = "Problem Server";
            Log.i(TAG, "code:" + code + " Message:" + httpException.getMessage());
            onFailure(message);
        } else {
            onFailure(e.getMessage());
            Log.i("OnFailure", e.getMessage());
        }
        onFinish();
    }


    @Override
    public void onNext(M model) {
        final Gson gson = new Gson();
        Log.d("APIRespond", gson.toJson(model));
        onSuccess(model);
    }
}
