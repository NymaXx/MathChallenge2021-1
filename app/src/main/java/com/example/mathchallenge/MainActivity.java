package com.example.mathchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
         private TextView pregunta;
         private EditText respuestaInput;
         private Button okbtn;
         private Pregunta p;
         private TextView puntaje;
         private TextView cron;
         private Button tryAgainButton;
         private Boolean isTouching = false;
         private int tiempo = 1500;

         private int actualpoint;
         private int counter = 30;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tryAgainButton = findViewById(R.id.tryAgainButton);
        pregunta = findViewById(R.id.pregunta);
        respuestaInput = findViewById(R.id.respuestaInput);
        okbtn = findViewById(R.id.okBtn);
        puntaje = findViewById(R.id.puntaje);
        cron = findViewById(R.id.cron);


        puntaje.setText("Puntaje: ");
        chronometer();
        nuevaPregunta();

        actualpoint = 0;

        okbtn.setOnClickListener(
                v->{
                 responder();
            }
        );

        tryAgainButton.setOnClickListener(
                v->{
                    restart();
                }
        );

        pregunta.setOnTouchListener(
                (view, event)-> {
                    switch(event.getAction()){

                        case MotionEvent.ACTION_DOWN:
                            isTouching = true;
                            new Thread(
                                    ()->{

                                        for(int i=0 ; i<20 ; i++){
                                            try {
                                                Thread.sleep(75);
                                                if(isTouching == false){
                                                    return;
                                                }else{

                                                }
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                        runOnUiThread(
                                                ()->{
                                                   /* Toast.makeText(this,"sirve",
                                                                    Toast.LENGTH_LONG).show();*/
                                                    p = new Pregunta();
                                                    pregunta.setText(p.getPregunta());
                                                }
                                        );
                                    }
                            ).start();
                            break;

                            case MotionEvent.ACTION_UP:
                                isTouching = false;
                                break;
                    }
                    return true;
                }
        );

        //cambio depregunta con click

        /*pregunta.setOnClickListener(
                v->{
                    p = new Pregunta();
                    pregunta.setText(p.getPregunta());
                }
        );*/

}

    public void responder(){
        String res = respuestaInput.getText().toString();
        int resInt = Integer.parseInt(res);
        int correcta = p.getRespuesta();

        if(resInt == correcta){
            //Toast.makeText(this,"Correcto",Toast.LENGTH_SHORT).show();
            actualpoint = actualpoint + 5;
            nuevaPregunta();
        }else{
            //Toast.makeText(this,"Mal",Toast.LENGTH_SHORT).show();
            actualpoint = actualpoint - 4;
        }
        respuestaInput.setText("");
        puntaje.setText("Puntaje: "+actualpoint);
    }

    public void nuevaPregunta(){
        p = new Pregunta();
        pregunta.setText(p.getPregunta());
    }

    public void chronometer() {
        new Thread(
                () -> {
                    while (counter > 0) {
                        counter--;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //cron.setText("Tiempo: " + counter);
                        if(counter == 0){
                            tryAgainButton.setVisibility(View.VISIBLE);
                        }
                    }
                }
        ).start();
    }

    public void restart(){
        counter = 30;
        nuevaPregunta();
        actualpoint = 0;

        }
    }








