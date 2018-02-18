package com.linkedpizza.copay;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_layout);

        String photoURL = getIntent().getExtras().getString("photoURL");

        ImageView profile = findViewById(R.id.profileView);

        Picasso.with(getApplicationContext())
                .load(photoURL)
                .resize(200, 200)
                .centerCrop()
                .transform(new CircleTransform())
                .into(profile);
    }

}
