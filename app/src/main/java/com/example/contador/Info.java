package com.example.contador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contador.rvstuff.Colaborador;
import com.example.contador.rvstuff.ColaboradorAdapter;

import java.util.Arrays;
import java.util.List;

public class Info extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        List<Colaborador> centros = Arrays.asList(
                new Colaborador("Abel Alonso Jiménez", "Producción de código", R.drawable.img_abel),
                new Colaborador("Nuria Valdés Cuesta", "Producción gráfica", R.drawable.img_nuria),
                new Colaborador("Alfonso García Martín", "Direccion ejecutiva", R.drawable.img_fonsi),
                new Colaborador("Ignacio Fernández Prieto", "Coproducción de código", R.drawable.img_nacho),
                new Colaborador("Pablo Eguilaz Pérez ", "Tester principal", R.drawable.img_pablo),
                new Colaborador("Carlos Manso González", "Team leader", R.drawable.img_carlos)
        );
        ColaboradorAdapter adapter = new ColaboradorAdapter(this, R.layout.item, centros);
        ListView dataList = findViewById(R.id.dataList);
        dataList.setAdapter(adapter);
        dataList.setOnItemClickListener(this);
//        setListAdapter(adapter);
//        getListView().setOnItemClickListener(this);
    }

    public void irAPantallaInicio(View view) {
        Intent i = new Intent(this, PantallaInicio.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}