package com.ndimeski.fitnesslab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    View view;
    ImageView backImg;
    EditText edttxtNameReg, edttxtSurenameReg, edttxtEmailReg, edttxtPWReg;
    Button btnRegister;
    ProgressBar progressBarReg;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();



        backImg = findViewById(R.id.backButton);

        btnRegister = findViewById(R.id.btnRegister);
        edttxtNameReg = findViewById(R.id.edttxtNameRegister);
        edttxtSurenameReg = findViewById(R.id.edttxtSureameRegister);
        edttxtEmailReg = findViewById(R.id.edttxtEmailRegister);
        edttxtPWReg = findViewById(R.id.edttxtPwRegister);
        progressBarReg = findViewById(R.id.progressReg);

        backImg.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                startActivity(new Intent(RegisterUser.this, MainActivity.class));
                break;
            case R.id.btnRegister:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String name = edttxtNameReg.getText().toString().trim();
        String surname = edttxtSurenameReg.getText().toString().trim();
        String email = edttxtEmailReg.getText().toString().trim();
        String password = edttxtPWReg.getText().toString().trim();


        if (name.isEmpty()){
            edttxtNameReg.setError("First Name is Required");
            edttxtNameReg.requestFocus();
            return;
        }
        if(surname.isEmpty()){
            edttxtSurenameReg.setError("Surname is Required");
            edttxtSurenameReg.requestFocus();
            return;
        }
        if(email.isEmpty()){
            edttxtEmailReg.setError("E-mail is Required");
            edttxtEmailReg.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edttxtEmailReg.setError("Please enter valid e-mail");
            edttxtEmailReg.requestFocus();
            return;
        }
        if(password.isEmpty()){
            edttxtPWReg.setError("Password is Required");
            edttxtPWReg.requestFocus();
            return;
        }
        if (password.length() < 6){
            edttxtPWReg.setError("Please enter longer password than 6 character");
            edttxtPWReg.requestFocus();
            return;
        }

        progressBarReg.setVisibility(view.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(name,surname,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                Toast.makeText(RegisterUser.this,"Registered successfully",Toast.LENGTH_LONG).show();
                                                progressBarReg.setVisibility(view.GONE);
                                                user.sendEmailVerification();
                                                startActivity(new Intent(RegisterUser.this,MainActivity.class));
                                            }else {
                                                Toast.makeText(RegisterUser.this,"Failed to Register",Toast.LENGTH_LONG).show();
                                                progressBarReg.setVisibility(view.GONE);
                                            }
                                        }
                                    });

                        }else {
                            Toast.makeText(RegisterUser.this,"Failed to Register",Toast.LENGTH_LONG).show();
                            progressBarReg.setVisibility(view.GONE);
                        }
                    }
                });

    }
}