package com.example.jangbogo.view;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jangbogo.R;
import com.example.jangbogo.model.User;
import com.example.jangbogo.service.JangBoGoAPI;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView txtSignUp;
    private Dialog dialog;
    private EditText editId;
    private EditText editPass;
    private TextView txtCancle;
    private TextView txtJoin;
    private TextInputEditText putId;
    private TextInputEditText putPassword;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_join);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        editId = dialog.findViewById(R.id.editId);
        editPass = dialog.findViewById(R.id.editPasswd);
        txtCancle = dialog.findViewById(R.id.txtCancle);
        txtJoin = dialog.findViewById(R.id.txtJoin);
        putId = (TextInputEditText) findViewById(R.id.putId);
        putPassword = (TextInputEditText) findViewById(R.id.putPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtSignUp = (TextView) findViewById(R.id.txtSignUp);

        txtCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        txtJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> request = new HashMap<>();
                request.put("username", editId.getText().toString());
                request.put("password", editPass.getText().toString());
                Call<User> call = JangBoGoAPI.getInstance().getService().signUp(request);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User res = response.body();
                        if (res == null) {
                            Toast.makeText(getApplicationContext(), "중복된 아이디입니다!", Toast.LENGTH_SHORT).show();
                        }else {
                            dialog.cancel();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = putId.getText().toString();
                String password = putPassword.getText().toString();
                JangBoGoAPI.getInstance().getService().signIn(username, password).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            User res = response.body();
                            if (res != null) {
                                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                                intent.putExtra("user", res);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                dialog.show();

            }
        });
    }
}
