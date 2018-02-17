package com.linkedpizza.copay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.firebase.auth.FirebaseAuth;



public class LoginActivity extends AppCompatActivity {

    SignInButton signInButton;
    //private Database db;

    // Request sing in code. Could be anything as you required.
    protected static final int RequestSignInCode = 7;

    // Google API Client object.
    static private GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Creating and Configuring Google Api Client.
        googleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                .enableAutoManage(LoginActivity.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Adding Click listener to User Sign in Google button.
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userSignIn();
            }
        });
    
    }

    protected void userSignIn(){
        // Hiding Login in button.
        signInButton.setVisibility(View.GONE);

        // Passing Google Api Client into Intent.
        Auth.GoogleSignInApi.signOut(googleApiClient);
        Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(AuthIntent, RequestSignInCode);
    }

    protected void userSignOut() {
        System.out.println(googleApiClient);

        if (googleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(googleApiClient);
        }

        // After logout setting up login button visibility to visible.
        if (signInButton != null) {
            signInButton.setVisibility(View.VISIBLE);
        }
    }
    protected void onLoginComplete(){
        // Welcome user and move to main activity.
        //Toast.makeText(LoginActivity.this, "Welcome " + user.getFirstName() + "!", Toast.LENGTH_LONG).show();
        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
        mainActivity.putExtra("name",displayName);
        mainActivity.putExtra("email",email);
        startActivity(mainActivity);
    }
    String displayName;
    String email;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestSignInCode){
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (googleSignInResult.isSuccess()){
                // Google auth complete; start Firebase auth.
                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
                makeToast("Google authentication success.");

                displayName = googleSignInAccount.getDisplayName();
                email= googleSignInAccount.getEmail();

                //makeToast(displayName + ":" + email);
                onLoginComplete();


            }
            else{
                makeToast("Google authentication failed.");
;
            }
        }
    }
    private void makeToast(String s){
        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
    }
}
