package com.example.mathchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

         private int actualpoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pregunta = findViewById(R.id.pregunta);
        respuestaInput = findViewById(R.id.respuestaInput);
        okbtn = findViewById(R.id.okBtn);
        puntaje = findViewById(R.id.puntaje);

        nuevaPregunta();

        actualpoint = 0;
        //Toast.makeText(this,"respuesta"+p.getRespuesta(), Toast.LENGTH_LONG).show();

        okbtn.setOnClickListener(
                v->{
                 responder();
            }
        );

        pregunta.setOnClickListener(
                v->{
                    p = new Pregunta();
                    pregunta.setText(p.getPregunta());
                }
        );

    }

    public void responder(){
        String res = respuestaInput.getText().toString();
        int resInt = Integer.parseInt(res);
        int correcta = p.getRespuesta();

        if(resInt == correcta){
            Toast.makeText(this,"Correcto",Toast.LENGTH_SHORT).show();
            actualpoint = actualpoint + 5;
            puntaje.setText(""+actualpoint);
            nuevaPregunta();
        }else{
            Toast.makeText(this,"Mal",Toast.LENGTH_SHORT).show();
            actualpoint = actualpoint - 4;
            puntaje.setText(""+actualpoint);
        }
        respuestaInput.setText("");
    }

    public void nuevaPregunta(){
        p = new Pregunta();
        pregunta.setText(p.getPregunta());
    }








}