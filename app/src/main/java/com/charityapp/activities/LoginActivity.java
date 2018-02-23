package com.charityapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.charityapp.DatabaseHelper;
import com.charityapp.R;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private Button btnLogin;
    private EditText tvUsername, tvPassword;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(LoginActivity.this);

        tvUsername = findViewById(R.id.login_etUsername);
        tvPassword = findViewById(R.id.login_etPassword);
        btnLogin = findViewById(R.id.login_btnLogin);
        tvRegister = findViewById(R.id.login_tvRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isValid()) {
                    if(databaseHelper.checkUser(tvUsername.getText().toString(), tvPassword.getText().toString())){
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isValid() {

        boolean isvalid = true;

        if (TextUtils.isEmpty(tvUsername.getText().toString())) {
            tvUsername.setError("Please Enter Username");
            isvalid = false;
        }

        if (TextUtils.isEmpty(tvPassword.getText().toString())) {
            tvPassword.setError("Please Enter Mobile Number");
            isvalid = false;
        }

        return isvalid;
    }

}
