package com.gmail.creativescientist.nfctoggleunimy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    EditText userName, userPassword, userRepassword;
    Button regButton;
    TextView tvSignIn;
    private FirebaseAuth mAuth;
    private Spinner spinner;
    user User;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = findViewById(R.id.toolbar_signup);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        userName = findViewById(R.id.usernamesignup);
        userPassword = findViewById(R.id.passwordsignup);
        userRepassword = findViewById(R.id.repasswordsignup);
        regButton = findViewById(R.id.bt_signup);
        tvSignIn = findViewById(R.id.tvSignIn);
        mAuth = FirebaseAuth.getInstance();
        spinner = findViewById(R.id.spinnersignup);
        User = new user();
        progressBar = findViewById(R.id.progressBar);

        List<String> peopleType = new ArrayList<>();
        peopleType.add(0, "Choose One");
        peopleType.add("Lecturer");
        peopleType.add("Student");
        peopleType.add("Staff");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, peopleType);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                if (parent.getItemAtPosition(position).equals("Choose One")){

                    ///do nothing

                }else{
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " +item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        progressBar.setVisibility(View.GONE);

        regButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                String email = userName.getText().toString();
                String pwd = userPassword.getText().toString();
                String repwd = userRepassword.getText().toString();


                int selectedItemOfMySpinner = spinner.getSelectedItemPosition();

                if (email.isEmpty() && pwd.isEmpty() && repwd.isEmpty()) {
                    Toast.makeText(SignUp.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    userName.setError("Please enter email address");
                    userName.requestFocus();
                }
                else if(pwd.isEmpty()){
                    userPassword.setError("Please enter your password");
                    userPassword.requestFocus();
                }else if(repwd.isEmpty()){
                    userRepassword.setError("Please rewrite your password");
                    userRepassword.requestFocus();
                }
                else  if(selectedItemOfMySpinner == 0 ) {
                    spinner.requestFocus();
                    TextView selectedTextView = (TextView)spinner.getSelectedView();
                    selectedTextView.setError("Please choose one");
                    selectedTextView.setTextColor(Color.RED);
                    selectedTextView.setText("Please choose one");

                }
                else if (!(email.isEmpty() && pwd.isEmpty() && repwd.isEmpty())) {
                    if (!(pwd.equals(repwd))) {
                        userPassword.setError("Please rewrite the same password");
                        userPassword.requestFocus();
                        userRepassword.setError("Please rewrite the same password");
                        userRepassword.requestFocus();
                    }else{
                    mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.VISIBLE);
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "Sign Up Failed, Please try again", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            } else {
                                String user_id = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                                Map newpost = new HashMap();
                                newpost.put("peopleType", selectedItemOfMySpinner);

                                current_user_db.setValue(newpost);

                                Objects.requireNonNull(mAuth.getCurrentUser()).sendEmailVerification();
                                Toast.makeText(SignUp.this, "Sign Up Successful, Please verify your e-mail", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SignUp.this,MainActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }
                }
                else{
                    Toast.makeText(SignUp.this,"Error Occurred",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}

