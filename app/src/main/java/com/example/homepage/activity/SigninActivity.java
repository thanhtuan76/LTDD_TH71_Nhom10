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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.homepage.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        GetListUser();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                finish();
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
                    int idxUsername = listUsername.indexOf(txtUsername.getText().toString().trim());
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

    private void GetListUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://5ed91adb4378690016c6ac70.mockapi.io/api/Tablets", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    String username;
                    String password;
                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            username = jsonObject.getString("name");
                            password = jsonObject.getString("password");
                            listUsername.add(username);
                            listPassword.add(password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}