package com.test.contact_jenius.Features.SaveActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.test.contact_jenius.Base.mvp.MvpActivity;
import com.test.contact_jenius.Features.MainActivity.MainActivity;
import com.test.contact_jenius.Models.delete;
import com.test.contact_jenius.Models.wraperContectById;
import com.test.contact_jenius.Parameter.saveParam;
import com.test.contact_jenius.R;
import com.test.contact_jenius.utils.Functionality;
import com.test.contact_jenius.utils.Preference;

import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaveActivity extends MvpActivity<SavePresenter> implements SaveView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.firstName)
    AppCompatEditText firstName;
    @BindView(R.id.firstNameWrapper)
    TextInputLayout firstNameWrapper;
    @BindView(R.id.lastName)
    AppCompatEditText lastName;
    @BindView(R.id.lastNameWrapper)
    TextInputLayout lastNameWrapper;
    @BindView(R.id.age)
    AppCompatEditText age;
    @BindView(R.id.ageWrapper)
    TextInputLayout ageWrapper;
    @BindView(R.id.upload)
    LinearLayout upload;
    @BindView(R.id.loginLayout)
    RelativeLayout loginLayout;
    private Context context = this;
    private Activity activity = this;
    public static final int GET_FROM_GALLERY = 3;
    Bundle extras;
    wraperContectById byId;

    @Override
    protected SavePresenter createPresenter() {
        return new SavePresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        ButterKnife.bind(this);
        setToolbar();
        extras = getIntent().getExtras();
        if (extras != null) {
            initLayout();
        }

    }

    private void initLayout() {
        byId = Functionality.getObjectFromSharedPrefObj(context, wraperContectById.class, Preference.CntactData);
        int intAge = byId.getData().getAge();
        String strAge = String.valueOf(intAge);
        firstName.setText(byId.getData().getFirstName());
        lastName.setText(byId.getData().getLastName());
        age.setText(strAge);
    }

    private void setToolbar() {
        toolbar.bringToFront();
        toolbar.setTitle("Save Contact");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.left_arrow); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });
    }

    @OnClick({R.id.upload, R.id.ivAvatar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upload:
                checkInput();
                break;
            case R.id.ivAvatar:
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ivAvatar.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void checkInput() {
        int intAge;
        String first = firstName.getText().toString();
        String last = lastName.getText().toString();
        String strAge = age.getText().toString();
        if (strAge.equals("")) {
            intAge = 0;
        } else {
            intAge = Integer.parseInt(strAge);

        }
        if (first.equals("") || last.equals("") || strAge.equals("")) {
            Toast.makeText(context, "Complete your input", Toast.LENGTH_SHORT).show();
        } else {
            saveParam saveParams = new saveParam();
            saveParams.setFirstName(first);
            saveParams.setLastName(last);
            saveParams.setAge(intAge);
            if (extras != null) {
                String id = byId.getData().getId();
                presenter.editContact(context, activity, saveParams,id);
            } else {
                presenter.saveContact(context, activity, saveParams);

            }
        }
    }

    @Override
    public void saveSuccess(delete model) {
        Toast.makeText(context, model.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
    }
}
