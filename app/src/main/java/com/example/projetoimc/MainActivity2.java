package com.example.projetoimc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void calcularImc(View view) {
        EditText campoAltura = findViewById(R.id.editTextAltura);
        EditText campoPeso = findViewById(R.id.editTextPeso);
        String altura = campoAltura.getText().toString();
        String peso = campoPeso.getText().toString();

        // Verifica se os campos estão preenchidos
        if (altura.isEmpty() || peso.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Conversão dos dados de String para Double
            double numAltura = Double.parseDouble(altura);
            double numPeso = Double.parseDouble(peso);

            // Verificar se os valores são positivos
            if (numAltura <= 0 || numPeso <= 0) {
                Toast.makeText(this, "Insira valores positivos para peso e altura.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cálculo do IMC
            double numAlturaM = numAltura / 100.0;
            double imc = numPeso / (numAlturaM * numAlturaM);
            String imcStr = String.format("%.2f", imc);

            // Determinar a classificação do IMC
            String classificacao = calcularClassificacao(imc);

            // Criar a Intent para enviar os dados para a próxima Activity
            Intent intent;

            // Dependendo do IMC envia o usuário para uma tela de feedback diferente
            switch (classificacao) {
                case "Abaixo do peso":
                    intent = new Intent(MainActivity2.this, MainActivity3.class);
                    break;
                case "Peso normal":
                    intent = new Intent(MainActivity2.this, MainActivity4.class);
                    break;
                case "Sobrepeso":
                    intent = new Intent(MainActivity2.this, MainActivity5.class);
                    break;
                case "Obesidade grau 1":
                    intent = new Intent(MainActivity2.this, MainActivity6.class);
                    break;
                case "Obesidade grau 2":
                    intent = new Intent(MainActivity2.this, MainActivity7.class);
                    break;
                case "Obesidade grau 3":
                    intent = new Intent(MainActivity2.this, MainActivity8.class);
                    break;
                default:
                    Toast.makeText(this, "Erro ao calcular IMC", Toast.LENGTH_SHORT).show();
                    return;
            }

            // Passando os dados para a próxima tela
            intent.putExtra("imc", imcStr);
            intent.putExtra("classificacao", classificacao);
            intent.putExtra("peso", peso);
            intent.putExtra("altura", altura);
            startActivity(intent);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, insira valores válidos para peso e altura.", Toast.LENGTH_SHORT).show();
        }
    }

    private String calcularClassificacao(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc >= 18.5 && imc < 24.9) {
            return "Peso normal";
        } else if (imc >= 25 && imc < 29.9) {
            return "Sobrepeso";
        } else if (imc >= 30 && imc < 34.9) {
            return "Obesidade grau 1";
        } else if (imc >= 35 && imc < 39.9) {
            return "Obesidade grau 2";
        } else {
            return "Obesidade grau 3";
        }
    }

    public void voltartelaprincipal(View view) {
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(intent);
    }

    public void Limpar(View view) {
        EditText campoAltura = findViewById(R.id.editTextAltura);
        EditText campoPeso = findViewById(R.id.editTextPeso);

        campoAltura.setText(""); // Limpa o campo de altura
        campoPeso.setText("");   // Limpa o campo de peso
        campoAltura.requestFocus(); // Retorna o foco para o campo de altura
    }
}


