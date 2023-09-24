package com.gmail.creativescientist.nfctoggleunimy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText userName, userPassword;
    Button Signin;
    TextView btnSignUp ;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        userName = findViewById(R.id.username);
        userPassword = findViewById(R.id.password);
        Signin = findViewById(R.id.bt_login);
        btnSignUp = findViewById(R.id.tvSignup);
        progressBar = findViewById(R.id.progressBar2);

        progressBar.setVisibility(View.GONE);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    if (!(firebaseAuth.getCurrentUser().isEmailVerified())){
                        Toast.makeText(MainActivity.this, "Please verify your e-mail before log in", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, Home.class);
                        startActivity(i);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = userName.getText().toString();
                String pwd = userPassword.getText().toString();
                if(email.isEmpty()){
                    userName.setError("Please enter email id");
                    userName.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    userPassword.setError("Please enter your password");
                    userPassword.requestFocus();
                }
                    else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter all the details",Toast.LENGTH_SHORT).show();
                }
                        else  if(!(email.isEmpty() && pwd.isEmpty())){
                          mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               progressBar.setVisibility(View.VISIBLE);
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                            else{
                                Intent intToHome = new Intent(MainActivity.this,Home.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }

            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }
    @Override
    public void onBackPressed(){

    }
}
