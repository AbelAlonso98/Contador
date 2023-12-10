package com.example.contador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


    }

    public void irALogin(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }

    public void register(View view) {
        if (!confirmPasswordTxt.getText().toString().equals(passwordTxt.getText().toString()))
            Toast.makeText(this, "Las contraseñas deben coincidir.", Toast.LENGTH_SHORT).show();
        else {
            MyDataBaseHelper myDB = new MyDataBaseHelper(Register.this);
            myDB.addUser(userTxt.getText().toString().trim(), passwordTxt.getText().toString().trim());
        }
    }
}