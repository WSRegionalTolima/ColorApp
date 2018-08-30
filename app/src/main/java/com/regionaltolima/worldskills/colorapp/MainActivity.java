package com.regionaltolima.worldskills.colorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    Button btn_Iniciar, btn_Puntajes, btn_Configuracion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Iniciar = findViewById(R.id.iniciar);
        btn_Puntajes = findViewById(R.id.ver_puntajes);
        btn_Configuracion = findViewById(R.id.configurar);

    }

    public void listenerButton(View v) {
       int boton = v.getId();
        Intent ir = null;

       switch (boton){
           case R.id.iniciar : ir = new Intent(getApplicationContext(), GameActivity.class);
               break;
           case R.id.ver_puntajes : ir = new Intent(getApplicationContext(), GameActivity.class);
               break;
           case R.id.configurar : ir = new Intent(getApplicationContext(), GameActivity.class);
               break;
       }
       startActivity(ir);
    }
}
