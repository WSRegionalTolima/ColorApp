package com.regionaltolima.worldskills.colorapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.regionaltolima.worldskills.colorapp.Clases.Stroop;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private static final int TIEMPO_DEFECTO = 3000;

    TextView stroopWord, numero;
    Stroop stroop;

    Button btn, btn1, btn2, btn3, btn4;
    boolean diferente;

    CountDownTimer timer;

    ArrayList<Integer> colores, nombres, draw;
    ArrayList<Button> botones;

    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        r = new Random();

        stroopWord = findViewById(R.id.tv_stroop_word);
        numero = findViewById(R.id.numero);
        btn = findViewById(R.id.boton);

        colores = new ArrayList<>();
        nombres = new ArrayList<>();
        botones = new ArrayList<>();
        draw = new ArrayList<>();

        iniciarNombres();
        iniciarColores();
        iniciarColoresBotones();
        iniciarBotones();

        iniciarJuego();

        setStroop();
        iniciarTemp();
        btn.setBackground(getDrawable(R.drawable.amarillo));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStroop();
            }
        });

    }



    public void iniciarJuego() {

    }
     public void iniciarTemp(){

            timer = new CountDownTimer(TIEMPO_DEFECTO, 1000) {
            int i = 0;
            @Override
            public void onTick(long millisUntilFinished) {
                i ++;
                numero.setText(i + "");
            }

            @Override
            public void onFinish() {
                setStroop();
                iniciarTemp();
                btn.setBackground(getDrawable(draw.get(getRandomA())));
            }
        }.start();
    }

    /**
     * Metodo que Asigna Los Colores en un ArrayList Para despues asignarlo a la palabra
     */
    public void iniciarNombres() {
        nombres.add(R.string.Azul);
        nombres.add(R.string.Amarillo);
        nombres.add(R.string.Rojo);
        nombres.add(R.string.Verde);
    }

    /**
     * Metodo que Asigna Los Nombres de Colores en un ArrayList Para despues asignarlo a la palabra
     */
    public void iniciarColores() {
        colores.add(R.color.Azul);
        colores.add(R.color.Amarillo);
        colores.add(R.color.Rojo);
        colores.add(R.color.Verde);
    }

    /**
     * Metodo que le asigna un los drawable de los botones al arreglo
     */
    private void iniciarBotones() {
        botones.add(btn1);
        botones.add(btn2);
        botones.add(btn3);
        botones.add(btn4);
    }

    /**
     * Metodo que asigna los drawables a arreglo para posteriormente asignarlos aleatoriamente
     */
    public void iniciarColoresBotones(){
        draw.add(R.drawable.azul);
        draw.add(R.drawable.amarillo);
        draw.add(R.drawable.rojo);
        draw.add(R.drawable.verde);
    }



    /**
     * Resetea los valores de color y texto a la palabra
     */
    public void setStroop() {
        int nombre, color;

        do {
            nombre = getRandomA();
            color = getRandomB();

            if (nombre == color){
                diferente = false;
            }else{

                diferente = true;
                stroop = new Stroop(getString(nombres.get(nombre)), colores.get(color));

                stroopWord.setText(nombres.get(nombre));
                stroopWord.setTextColor(getColor(stroop.getColorPalabra()));
            }

        }while(!diferente);


    }

    public void setBotones(){

        for (int i = 0; i < botones.size(); i++){
            botones.get(i).setBackground(getDrawable(draw.get(getRandomB())));

        }
    }

    public int getRandomA(){
        return r.nextInt(4);
    }

    public int getRandomB(){
        return r.nextInt(4);
    }
}
