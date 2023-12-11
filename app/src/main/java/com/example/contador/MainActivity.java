package com.example.contador;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    TextView contador, textValorClick, textValorAutoClick, textVelocidadAutoClick;
    ImageView moneda;
    ScaleAnimation fade_in = new ScaleAnimation(0.7f, 1.2f, 0.7f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    MediaPlayer mediaPlayer;
    SoundPool soundPool;
    int soundId;
    BigDecimal num = new BigDecimal("3000"), inc = new BigDecimal("1"), incAuto = new BigDecimal("1");
    BigDecimal precioUpgradeClick = new BigDecimal("100"), precioUpgradeAutoClick = new BigDecimal("200"), precioUpgradeSpeed = new BigDecimal("400");

    int nivelUpgradeClick = 1, nivelUpgradeAutoClick = 1, nivelUpgradeSpeed = 1, tiempoAutoClick = 1000;
    String userNick;
    MyDataBaseHelper myDB;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            user = (User) extras.getSerializable("USER");
            userNick = user.getUser();
            num = new BigDecimal(user.getMoney());
            inc = new BigDecimal(user.getClickValue());
            incAuto = new BigDecimal(user.getAutoClickValue());
            tiempoAutoClick = user.getAutoClickTime();
            precioUpgradeClick = new BigDecimal(user.getUpgradePrecioClick());
            precioUpgradeAutoClick = new BigDecimal(user.getUpgradePrecioAutoClick());
            precioUpgradeSpeed = new BigDecimal(user.getUpgradePrecioSpeed());
            nivelUpgradeClick = user.getUpgradeNivelClick();
            nivelUpgradeAutoClick = user.getUpgradeNivelAutoClick();
            nivelUpgradeSpeed = user.getUpgradeNivelSpeed();
        }

        //  Cargo todos los componentes que voy a usar.
        contador = findViewById(R.id.textocontador);
        textValorClick = findViewById(R.id.textValorClick);
        textValorAutoClick = findViewById(R.id.textValorAutoClick);
        textVelocidadAutoClick = findViewById(R.id.textVelocidadAutoClick);
        moneda = findViewById(R.id.moneda);

        // Cargo animaciones, musica y efectos de sonido
        fade_in.setDuration(100);
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        mediaPlayer.setLooping(true);
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(this, R.raw.coin_sound, 1);


        // Cargo el texto con la funcion que lo formatea e inicio el sumar auto.
        setContText();
        sumarAuto();
    }

    public void sumar(View v) {
        num = num.add(inc);
        moneda.startAnimation(fade_in);
        Toast.makeText(moneda.getContext(), "+1", Toast.LENGTH_SHORT);
        soundPool.play(soundId, 1, 1, 0, 0, 1);
        setContText();
    }

    public void sumarAuto() {
        new Thread(() -> {
            while (true) {
                num = num.add(incAuto);
                runOnUiThread(this::setContText);
                try {
                    Thread.sleep(tiempoAutoClick);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }


    public void setContText() {
        HashMap<String, BigDecimal> VALORES = new LinkedHashMap<>();
        VALORES.put("K", new BigDecimal("1000"));
        VALORES.put("M", new BigDecimal("1000000"));
        VALORES.put("B", new BigDecimal("1000000000"));
        VALORES.put("T", new BigDecimal("1000000000000"));
        VALORES.put("C", new BigDecimal("1000000000000000"));
        VALORES.put("Q", new BigDecimal("1000000000000000000"));
        VALORES.put("S", new BigDecimal("1000000000000000000000"));
        VALORES.put("H", new BigDecimal("10000000000000000000000000"));
        VALORES.put("O", new BigDecimal("10000000000000000000000000000"));
        VALORES.put("N", new BigDecimal("10000000000000000000000000000000"));
        VALORES.put("D", new BigDecimal("10000000000000000000000000000000000"));
        VALORES.put("UD", new BigDecimal("10000000000000000000000000000000000000"));
        VALORES.put("DD", new BigDecimal("1000000000000000000000000000000000000000"));
        VALORES.put("TD", new BigDecimal("1000000000000000000000000000000000000000000"));
        VALORES.put("CD", new BigDecimal("1000000000000000000000000000000000000000000000"));
        VALORES.put("QD", new BigDecimal("1000000000000000000000000000000000000000000000000"));
        VALORES.put("SD", new BigDecimal("1000000000000000000000000000000000000000000000000000"));
        VALORES.put("HD", new BigDecimal("1000000000000000000000000000000000000000000000000000000"));
        VALORES.put("OD", new BigDecimal("1000000000000000000000000000000000000000000000000000000000"));
        VALORES.put("ND", new BigDecimal("1000000000000000000000000000000000000000000000000000000000000"));
        VALORES.put("V", new BigDecimal("10000000000000000000000000000000000000000000000000000000000000000"));

        if (num.compareTo(VALORES.get("K")) < 0)
            contador.setText(num.toString());
        else {
            for (String s : VALORES.keySet()) {
                if (num.compareTo(VALORES.get(s)) >= 0)
                    contador.setText(num.divide(VALORES.get(s)).setScale(2, RoundingMode.HALF_EVEN).toString() + s);
            }
        }
        textValorClick.setText("Click: " + inc.toString());
        textValorAutoClick.setText("Autoclick: " + incAuto.toString());
        textVelocidadAutoClick.setText("Autoclick speed: " + tiempoAutoClick + "m" + "s");
    }

    public void reset(View v) {
        AlertDialog.Builder constructor = new AlertDialog.Builder(this);
        constructor.setCancelable(true);
        constructor.setTitle("Reset");
        constructor.setMessage("¿Seguro que quieres resetear el progreso?");
        constructor.setPositiveButton("RESET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                num = new BigDecimal("0");
                setContText();
            }
        });
        constructor.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = constructor.create();
        dialog.show();

    }

    public void irAPantallaInicio(View view) {

        AlertDialog.Builder constructor = new AlertDialog.Builder(this);
        constructor.setCancelable(true);
        constructor.setTitle("Salir");
        constructor.setMessage("¿Seguro que quieres salir?");
        constructor.setPositiveButton("SALIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                salir();
            }
        });
        constructor.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = constructor.create();
        dialog.show();

    }

    public void salir() {
        Intent i = new Intent(this, PantallaInicio.class);
        startActivity(i);
        finish();
    }

    public void irACompras(View view) {
        Intent i = new Intent(this, Compras.class);
        user = new User(
                user.getUser(),
                user.getPassword(),
                num.toString(),
                inc.toString(),
                incAuto.toString(),
                tiempoAutoClick,
                precioUpgradeClick.toString(),
                precioUpgradeAutoClick.toString(),
                precioUpgradeSpeed.toString(),
                nivelUpgradeClick,
                nivelUpgradeAutoClick,
                nivelUpgradeSpeed);
        i.putExtra("USER", user);
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        myDB = new MyDataBaseHelper(MainActivity.this);
        user = new User(
                user.getUser(),
                user.getPassword(),
                num.toString(),
                inc.toString(),
                incAuto.toString(),
                tiempoAutoClick,
                precioUpgradeClick.toString(),
                precioUpgradeAutoClick.toString(),
                precioUpgradeSpeed.toString(),
                nivelUpgradeClick,
                nivelUpgradeAutoClick,
                nivelUpgradeAutoClick);
        myDB.updateUser(user);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();

    }
}