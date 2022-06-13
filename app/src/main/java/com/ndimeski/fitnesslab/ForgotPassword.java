package com.ndimeski.fitnesslab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    View view;
    ImageView imgback;
    EditText edttxtemailforgot;
    Button btnreset;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();

        imgback = findViewById(R.id.backButton);
        edttxtemailforgot = findViewById(R.id.edttxtEmailForgot);
        btnreset = findViewById(R.id.btnReset);

        imgback.setOnClickListener(this);
        btnreset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backButton:
                startActivity(new Intent(ForgotPassword.this, MainActivity.class));
                break;
            case R.id.btnReset:
                resetPassword();
                break;
        }
    }
    private void resetPassword() {
        String email = edttxtemailforgot.getText().toString().trim();
        if(email.isEmpty()){
            edttxtemailforgot.setError("E-mail is Required");
            edttxtemailforgot.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edttxtemailforgot.setError("Please enter valid e-mail");
            edttxtemailforgot.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"Your reset link has been sent to your e-mail",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(ForgotPassword.this,"Something went wrong! Please try again",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}