package com.linkedpizza.copay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.*;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {

    private String paymentDetails = null;
    TextView txtid, txtamt, txtstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        txtid = (TextView) findViewById (R.id.txtid);
        txtamt = (TextView) findViewById(R.id.txtamt);
        txtstatus = (TextView) findViewById(R.id.txtstatus);

        // Retrieve state from intent.
        Bundle extras = getIntent().getExtras();

        if (extras.getString("PaymentDetails") != null) {
            System.out.println("PD: " + extras.getString("PaymentDetails"));
            paymentDetails = extras.getString("PaymentDetails");
        } else {
            System.out.println("PD: is null");
            System.out.println("check 1");
            onPaymentFailure();
            return;
        }

        try {
            JSONObject jsonObject = new JSONObject(paymentDetails);
            onPaymentSuccess();
        } catch (JSONException e) {
            System.out.println("check 2");
            onPaymentFailure();
            e.printStackTrace();
        }

    }

    private void onPaymentSuccess() {
        Toast.makeText(getApplicationContext(), "Donation Successful!",Toast.LENGTH_LONG).show();
        finish();
    }

    private void onPaymentFailure() {
        Toast.makeText(getApplicationContext(), "Donation Failed!",Toast.LENGTH_LONG).show();
        finish();
    }

    private void showDetails(JSONObject response ,String paymentAmount){

        try{
            txtid.setText( response.getString("id") );
            txtamt.setText(response.getString("state"));
            txtamt.setText(response.getString(String.format("$%s",paymentAmount)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
