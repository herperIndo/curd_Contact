package com.test.contact_jenius.Features.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.test.contact_jenius.Adapter.contactAdapter;
import com.test.contact_jenius.Base.mvp.MvpActivity;
import com.test.contact_jenius.Features.SaveActivity.SaveActivity;
import com.test.contact_jenius.Models.allContact;
import com.test.contact_jenius.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpActivity<MainActivityPresenter> implements MainView {
    @BindView(R.id.rvContact)
    RecyclerView rvContact;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private Context context = this;
    private Activity activity = this;
    contactAdapter adapter;

    @Override
    protected MainActivityPresenter createPresenter() {
        return new MainActivityPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.getAllContact(context);
    }

    @Override
    public void GetContactSuccess(allContact model) {
        rvContact.setHasFixedSize(true);
        rvContact.setLayoutManager(new LinearLayoutManager(context));
        rvContact.setItemAnimator(new DefaultItemAnimator());
        rvContact.setAdapter(new contactAdapter(model, context, activity));
    }

    @OnClick({R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Intent intentToSave = new Intent(activity, SaveActivity.class);
                startActivity(intentToSave);
                break;

        }
    }
}
