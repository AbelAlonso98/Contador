package com.example.contador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText userTxt, passwordTxt, confirmPasswordTxt;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userTxt = findViewById(R.id.userTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        confirmPasswordTxt = findViewById(R.id.confirmPasswordTxt);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDataBaseHelper myDB = new MyDataBaseHelper(Register.this);
                myDB.addUser(userTxt.getText().toString().trim(), passwordTxt.getText().toString().trim());
            }
        });


    }
    public void irALogin(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }
}