package com.ndimeski.fitnesslab;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView register,forgotpw, loginGuest;
    EditText editTextEmail, editTextPassword;
    Button btnLogin;
    ProgressBar progressBarLogin;
    LinearLayout googleLogin;
    private GoogleSignInClient client;
    private static final String EMAIL = "email";
    public static class C {
       public static boolean guest = false;
    }


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);


        register = findViewById(R.id.txtRegisterLogin);
        forgotpw = findViewById(R.id.txtForgotLogin);
        editTextEmail = findViewById(R.id.edttxtEmailLogin);
        editTextPassword = findViewById(R.id.edttxtPwLogin);
        btnLogin = findViewById(R.id.btnLogin);
        progressBarLogin = findViewById(R.id.progressLogin);
        loginGuest = findViewById(R.id.txtLoginGuest);
        googleLogin = findViewById(R.id.googleLogin);
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this, options);

        googleLogin.setOnClickListener(this);
        loginGuest.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        register.setOnClickListener(this);
        forgotpw.setOnClickListener(this);

        //--Cloud Messaging
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d(TAG, token);
                    }
                });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                if(isNetworkAvilable() != true){
                    Toast.makeText(MainActivity.this,"The Device isn't connected on internet!",Toast.LENGTH_LONG).show();
                    break;
                }
                userLogin();
                break;
            case R.id.txtRegisterLogin:
                if(isNetworkAvilable() != true){
                    Toast.makeText(MainActivity.this,"The Device isn't connected on internet!",Toast.LENGTH_LONG).show();
                    break;
                }
                startActivity(new Intent(MainActivity.this, RegisterUser.class));
                break;
            case R.id.txtForgotLogin:
                if(isNetworkAvilable() != true){
                    Toast.makeText(MainActivity.this,"The Device isn't connected on internet!",Toast.LENGTH_LONG).show();
                    break;
                }
                startActivity(new Intent(MainActivity.this, ForgotPassword.class));
                break;
            case R.id.txtLoginGuest:
                if(isNetworkAvilable() != true){
                    Toast.makeText(MainActivity.this,"The Device isn't connected on internet!",Toast.LENGTH_LONG).show();
                    break;
                }
                anonymusAuth();
                break;
            case R.id.googleLogin:
                if(isNetworkAvilable() != true){
                    Toast.makeText(MainActivity.this,"The Device isn't connected on internet!",Toast.LENGTH_LONG).show();
                    break;
                }
                Intent i = client.getSignInIntent();
                startActivityForResult(i,1234);
                break;
        }
    }

    

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent( MainActivity.this, Home.class));
                                }else {
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }

                            }
                        });

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }


    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("E-mail is Required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter valid e-mail");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is Required");
            editTextPassword.requestFocus();
        }
        if (password.length() < 6){
            editTextPassword.setError("Please enter longer password than 6 character");
            editTextPassword.requestFocus();
            return;
        }

        progressBarLogin.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        updateUI(user);
                    }else {
                        Toast.makeText(MainActivity.this,"Check your e-mail for verification link.",Toast.LENGTH_LONG).show();
                        user.sendEmailVerification();
                        updateUI(null);
                        progressBarLogin.setVisibility(View.GONE);
                    }

                }else{
                    Toast.makeText(MainActivity.this,"Failed to Login. Please check your credentials and try again.", Toast.LENGTH_LONG).show();
                    progressBarLogin.setVisibility(View.GONE);
                }
            }
        });
    }

    private void anonymusAuth() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            MainActivity.C.guest = true;
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }




    public void updateUI(FirebaseUser account){

        if(account != null){
            if(isNetworkAvilable()) {
                Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, Home.class));
            }else {
                Toast.makeText(MainActivity.this, "The Device isn't connected on internet!", Toast.LENGTH_LONG).show();
            }
        }

    }

    public boolean isNetworkAvilable(){
        try {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = null;

            if(manager!= null) {
                networkInfo = manager.getActiveNetworkInfo();
            }
            return networkInfo != null && networkInfo.isConnected();
        } catch (NullPointerException e){
            return false;
        }
    }



}