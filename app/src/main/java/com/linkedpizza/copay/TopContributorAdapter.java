package com.linkedpizza.copay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by david on 2018-02-17.
 */

public class TopContributorAdapter extends ArrayAdapter<UserAccount> {

    private Context context;
    private ArrayList<UserAccount> users;

    public TopContributorAdapter(Context context, ArrayList<UserAccount> users) {
        super(context, R.layout.top_contributor_adapter_layout, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.top_contributor_adapter_layout, parent, false);

        UserAccount user = users.get(position);

        ((TextView) rowView.findViewById(R.id.txtNumber)).setText("#" + (position + 1));

        if (user.getPhotoURL() != null) {
            Picasso.with(rowView.getContext()).load(user.getPhotoURL()).into((ImageView) rowView.findViewById(R.id.imgUser));
        } else {
            ((ImageView) rowView.findViewById(R.id.imgUser)).setImageResource(R.drawable.avatar);
        }

        ((TextView) rowView.findViewById(R.id.txtName)).setText(user.getName());

        // Return the completed view to render on screen
        return rowView;
    }
}
