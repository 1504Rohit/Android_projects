package com.example.e_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText editTextTextPersonName2;
    EditText editTextPhone;
    Button btnGenerateOTPButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextPhone = findViewById(R.id.editTextPhone);
        btnGenerateOTPButton = findViewById(R.id.btnGenerateOTP);

        btnGenerateOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextPhone.getText().toString().trim().isEmpty()){
                    if((editTextPhone.getText().toString().trim()).length()==10){

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + editTextPhone.getText().toString(),
                                Long.parseLong("60"),
                                TimeUnit.SECONDS,
                                MainActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        Intent intent = new Intent(MainActivity.this,OtpVerify.class);
                                        intent.putExtra("mobile",editTextPhone.getText().toString());
                                        intent.putExtra("backendotp",backendotp);
                                        startActivity(intent);
                                    }
                                }
                        );
//
                    }else{
                        Toast.makeText(MainActivity.this, "Please Enter correct number", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}