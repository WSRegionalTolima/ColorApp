package com.regionaltolima.worldskills.colorapp;

import android.content.Intent;
import android.icu.text.TimeZoneFormat;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.regionaltolima.worldskills.colorapp.Clases.Stroop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private static final int TIEMPO_DEFECTO = 2000;
    private static int INTENTOS;
    private static int PALABRAS;
    private static int CORRECTAS;
    private static int INCORRECTAS;
    private static int PAUSAS;
    private static int INTERACCION;


    TextView stroopWord, stroopIntents, stroopCWords, stroopWords;
    Stroop stroop;

    Button btn1, btn2, btn3, btn4;
    ImageButton btn_Pausa;
    boolean diferente;

    CountDownTimer timer;

    ArrayList<Integer> colores, nombres, draw, sdraw;
    ArrayList<Button> botones;

    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        r = new Random();

        timer = null;

        INTENTOS = 3;
        PALABRAS = 0;
        CORRECTAS = 0;
        INCORRECTAS = 0;
        PAUSAS = 0;
        INTERACCION = 0;

        stroopWord = findViewById(R.id.tv_stroop_word);
        stroopWords = findViewById(R.id.tv_stroop_words);
        stroopIntents = findViewById(R.id.tv_stroop_intents);
        stroopCWords = findViewById(R.id.tv_stroop_correct);

        btn1 = findViewById(R.id.b1);
        btn2 = findViewById(R.id.b2);
        btn3 = findViewById(R.id.b3);
        btn4 = findViewById(R.id.b4);
        btn_Pausa = findViewById(R.id.btn_Pausa);

        iniciarArrayList();

        iniciarNombres();
        iniciarColores();
        iniciarColoresBotones();
        iniciarBotones();
        shuffleDraw();

        setStroop();
        setBotones();
        setStats();
        iniciarTemp();

        btn_Pausa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                INTERACCION ++;
                verificarInteracciones(INTERACCION);
                PAUSAS ++;
                verificarPausas(PAUSAS);
                Toast.makeText(getApplicationContext(),""+PAUSAS, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void verificarInteracciones(int interaccion) {
        if (interaccion == 1){
            timer.cancel();
        }else if (interaccion == 2){
            timer.start();
            INTERACCION = 0;
        }
    }


    private void verificarPausas(int pausas) {
        if (pausas >= 4){
            btn_Pausa.setVisibility(View.INVISIBLE);
        }
    }

    public void iniciarArrayList() {
        colores = new ArrayList<>();
        nombres = new ArrayList<>();
        botones = new ArrayList<>();
        draw = new ArrayList<>();
        sdraw = new ArrayList<>();
    }

    /**
     * Metodo que Inicia el Juego
     */
     public void iniciarTemp(){

             timer = new CountDownTimer(TIEMPO_DEFECTO, 1000) {

                 @Override
                 public void onTick(long millisUntilFinished) {

                 }

                 @Override
                 public void onFinish() {

                     setStroop();
                     setBotones();
                     INTENTOS --;
                     PALABRAS ++;
                     INCORRECTAS ++;

                     setStats();
                     iniciarTemp();
                 }
             };

         if (verificarIntentos(INTENTOS)){
             timer.start();
         }else {
             timer.cancel();
         }
    }

    private boolean verificarIntentos(int intentos) {
         if (intentos == 0){
             timer.cancel();
             Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
             Bundle bundle = new Bundle();
             bundle.putInt("total_Palabras", PALABRAS);
             bundle.putInt("total_PalabrasC", CORRECTAS);
             bundle.putInt("total_PalabrasI", INCORRECTAS);
             intent.putExtras(bundle);
             startActivity(intent);
             finish();
         }
         return true;
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

        setOnClickBotones();
    }

    private void setOnClickBotones(){
        for (int i = 0; i < botones.size(); i++){
            final int finalI = i;
            botones.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "Prueba", Toast.LENGTH_SHORT).show();
                   // comprobarIgualdad(botones.get(finalI));
                }
            });
        }
    }

    private void comprobarIgualdad(Button b) {

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
     * Metodo para guardar los Colores de los botones
     */
    public void shuffleDraw(){
        sdraw.add(R.drawable.azul);
        sdraw.add(R.drawable.amarillo);
        sdraw.add(R.drawable.rojo);
        sdraw.add(R.drawable.verde);
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

    /**
     * Metodo que cambia aleatoriamente el color de los botones
     */
    public void setBotones() {
        for (int i = 0; i < botones.size(); i++) {
            botones.get(i).setBackground(getDrawable(sdraw.get(i)));
        }
        Collections.shuffle(sdraw);
    }

    private void setStats(){
        stroopIntents.setText("Intentos: " + INTENTOS);
        stroopWords.setText("Palabras: " + PALABRAS);
    }

    /**
     * Metodo para generar un Numero aleatorio entre 0 y 3
     * @return devuelve un numero aleatorio entre el limite
     */
    public int getRandomA(){
        return r.nextInt(4);
    }

    /**
     * Metodo para generar un Numero aleatorio entre 0 y 3
     * @return devuelve un numero aleatorio entre el limite
     */
    public int getRandomB(){
        return r.nextInt(4);
    }
}
