package com.test.contact_jenius.Features.MainActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.test.contact_jenius.Base.ui.BasePresenter;
import com.test.contact_jenius.Models.allContact;
import com.test.contact_jenius.Network.NetworkCallback;

public class MainActivityPresenter extends BasePresenter<MainView> {
    MainActivityPresenter(MainView view) {
        super.attachView(view);
    }

    public void getAllContact(Context context) {
        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Please Wait...", true);

        addSubscribe(apiStores.getAllContact(), new NetworkCallback<allContact>() {
            @Override
            public void onSuccess(allContact model) {
                view.GetContactSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                Log.d("Log", message);
            }

            @Override
            public void onFinish() {
                progressDialog.dismiss();
            }
        });
    }
}
