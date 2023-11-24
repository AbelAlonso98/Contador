package com.example.contador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "UsersData.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users_login";
    private static final String COLUMN_USER = "login_user";
    private static final String COLUMN_PASSWORD = "login_password";
    private static final String COLUMN_NUM = "game_num";
    private static final String COLUMN_INC = "game_inc";
    private static final String COLUMN_INCAUTO = "game_incauto";
    private static final String COLUMN_TIEMPOAUTOCLICK = "game_tiempoautoclick";
    private static final String COLUMN_PRECIOCLICK = "game_precioclick";
    private static final String COLUMN_PRECIOAUTOCLICK = "game_precioautoclick";
    private static final String COLUMN_PRECIOSPEED = "game_preciospeed";
    private static final String COLUMN_NIVELCLICK = "game_nivelclick";
    private static final String COLUMN_NIVELAUTOCLICK = "game_nivelautoclick";
    private static final String COLUMN_NIVELSPEED = "game_nivelspeed";



    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                        " (" + COLUMN_USER + " TEXT PRIMARY KEY, " +
                        COLUMN_PASSWORD + " TEXT, " +
                        COLUMN_NUM + " TEXT, " +
                        COLUMN_INC + " TEXT, " +
                        COLUMN_INCAUTO + " TEXT, " +
                        COLUMN_TIEMPOAUTOCLICK + " INT, " +
                        COLUMN_PRECIOCLICK + " TEXT, " +
                        COLUMN_PRECIOAUTOCLICK + " TEXT, " +
                        COLUMN_PRECIOSPEED + " TEXT, " +
                        COLUMN_NIVELCLICK + " TEXT, " +
                        COLUMN_NIVELAUTOCLICK + " TEXT, " +
                        COLUMN_NIVELSPEED + " TEXT); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    void addUser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER, user.toUpperCase());
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_NUM, "0");
        cv.put(COLUMN_INC, "1");
        cv.put(COLUMN_INCAUTO, "1");
        cv.put(COLUMN_TIEMPOAUTOCLICK, Integer.parseInt("1000"));
        cv.put(COLUMN_PRECIOCLICK, "100");
        cv.put(COLUMN_PRECIOAUTOCLICK, "200");
        cv.put(COLUMN_PRECIOSPEED, "400");
        cv.put(COLUMN_NIVELCLICK, "1");
        cv.put(COLUMN_NIVELAUTOCLICK, "1");
        cv.put(COLUMN_NIVELSPEED, "1");
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "El usuario ya existe.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Succesfully Added", Toast.LENGTH_SHORT).show();
        }
    }

    void updateUser(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER, user.toUpperCase());
        cv.put(COLUMN_NUM, "0");
        cv.put(COLUMN_INC, "1");
        cv.put(COLUMN_INCAUTO, "1");
        cv.put(COLUMN_TIEMPOAUTOCLICK, Integer.parseInt("1000"));
        cv.put(COLUMN_PRECIOCLICK, "100");
        cv.put(COLUMN_PRECIOAUTOCLICK, "200");
        cv.put(COLUMN_PRECIOSPEED, "400");
        cv.put(COLUMN_NIVELCLICK, "1");
        cv.put(COLUMN_NIVELAUTOCLICK, "1");
        cv.put(COLUMN_NIVELSPEED, "1");
        db.update(TABLE_NAME, cv, COLUMN_USER + "=" +user.toUpperCase(), null);
    }

    Cursor getUser(String user){
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USER + " = '" + user.toUpperCase() + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
