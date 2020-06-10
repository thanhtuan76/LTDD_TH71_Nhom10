package com.example.homepage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.homepage.R;

public class SigninActivity extends AppCompatActivity {
    private EditText txtUsername, txtPass;
    private Button btnSignUp, btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Initial();
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void Initial() {
        txtUsername = findViewById(R.id.txtUsername);
        txtPass = findViewById(R.id.txtPassword);
        btnSignUp = findViewById(R.id.btnSubSignUp);
        btnDone = findViewById(R.id.btnDone);
    }
}