package com.linkedpizza.copay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PaymentActivity extends AppCompatActivity {

    private Button bt ;
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        bt = (Button) findViewById(R.id.paybt);
        et = (EditText)findViewById(R.id.payamt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String text = et.getText().toString();
                intent.putExtra("res",text);
                setResult(0,intent);
                finish();
            }
        });






    }
}