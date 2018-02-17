package com.linkedpizza.copay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String email;
    String name;
    UserAccount user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=getIntent().getExtras().getString("email");
        name=getIntent().getExtras().getString("name");
        user = new UserAccount(name,email);
        makeToast("Welcome" +"\n" + user.toString());
    }


    private void makeToast(String s){
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
