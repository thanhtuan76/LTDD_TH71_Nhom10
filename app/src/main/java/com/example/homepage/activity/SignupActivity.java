package com.example.homepage.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.homepage.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
    private EditText txtUsername, txtLastname, txtFirstname, txtEmail, txtPass, txtConfirmPass;
    private Button btnDone;
    private List<String> listUsername = new ArrayList<>();
    private List<String> listPassword = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Initial();
        GetListUser();

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isHoOK = checkHo();
                boolean isTenOK = checkTen();
                boolean isEmailOK = checkEmail();
                boolean isUsernameOK = checkUsername();
                boolean isPasswordOK = checkPassword();
                boolean isConfirmOK = checkConfirmPassword();
                boolean allOK = isHoOK && isTenOK && isUsernameOK && isEmailOK && isPasswordOK && isConfirmOK;

                if (allOK) {
                    AddUser(txtUsername.getText().toString().trim(), txtPass.getText().toString());
                    Intent intent= new Intent(getApplicationContext(), SigninActivity.class);
                    intent.putExtra("username", txtUsername.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    public void Initial() {
        txtUsername = findViewById(R.id.txtName);
        txtFirstname = findViewById(R.id.txtFirstname);
        txtLastname = findViewById(R.id.txtLastname);
        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);
        txtConfirmPass = findViewById(R.id.txtConfirmPass);
        btnDone = findViewById(R.id.btnDone);
    }

    public boolean checkHo() {
        String ho = txtLastname.getText().toString();
        if (ho.length() == 0) {
            txtLastname.setError("Họ không được bỏ trống");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkTen() {
        String ten = txtFirstname.getText().toString();
        if (ten.length() == 0) {
            txtFirstname.setError("Tên không được bỏ trống");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkUsername() {
        String username = txtUsername.getText().toString().trim();
        if (username.length() == 0) {
            txtUsername.setError("Tên đăng nhập không được bỏ trống");
            return false;
        } else {
            if (username.length() < 8) {
                txtUsername.setError("Tên đăng nhập không được ít hơn 8 kí tự");
                return false;
            } else {
                if (username.contains(" ")) {
                    txtUsername.setError("Tên đăng nhập không được chứa khoảng cách");
                    return false;
                } else {
                    if (!username.matches("[a-zA-Z0-9]*")) {
                        txtUsername.setError("Tên đăng nhập không được chứa kí tự đặc biệt");
                        return false;
                    } else {
                        if (username.length() > 128) {
                            txtUsername.setError("Tên đăng nhập không được quá 128 kí tự");
                            return false;
                        } else {
                            if (listUsername.indexOf(username) != -1) {
                                txtUsername.setError("Tên đăng nhập đã tồn tại");
                                return false;
                            }
                            else
                                return true;
                        }
                    }
                }
            }
        }
    }

    public boolean checkEmail() {
        String email = txtEmail.getText().toString();
        if (email.length() == 0) {
            txtEmail.setError("Email không được bỏ trống");
            return false;
        } else {
            if (!email.contains("@")) {
                txtEmail.setError("Email không hợp lệ");
                return false;
            } else
                return true;
        }
    }

    public boolean checkPassword() {
        String matkhau= txtPass.getText().toString();
        if (matkhau.length() == 0) {
            txtPass.setError("Mật khẩu không được bỏ trống");
            return false;
        } else {
            if (matkhau.length() < 8) {
                txtPass.setError("Mật khẩu không được ít hơn 8 kí tự");
                return false;
            } else {
                if (!matkhau.matches(".*\\d.*")) {
                    txtPass.setError("Mật khẩu phải có ít nhất 1 chữ số");
                    return false;
                } else {
                    if (matkhau.matches("[a-zA-Z0-9]*")) {
                        txtPass.setError("Mật khẩu phải có ít nhất 1 kí tự đặc biệt");
                        return false;
                    } else {
                        boolean hasLowercase = !matkhau.equals(matkhau.toUpperCase());
                        if (!hasLowercase) {
                            txtPass.setError("Mật khẩu phải có ít nhất 1 chữ thường");
                            return false;
                        } else {
                            boolean hasUppercase = !matkhau.equals(matkhau.toLowerCase());
                            if (!hasUppercase) {
                                txtPass.setError("Mật khẩu phải có ít nhất 1 chữ hoa");
                                return false;
                            } else {
                                return true;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean checkConfirmPassword() {
        String matkhau = txtPass.getText().toString();
        String xacnhanmatkhau = txtConfirmPass.getText().toString();
        if (matkhau.length() == 0) {
            txtConfirmPass.setError("Mật khẩu xác nhận không được bỏ trống");
            return false;
        } else {
            if (xacnhanmatkhau.compareTo(matkhau) == 0)
                return true;
            else {
                txtConfirmPass.setError("Mã xác nhận không trùng với mật khẩu");
                return false;
            }
        }
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

    private void AddUser(final String username, final String password) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://5ed91adb4378690016c6ac70.mockapi.io/api/Tablets");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("name", username);
                    jsonParam.put("password", password);

                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}