package com.example.timibaba.againmapworking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private Button buttonToSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonRegister = (Button) findViewById(R.id.register_now_button);
        buttonToSignIn = (Button) findViewById(R.id.to_sign_in);
        editTextEmail = (EditText) findViewById(R.id.txtEmail);
        editTextPassword = (EditText) findViewById(R.id.txtPassword);

        buttonRegister.setOnClickListener(this);
        buttonToSignIn.setOnClickListener(this);
    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        /*if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please enter the email",Toast.LENGTH_SHORT).show();
            //stop the function execution
            //return;
        }
        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter the password",Toast.LENGTH_SHORT).show();
            //stop the function execution
            //return;
        }*/
        //if validations are okay
        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //start the activity
                            Toast.makeText(Register.this, "registration successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, ProfileActivity.class));

                        }else{
                            Toast.makeText(Register.this, "failed registration", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == buttonRegister)
        {
            registerUser();
        }
        if(v== buttonToSignIn){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
