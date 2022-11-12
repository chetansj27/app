package com.example.dream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class PhoneInput extends AppCompatActivity {
    Toolbar toolbar;
    TextInputEditText editTextMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_input);
        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        editTextMobile=findViewById(R.id.inputPhone);
        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = editTextMobile.getText().toString().trim();

                if(mobile.isEmpty() || mobile.length() < 10){
                    editTextMobile.setError("Enter a valid mobile");
                    editTextMobile.requestFocus();
                    return;
                }

                Intent intent = new Intent(PhoneInput.this, OtpInputActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent=new Intent(PhoneInput.this,MainActivity.class);
        startActivity(intent);
    }
}