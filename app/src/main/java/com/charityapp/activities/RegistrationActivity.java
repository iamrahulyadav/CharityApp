package com.charityapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.charityapp.DatabaseHelper;
import com.charityapp.R;
import com.charityapp.RegistrationDataPojo;

public class RegistrationActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private Button btnContinue;
    private TextView btnLogin;
    private EditText tvUsername, tvPassword, tvEmail, tvMobile;

    RegistrationDataPojo registrationDataPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        databaseHelper = new DatabaseHelper(RegistrationActivity.this);

        registrationDataPojo = new RegistrationDataPojo();

        btnContinue = findViewById(R.id.regi_btnSignUp);
        tvUsername = findViewById(R.id.regi_etUsername);
        tvPassword = findViewById(R.id.regi_etPassword);
        tvEmail = findViewById(R.id.regi_etEmail);
        tvMobile = findViewById(R.id.regi_etMobile);
        btnLogin = findViewById(R.id.regi_btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registrationDataPojo.setName(tvUsername.getText().toString());
                registrationDataPojo.setContact(tvMobile.getText().toString());
                registrationDataPojo.setEmail(tvEmail.getText().toString());
                registrationDataPojo.setPassword(tvPassword.getText().toString());

                if (isValid()){
                    databaseHelper.addRegistrationData(registrationDataPojo);
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
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
