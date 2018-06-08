package com.example.user.registro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.user.registro.Modelo.Estudiante;

import java.util.ArrayList;

public class act_lista extends AppCompatActivity {

    ListView list;
    Adapter adapterP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_lista);

        list = findViewById(R.id.list);
        ArrayList<Estudiante> listaEstudiates = new ArrayList<>();

        listaEstudiates = (ArrayList)getIntent().getParcelableArrayListExtra("Lista");
        adapterP = new Adapter(act_lista.this,listaEstudiates);
        list.setAdapter(adapterP);
    }
}
