package com.test.contact_jenius.Features.SaveActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.test.contact_jenius.Base.ui.BasePresenter;
import com.test.contact_jenius.Models.allContact;
import com.test.contact_jenius.Models.delete;
import com.test.contact_jenius.Models.wraperSave;
import com.test.contact_jenius.Network.NetworkCallback;
import com.test.contact_jenius.Parameter.saveParam;

public class SavePresenter extends BasePresenter<SaveView> {
    SavePresenter(SaveView view) {
        super.attachView(view);
    }

    public void saveContact(Context context, Activity activity, saveParam saveParams) {

        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Please Wait...", true);
        addSubscribe(apiStores.saveContact(saveParams), new NetworkCallback<delete>() {
            @Override
            public void onSuccess(delete model) {
                view.saveSuccess(model);
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

    public void editContact(Context context, Activity activity, saveParam saveParams, String id) {
        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Please Wait...", true);
        addSubscribe(apiStores.updateContact(id, saveParams), new NetworkCallback<delete>() {
            @Override
            public void onSuccess(delete model) {
                view.saveSuccess(model);
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

