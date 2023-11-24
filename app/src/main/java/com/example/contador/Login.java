package com.example.contador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText userTxt, passwordTxt;
    Button loginButton, registerButton;
    MyDataBaseHelper myDB;
    String user = "?";


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
                user = cursor.getString(0);
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("MONEY_COUNT", cursor.getString(2));
                i.putExtra("CLICK_VALUE", cursor.getString(3));
                i.putExtra("AUTOCLICK_VALUE", cursor.getString(4));
                i.putExtra("AUTOCLICK_TIME", cursor.getInt(5));
                i.putExtra("UPGRADE_PRECIO_CLICK", cursor.getString(6));
                i.putExtra("UPGRADE_PRECIO_AUTOCLICK", cursor.getString(7));
                i.putExtra("UPGRADE_PRECIO_SPEED", cursor.getString(8));
                i.putExtra("UPGRADE_NIVEL_CLICK", cursor.getString(9));
                i.putExtra("UPGRADE_NIVEL_AUTOCLICK", cursor.getString(10));
                i.putExtra("UPGRADE_NIVEL_SPEED", cursor.getString(11));
                i.putExtra("USER", user);
                startActivity(i);
                finish();
            }
        }
        else Toast.makeText(this, "Usuario no existe.", Toast.LENGTH_SHORT).show();

    }
}