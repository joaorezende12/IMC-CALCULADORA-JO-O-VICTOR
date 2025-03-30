package com.example.projetoimc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        // Receber os dados passados pela Intent
        Intent intent = getIntent();
        String imc = intent.getStringExtra("imc");
        String classificacao = intent.getStringExtra("classificacao");
        String peso = intent.getStringExtra("peso");
        String altura = intent.getStringExtra("altura");

        // Exibir os dados na tela
        TextView resultadoTextView = findViewById(R.id.textResultado);
        TextView motivacionalTextView = findViewById(R.id.textMotivacional);
        TextView pesoTextView = findViewById(R.id.textPeso);
        TextView alturaTextView = findViewById(R.id.textAltura);

        // Definindo os valores na tela
        resultadoTextView.setText("IMC: " + imc);
        motivacionalTextView.setText("Classificação: " + classificacao);
        pesoTextView.setText("Peso: " + peso);
        alturaTextView.setText("Altura: " + altura);

        // Adicionar mensagem motivacional específica para Obesidade grau 2
        if (classificacao.equals("Obesidade grau 2")) {
            motivacionalTextView.append("\nBusque ajuda médica para controlar o peso e melhorar sua saúde.");
        }
    }

    public void voltartelaprincipal(View view) {
        Intent intent = new Intent(MainActivity7.this, MainActivity.class);
        startActivity(intent);
    }
}