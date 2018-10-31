package com.test.contact_jenius.Features.EditActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.test.contact_jenius.Base.mvp.MvpActivity;
import com.test.contact_jenius.Features.MainActivity.MainActivity;
import com.test.contact_jenius.Features.SaveActivity.SaveActivity;
import com.test.contact_jenius.Models.delete;
import com.test.contact_jenius.Models.wraperContectById;
import com.test.contact_jenius.R;
import com.test.contact_jenius.utils.CircleTransform;
import com.test.contact_jenius.utils.Functionality;
import com.test.contact_jenius.utils.Preference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditActivity extends MvpActivity<EditPresenter> implements EditView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivAvatarEdit)
    ImageView ivAvatarEdit;
    @BindView(R.id.nameEdit)
    TextView nameEdit;
    @BindView(R.id.idEdit)
    TextView idEdit;
    @BindView(R.id.ageEdit)
    TextView ageEdit;
    @BindView(R.id.update)
    LinearLayout update;
    @BindView(R.id.delete_Button)
    LinearLayout deleteButton;
    private Context context = this;
    private Activity activity = this;
    Bundle extra;
    String id;
    wraperContectById contectById;

    @Override
    protected EditPresenter createPresenter() {
        return new EditPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        setToolbar();
        extra = getIntent().getExtras();
        id = extra.getString(Preference.ContactID);
        initLayoutCategori(id);

    }

    private void setToolbar() {
        toolbar.bringToFront();
        toolbar.setTitle("Detail Contact");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.left_arrow); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });
    }


    private void initLayoutCategori(String id) {
        presenter.getContactCategori(context, activity, id);
    }

    @Override
    public void ContactCategoriSuccess(wraperContectById model) {
        contectById = model;
        String age = String.valueOf(model.getData().getAge());
        nameEdit.setText(model.getData().getLastName() + " " + model.getData().getFirstName());
        idEdit.setText(model.getData().getId());
        ageEdit.setText(age + " years old");
        Glide.with(context)
                .load(model.getData().getPhoto())
                .centerCrop()
                .transform(new CircleTransform(context))
                .error(R.drawable.user128)
                .into(ivAvatarEdit);

    }

    @Override
    public void DeleteSuccess(delete model) {
        Toast.makeText(context, model.getMessage(), Toast.LENGTH_LONG).show();
        Intent intentToMain = new Intent(activity, MainActivity.class);
        startActivity(intentToMain);
        activity.finish();

    }

    @OnClick({R.id.delete_Button, R.id.update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.delete_Button:
                presenter.deleteContact(context, activity, id);
                break;
            case R.id.update:
                Functionality.storeObjectToSharedPrefObj(context, contectById, Preference.CntactData);
                Intent intent = new Intent(activity, SaveActivity.class);
                intent.putExtra(Preference.FromEdit, "edit");
                startActivity(intent);
                break;

        }
    }
}
