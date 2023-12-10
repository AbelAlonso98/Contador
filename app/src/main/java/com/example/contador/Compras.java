package com.example.contador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author Abel Alonso Jiménez - ZTL55769@educastur.es
 */
public class Compras extends AppCompatActivity {
    // Variables utilizadas para el funcionamiento del juego

    BigDecimal precioUpgradeClick = new BigDecimal("100"), precioUpgradeAutoClick = new BigDecimal("200"), precioUpgradeSpeed = new BigDecimal("400"), num, inc, incAuto;;
    int nivelUpgradeClick = 0, nivelUpgradeAutoClick = 0, nivelUpgradeSpeed = 0, nivelPosibleUpgradeClick, nivelPosibleUpgradeAutoClick, nivelPosibleUpgradeSpeed, tiempoAutoClick;
    private final int maxNivelUpgradeClick = 10, maxNivelUpgradeAutoClick = 5, maxNivelUpgradeSpeed = 3;

    // Variables utilizadas para instanciar los componentes
    Button botonSuma, botonSumaAuto, botonAutoSpeed;
    TextView textValorClick, textValorAutoClick, textVelocidadAutoClick, textMoneyCount, clickUpgradeTitle, autoClickUpgradeTitle, autoClickSpeedUpgradeTitle;
    SoundPool soundPool;
    int soundId;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = (User) extras.getSerializable("USER");
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


        botonSuma = findViewById(R.id.botonMejora);
        botonSumaAuto = findViewById(R.id.botonSumarAuto);
        botonAutoSpeed = findViewById(R.id.botonSumarAutoSpeed);
        textValorClick = findViewById(R.id.textValorClick);
        textValorAutoClick = findViewById(R.id.textValorAutoClick);
        textVelocidadAutoClick = findViewById(R.id.textVelocidadAutoClick);
        textMoneyCount = findViewById(R.id.txtMoneyCount);
        clickUpgradeTitle = findViewById(R.id.tituloMejora);
        autoClickUpgradeTitle = findViewById(R.id.tituloMejora2);
        autoClickSpeedUpgradeTitle = findViewById(R.id.tituloMejora3);
        setContText();

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(this, R.raw.upgrade_sound, 1);
    }

    public void volverAJugar(View v) {
        Intent i = new Intent(this, MainActivity.class);
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

    public void setContText() {
        int aux;
        // El texto de los botones es dinamico, se cargan aquí
        if (nivelUpgradeClick < maxNivelUpgradeClick) {
            botonSuma.setText(precioUpgradeClick + "$");

        } else {
            botonSuma.setText("MAX");
            botonSuma.setEnabled(false);
            botonSuma.setAlpha(0.65f);
        }
        if (nivelUpgradeAutoClick < maxNivelUpgradeAutoClick) {
            botonSumaAuto.setText(precioUpgradeAutoClick + "$");
        } else {
            botonSumaAuto.setText("MAX");
            botonSumaAuto.setEnabled(false);
            botonSumaAuto.setAlpha(0.65f);
        }
        if (nivelUpgradeSpeed < maxNivelUpgradeSpeed) {
            botonAutoSpeed.setText(precioUpgradeSpeed + "$");
        } else {
            botonAutoSpeed.setText("MAX");
            botonAutoSpeed.setEnabled(false);
            botonAutoSpeed.setAlpha(0.65f);
        }
        // Cargo los indicadores de niveles
        clickUpgradeTitle.setText("Valor del click (" + nivelUpgradeClick + "/" + maxNivelUpgradeClick + ")");
        autoClickUpgradeTitle.setText("Valor del auto-click (" + nivelUpgradeAutoClick + "/" + maxNivelUpgradeAutoClick + ")");
        autoClickSpeedUpgradeTitle.setText("AutoClick speed (" + nivelUpgradeSpeed + "/" + maxNivelUpgradeSpeed + ")");

        // Cargo los datos de la barra superior
        textValorClick.setText("Click: " + inc.toString());
        textValorAutoClick.setText("Autoclick: " + incAuto.toString());
        textVelocidadAutoClick.setText("Autoclick speed: " + tiempoAutoClick + "m" + "s");

        // Formateo el contador.
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
            textMoneyCount.setText(num.toString());
        else {
            for (String s : VALORES.keySet()) {
                if (num.compareTo(VALORES.get(s)) >= 0)
                    textMoneyCount.setText(num.divide(VALORES.get(s)).setScale(2, RoundingMode.HALF_EVEN).toString() + s);
            }
        }
    }

    // Metodos para botones de la primera mejora (Click value)
    public void mejorarClick(View v) {
        if (num.compareTo(precioUpgradeClick) >= 0 && nivelUpgradeClick < maxNivelUpgradeClick) {
            inc = inc.add(BigDecimal.valueOf(1));
            num = num.subtract(precioUpgradeClick);
            nivelUpgradeClick++;
            nivelPosibleUpgradeClick = maxNivelUpgradeClick - nivelUpgradeClick;
            precioUpgradeClick = precioUpgradeClick.multiply(BigDecimal.valueOf(2));
            setContText();
            soundPool.play(soundId, 1, 1, 0, 0, 1);
        }
    }


    // Metodos para botones de la segunda mejora (AutoClick value)
    public void mejorarAutoClick(View v) {
        if (num.compareTo(precioUpgradeAutoClick) >= 0 && nivelUpgradeAutoClick < maxNivelUpgradeAutoClick) {
            incAuto = incAuto.add(BigDecimal.valueOf(1));
            num = num.subtract(precioUpgradeAutoClick);
            nivelUpgradeAutoClick++;
            nivelPosibleUpgradeAutoClick--;
            precioUpgradeAutoClick = precioUpgradeAutoClick.multiply(BigDecimal.valueOf(2));
            setContText();
            soundPool.play(soundId, 1, 1, 0, 0, 1);
        }
    }

    // Metodos para botones de la tercera mejora (AutoClick speed)
    public void mejorarAutoClickSpeed(View v) {
        if (num.compareTo(precioUpgradeSpeed) >= 0 && nivelUpgradeSpeed < maxNivelUpgradeSpeed) {
            num = num.subtract(precioUpgradeSpeed);
            tiempoAutoClick = (int) (tiempoAutoClick / 1.25);
            nivelUpgradeSpeed++;
            precioUpgradeSpeed = precioUpgradeSpeed.multiply(BigDecimal.valueOf(3));
            setContText();
            soundPool.play(soundId, 1, 1, 0, 0, 1);
        }
    }
}