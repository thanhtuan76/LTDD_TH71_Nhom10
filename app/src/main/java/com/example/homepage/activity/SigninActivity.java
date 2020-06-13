package com.example.homepage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homepage.R;

import java.util.ArrayList;
import java.util.List;

public class SigninActivity extends AppCompatActivity {
    private EditText txtUsername, txtPassword;
    private Button btnSignUp, btnSignIn;
    private List<String> listUsername = new ArrayList<>();
    private List<String> listPassword = new ArrayList<>();

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

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUsernameOK = checkUsername();
                boolean isPasswordOK = checkPassword();
                boolean allOK = isUsernameOK && isPasswordOK;

                if (allOK) {
                    RetrieveUser();
                    int idxUsername = listUsername.indexOf(txtUsername.getText().toString());
                    if (idxUsername != -1) {
                        String strInputPass = txtPassword.getText().toString();
                        String strListPass = listPassword.get(idxUsername);
                        if (strInputPass.equals(strListPass)) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("user_name", listUsername.get(idxUsername));
                            startActivity(intent);
                        } else {
                            txtPassword.setError("Mật khẩu không chính xác");
                        }
                    } else {
                        txtUsername.setError("Tên đăng nhập không tồn tại");
                    }
                }
            }
        });
    }

    public void Initial() {
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnSignUp = findViewById(R.id.btnActSignUp);
        btnSignIn = findViewById(R.id.btnActSignIn);
        String strUsername = getIntent().getStringExtra("username");

        if (strUsername != null || strUsername != "")
            txtUsername.setText(strUsername);
    }

    public boolean checkUsername() {
        String username = txtUsername.getText().toString();
        if (username.length() == 0) {
            txtUsername.setError("Tên đăng nhập không được bỏ trống");
            return false;
        }
        return true;
    }

    public boolean checkPassword() {
        String pass= txtPassword.getText().toString();
        if (pass.length() == 0) {
            txtPassword.setError("Mật khẩu không được bỏ trống");
            return false;
        }
        return true;
    }

    public void RetrieveUser() {
        String URL = "content://com.example.homepage.activity.UserProvider";
        Uri users = Uri.parse(URL);
        Cursor c =  managedQuery(users, null, null, null, "username");

        if (c.moveToFirst()) {
            do {
                String strName = c.getString(c.getColumnIndex(UserProvider.USERNAME));
                String strPass = c.getString(c.getColumnIndex(UserProvider.PASSWORD));
                listUsername.add(strName);
                listPassword.add(strPass);
            } while (c.moveToNext());
        }
    }
}