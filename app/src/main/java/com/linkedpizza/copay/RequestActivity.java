package com.linkedpizza.copay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paypal.android.sdk.payments.*;

import org.json.JSONException;

import java.math.BigDecimal;

import static com.linkedpizza.copay.config.config.PAYPAL_CLIENT_ID;

public class RequestActivity extends AppCompatActivity {
    private static final int PAYPAL_REQUEST_CODE  = 7171;


    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PAYPAL_CLIENT_ID);
    Button btpay;
    EditText edamount;
    String amount ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);


        btpay= (Button) findViewById(R.id.paybt);
        edamount= (EditText) findViewById(R.id.payamt);




        btpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processpayment();
            }
        });


    }

    private void processpayment() {
        amount= edamount.getText().toString();
        PayPalPayment palPayment = new PayPalPayment(new BigDecimal(String.valueOf(amount)), "USD", "Donate" ,PayPalPayment.PAYMENT_INTENT_SALE );
        Intent intent = new Intent(this, com.paypal.android.sdk.payments.PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT,palPayment);
    }
    protected void onActivityResult (int requestcode, int resultcode, Intent data){

        if (requestcode == PAYPAL_REQUEST_CODE){

            if (requestcode == RESULT_OK){

                PaymentConfirmation confirmation = data.getParcelableExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null){

                    try{
                        String paymentDetails = confirmation.toJSONObject().toString(4);
                        startActivity(new Intent (this, PaymentDetails.class)

                                .putExtra("Payment Details",paymentDetails)
                                .putExtra("Payment Amount",amount)  ) ;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(this,"cancel ", Toast.LENGTH_SHORT).show();
                }  if (resultcode == com.paypal.android.sdk.payments.PaymentActivity.RESULT_EXTRAS_INVALID){
                    Toast.makeText(this,"invalild",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
