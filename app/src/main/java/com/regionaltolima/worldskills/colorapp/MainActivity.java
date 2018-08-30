package com.regionaltolima.worldskills.colorapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{

    Button btn_Iniciar, btn_Puntajes, btn_Configuracion;
    ImageView logo;
    CountDownTimer count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        btn_Iniciar = findViewById(R.id.iniciar);
        btn_Puntajes = findViewById(R.id.ver_puntajes);
        btn_Configuracion = findViewById(R.id.configurar);

        //recargarImagen();
    }

    /*public void recargarImagen(){
        logo.setImageDrawable(getDrawable(R.drawable.logo));
        count = new CountDownTimer(3000,1500) {
            @Override
            public void onTick(long millisUntilFinished) {
                logo.setImageDrawable(getDrawable(R.drawable.logo2));
            }

            @Override
            public void onFinish() {
                recargarImagen();
            }
        }.start();

    }¨*/

    public void listenerButton(View v) {
       int boton = v.getId();
        Intent ir = null;

       switch (boton){
           case R.id.iniciar : ir = new Intent(getApplicationContext(), GameActivity.class);
               break;
           case R.id.ver_puntajes : mostrarPuntajes();
               break;
           case R.id.configurar : ir = new Intent(getApplicationContext(), SetActivity.class);
               break;
       }
       startActivity(ir);
    }

    public void mostrarPuntajes() {
    }
}
