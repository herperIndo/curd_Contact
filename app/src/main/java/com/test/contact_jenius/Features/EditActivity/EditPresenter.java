package com.test.contact_jenius.Features.EditActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.test.contact_jenius.Base.ui.BasePresenter;
import com.test.contact_jenius.Features.MainActivity.MainView;
import com.test.contact_jenius.Models.delete;
import com.test.contact_jenius.Models.wraperContectById;
import com.test.contact_jenius.Network.NetworkCallback;

public class EditPresenter extends BasePresenter<EditView> {
    EditPresenter(EditView view) {
        super.attachView(view);
    }

    public void getContactCategori(Context context, Activity activity, String id) {
        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Please Wait...", true);

        addSubscribe(apiStores.getContactCategory(id), new NetworkCallback<wraperContectById>() {
            @Override
            public void onSuccess(wraperContectById model) {
                view.ContactCategoriSuccess(model);
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

    public void deleteContact(final Context context, Activity activity, String id) {
        addSubscribe(apiStores.getDeleteContact(id), new NetworkCallback<delete>() {

            @Override
            public void onSuccess(delete model) {
                view.DeleteSuccess(model);
                Toast.makeText(context, model.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Toast.makeText(context,e.toString()+" Server Problem",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
