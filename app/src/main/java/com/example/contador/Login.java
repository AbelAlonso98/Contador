package com.example.contador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText userTxt, passwordTxt;
    Button loginButton, registerButton;
    MyDataBaseHelper myDB;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userTxt = findViewById(R.id.userTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
        myDB = new MyDataBaseHelper(Login.this);
    }


    public void irARegistro(View view) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
        finish();
    }

    public void login(View view) {
        Cursor cursor = myDB.getUser(userTxt.getText().toString());
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            if (!cursor.getString(1).equals(passwordTxt.getText().toString())) {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                passwordTxt.setText("");
            } else {
                user = new User(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getInt(9),
                        cursor.getInt(10),
                        cursor.getInt(11));
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("USER", user);
                startActivity(i);
                finish();
            }
        }
        else Toast.makeText(this, "Usuario no existe.", Toast.LENGTH_SHORT).show();

    }
}