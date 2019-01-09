package com.example.timibaba.againmapworking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmai;
    private Button buttonLogOut;
    private Button buttonGoToMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmai = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmai.setText("Welcome " + user.getEmail());
        buttonLogOut = (Button) findViewById(R.id.buttonLogOut);
        buttonGoToMaps = (Button) findViewById(R.id.buttonGoToMap);

        buttonLogOut.setOnClickListener(this);
        buttonGoToMaps.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v== buttonGoToMaps){
            startActivity(new Intent(this, MapsActivity.class));
        }
        if (v== buttonLogOut){
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
