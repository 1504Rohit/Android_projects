package com.example.e_voting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerify extends AppCompatActivity {
     EditText editTextOTP;
     Button btnSubmit;
     Button button3;
     String getotpbackend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        editTextOTP = findViewById(R.id.editTextOTP);
        btnSubmit=findViewById(R.id.btnSubmit);

        getotpbackend = getIntent().getStringExtra("backendotp");



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editTextOTP.getText().toString().trim().isEmpty()){
                    String entercodeotp = editTextOTP.getText().toString();
                    if(getotpbackend!=null){
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getotpbackend,entercodeotp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                       if(task.isSuccessful()){
                                           Intent intent = new Intent(OtpVerify.this,WorkPage.class);
                                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                           startActivity(intent);
                                       }else{
                                           Toast.makeText(OtpVerify.this, "Enter the correct OTP", Toast.LENGTH_SHORT).show();
                                       }
                                    }
                                });

                    }else{
                        Toast.makeText(OtpVerify.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(OtpVerify.this, "Please enter all number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OtpVerify.this, "Reached the Limit", Toast.LENGTH_SHORT).show();
            }
        });
    }

}