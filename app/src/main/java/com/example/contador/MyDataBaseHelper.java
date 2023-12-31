package com.example.contador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UsersData.db", TABLE_NAME = "users_login", COLUMN_USER = "login_user", COLUMN_PASSWORD = "login_password",
            COLUMN_NUM = "game_num", COLUMN_INC = "game_inc", COLUMN_INCAUTO = "game_incauto", COLUMN_TIEMPOAUTOCLICK = "game_tiempoautoclick",
            COLUMN_PRECIOCLICK = "game_precioclick", COLUMN_PRECIOAUTOCLICK = "game_precioautoclick", COLUMN_PRECIOSPEED = "game_preciospeed",
            COLUMN_NIVELCLICK = "game_nivelclick", COLUMN_NIVELAUTOCLICK = "game_nivelautoclick", COLUMN_NIVELSPEED = "game_nivelspeed";


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
                        COLUMN_NIVELCLICK + " INT, " +
                        COLUMN_NIVELAUTOCLICK + " INT, " +
                        COLUMN_NIVELSPEED + " INT); ";
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
        cv.put(COLUMN_TIEMPOAUTOCLICK, 1000);
        cv.put(COLUMN_PRECIOCLICK, "100");
        cv.put(COLUMN_PRECIOAUTOCLICK, "200");
        cv.put(COLUMN_PRECIOSPEED, "400");
        cv.put(COLUMN_NIVELCLICK, 1);
        cv.put(COLUMN_NIVELAUTOCLICK, 1);
        cv.put(COLUMN_NIVELSPEED, 1);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "El usuario ya existe.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Succesfully Added", Toast.LENGTH_SHORT).show();
        }

    }

    void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER, user.getUser().toUpperCase());
        cv.put(COLUMN_NUM, user.getMoney());
        cv.put(COLUMN_INC, user.getClickValue());
        cv.put(COLUMN_INCAUTO, user.getAutoClickValue());
        cv.put(COLUMN_TIEMPOAUTOCLICK, user.getAutoClickTime());
        cv.put(COLUMN_PRECIOCLICK, user.getUpgradePrecioClick());
        cv.put(COLUMN_PRECIOAUTOCLICK, user.getUpgradePrecioAutoClick());
        cv.put(COLUMN_PRECIOSPEED, user.getUpgradePrecioSpeed());
        cv.put(COLUMN_NIVELCLICK, user.getUpgradeNivelClick());
        cv.put(COLUMN_NIVELAUTOCLICK, user.getUpgradeNivelAutoClick());
        cv.put(COLUMN_NIVELSPEED, user.getUpgradeNivelSpeed());
        long result = db.update(TABLE_NAME, cv, COLUMN_USER + " = ?", new String[]{user.getUser().toUpperCase()});
        if (result == -1)
            Toast.makeText(context, "Error updating", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Updated correctly", Toast.LENGTH_SHORT).show();
    }

    Cursor getUser(String user) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USER + " = '" + user.toUpperCase() + "';";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor getUsers() {
        String query = "SELECT " + COLUMN_USER + ", " + COLUMN_NUM + " FROM " + TABLE_NAME +
                " ORDER BY CAST(" + COLUMN_NUM + " AS INT) DESC;";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
