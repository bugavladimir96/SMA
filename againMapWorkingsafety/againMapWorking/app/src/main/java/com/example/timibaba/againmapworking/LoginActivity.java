package com.example.timibaba.againmapworking;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private Button buttonToRegister;
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!= null){
            //profile activity here
            Toast.makeText(this, "yuhuuu",Toast.LENGTH_SHORT).show();
        }

        buttonSignIn = (Button) findViewById(R.id.sign_in_button);
        buttonToRegister = (Button) findViewById(R.id.to_register_button);
        editTextEmail = (EditText) findViewById(R.id.txtuserName);
        editTextPassword = (EditText) findViewById(R.id.txtPassword);

        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        buttonToRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        if (v == buttonSignIn)
        {
            userLogin();
        }
        if(v== buttonToRegister){
            finish();
            startActivity(new Intent(this, Register.class));
        }
    }

    private void userLogin() {
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
        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful())
                        {
                            //start startActivity(new iIntent(getApplicationContext(),ProfileActivity.class));
                            finish();
                            Toast.makeText(LoginActivity.this , "yuhuu", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                        }
                    }
                });
    }

}

