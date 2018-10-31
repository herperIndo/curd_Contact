package com.test.contact_jenius.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.contact_jenius.Features.EditActivity.EditActivity;
import com.test.contact_jenius.Models.allContact;
import com.test.contact_jenius.R;
import com.test.contact_jenius.utils.CircleTransform;
import com.test.contact_jenius.utils.Preference;

import java.util.List;

public class contactAdapter extends RecyclerView.Adapter<contactAdapter.ListContactHolder> {
    private allContact listContact;
    Context context;
    Activity activity;

    public contactAdapter(allContact listContact, Context context, Activity activity) {
        this.listContact = listContact;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public contactAdapter.ListContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ListContactHolder(view);
    }

    @Override
    public void onBindViewHolder(contactAdapter.ListContactHolder holder, int position) {
        String age = String.valueOf(listContact.getData().get(position).getAge() + " years old");
        final String id = listContact.getData().get(position).getId();
        holder.nameContact.setText(listContact.getData().get(position).getLastName() + " " + listContact.getData().get(position).getFirstName());
        holder.AgeContact.setText(age);
        Glide.with(context)
                .load(listContact.getData().get(position).getPhoto())
                .centerCrop()
                .transform(new CircleTransform(context))
                .error(R.drawable.user)
                .into(holder.imgAvatar);
        holder.frameContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToEdit = new Intent(activity, EditActivity.class);
                intentToEdit.putExtra(Preference.ContactID, id);
                activity.startActivity(intentToEdit);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listContact.getData().size();
    }

    public class ListContactHolder extends RecyclerView.ViewHolder {
        TextView nameContact;
        TextView AgeContact;
        ImageView imgAvatar;
        LinearLayout frameContact;

        public ListContactHolder(View itemView) {
            super(itemView);
            nameContact = (TextView) itemView.findViewById(R.id.contact_name);
            AgeContact = (TextView) itemView.findViewById(R.id.contact_age);
            imgAvatar = (ImageView) itemView.findViewById(R.id.contact_tmb);
            frameContact = (LinearLayout) itemView.findViewById(R.id.contact_frame);


        }
    }
}
