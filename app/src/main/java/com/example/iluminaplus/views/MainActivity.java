package com.example.iluminaplus.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iluminaplus.R;
import com.example.iluminaplus.data.SecurityPreferences;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        this.mViewHolder.textTitle = findViewById(R.id.text_title);
        this.mViewHolder.textTipoAmbiente = findViewById(R.id.text_tipo_ambiente);
        this.mViewHolder.radioAmbienteComum = findViewById(R.id.radio_ambiente_comum);
        this.mViewHolder.radioBancadas = findViewById(R.id.radio_bancadas);
        this.mViewHolder.radioMenorCirculacao = findViewById(R.id.radio_menor_circulacao);
        this.mViewHolder.radioHomeOffice = findViewById(R.id.radio_home_office);
        this.mViewHolder.radioLerEscrever = findViewById(R.id.radio_ler_escrever);
        this.mViewHolder.textComprimento = findViewById(R.id.text_comprimento);
        this.mViewHolder.editComprimento = findViewById(R.id.edit_comprimento);
        this.mViewHolder.textLargura = findViewById(R.id.text_largura);
        this.mViewHolder.editLargura = findViewById(R.id.edit_largura);
        this.mViewHolder.textQuantidade = findViewById(R.id.text_quantidade);
        this.mViewHolder.editQuantidade = findViewById(R.id.edit_quantidade);
        this.mViewHolder.textDistancia = findViewById(R.id.text_distancia);
        this.mViewHolder.editDistancia = findViewById(R.id.edit_distancia);
        this.mViewHolder.radio127v = findViewById(R.id.radio_127v);
        this.mViewHolder.radio220v = findViewById(R.id.radio_220v);
        this.mViewHolder.buttonCalculate = findViewById(R.id.button_calculate);

        this.mViewHolder.buttonCalculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_calculate) {
            // Declaração de variáveis:
            int ambiente, lux, disjuntor, rede;
            double area, lumens, lulamp, potencia, corrente, queda, capacidade, potlamp, fio, limrede;

            //Entrada de dados:
            //radioButton1:
            if(this.mViewHolder.radioAmbienteComum.isChecked()) {
                ambiente = 1;
            } else if(this.mViewHolder.radioBancadas.isChecked()) {
                ambiente = 2;
            } else if(this.mViewHolder.radioMenorCirculacao.isChecked()) {
                ambiente = 3;
            } else if(this.mViewHolder.radioHomeOffice.isChecked()) {
                ambiente = 4;
            } else {
                ambiente = 5;
            }

            //editText:
            String scomp = this.mViewHolder.editComprimento.getText().toString();
            String slarg = this.mViewHolder.editLargura.getText().toString();
            String squan = this.mViewHolder.editQuantidade.getText().toString();
            String sdist = this.mViewHolder.editDistancia.getText().toString();

            if ("".equals(scomp)) {
                Toast.makeText(this, "Por favor, informe o comprimento!", Toast.LENGTH_LONG).show();
            } else if ("".equals(slarg)) {
                Toast.makeText(this, "Por favor, informe a largura!", Toast.LENGTH_LONG).show();
            } else if ("".equals(squan)) {
                Toast.makeText(this, "Por favor, informe a quantidade!", Toast.LENGTH_LONG).show();
            } else if ("".equals(sdist)) {
                Toast.makeText(this, "Por favor, informe a distância!", Toast.LENGTH_LONG).show();
            } else {
                double comprimento = Double.parseDouble(scomp);
                double largura = Double.parseDouble(slarg);
                int quantidade = Integer.parseInt(squan);
                double distancia = Double.parseDouble(sdist);
            //radioButton1:
            return (this.mViewHolder.radio127v.isChecked()) ? rede = 127 : rede = 220;

            //Processamento
            if(ambiente == 1) {
                lux = 300;
            } else if(ambiente == 2) {
                lux = 750;
            } else if(ambiente == 3) {
                lux = 200;
            } else if(ambiente == 4) {
                lux = 800;
            } else {
                lux = 500;
            }
            area = comprimento * largura;
            lumens = area * lux;
            lulamp = lumens / quantidade;
            if(lulamp <= 90) {
                potlamp = 1;
            } else if(lulamp <= 270) {
                potlamp = 3;
            } else if(lulamp <= 360) {
                potlamp = 4;
            } else if(lulamp <= 400) {
                potlamp = 5;
            } else if(lulamp <= 480) {
                potlamp = 5.5;
            } else if(lulamp <= 600) {
                potlamp = 6;
            } else if(lulamp <= 700) {
                potlamp = 7;
            } else if(lulamp <= 810) {
                potlamp = 8;
            } else if(lulamp <= 900) {
                potlamp = 9;
            } else if(lulamp <= 1018) {
                potlamp = 10;
            } else if(lulamp <= 1311) {
                potlamp = 12;
            } else if(lulamp <= 1507) {
                potlamp = 15;
            } else if(lulamp <= 2000) {
                potlamp = 20;
            } else if(lulamp <= 2700) {
                potlamp = 25;
            } else if(lulamp <= 3000) {
                potlamp = 30;
            } else if(lulamp <= 3605) {
                potlamp = 35;
            } else if(lulamp <= 4120) {
                potlamp = 40;
            } else if(lulamp <= 5000) {
                potlamp = 60;
            } else if(lulamp <= 6000) {
                potlamp = 65;
            } else if(lulamp <= 7000) {
                potlamp = 70;
            } else if(lulamp <= 7200) {
                potlamp = 80;
            } else if(lulamp <= 7650) {
                potlamp = 85;
            } else if(lulamp <= 9500) {
                potlamp = 100;
            } else if(lulamp <= 11500) {
                potlamp = 120;
            } else if(lulamp <= 14500) {
                potlamp = 150;
            } else {
                potlamp = 151;
            }
            potencia = quantidade * potlamp;
            corrente = potencia / rede;
            if(potlamp == 151) {
                disjuntor = 0;
            } else if(corrente <=2) {
                disjuntor = 2;
            } else if(corrente <= 4) {
                disjuntor = 4;
            } else if(corrente <= 6) {
                disjuntor = 6;
            } else if(corrente <= 10) {
                disjuntor = 10;
            } else if(corrente <= 16) {
                disjuntor = 16;
            } else if(corrente <= 20) {
                disjuntor = 20;
            } else if(corrente <= 25) {
                disjuntor = 25;
            } else if(corrente <= 32) {
                disjuntor = 32;
            } else if(corrente <= 40) {
                disjuntor = 40;
            } else if (corrente <= 50) {
                disjuntor = 50;
            } else if (corrente <= 63) {
                disjuntor = 63;
            } else {
                disjuntor = 64;
            }
            limrede = rede * 0.96;
            if((rede - (distancia * (corrente * 0.0276))) > limrede) {
                queda = 1.5;
            } else if((rede - (distancia * (corrente * 0.0169))) > limrede) {
                queda = 2.5;
            } else if((rede - (distancia * (corrente * 0.0106))) > limrede) {
                queda = 4;
            } else if((rede - (distancia * (corrente * 0.00707))) > limrede) {
                queda = 6;
            } else if((rede - (distancia * (corrente * 0.00423))) > limrede) {
                queda = 10;
            } else if ((rede - (distancia * (corrente * 0.00268))) > limrede) {
                queda = 16;
            } else if ((rede - (distancia * (corrente * 0.00171))) > limrede) {
                queda = 25;
            } else {
                queda = 26;
            }
            if(corrente <= 15) {
                capacidade = 1.5;
            } else if(corrente <= 20) {
                capacidade = 2.5;
            } else if(corrente <= 27) {
                capacidade = 4;
            } else if(corrente <= 34) {
                capacidade = 6;
            } else if(corrente <= 46) {
                capacidade = 10;
            } else if(corrente <= 62) {
                capacidade = 16;
            } else if(corrente <= 80) {
                capacidade = 25;
            } else {
                capacidade = 26;
            }
            if(potlamp == 151) {
                fio = 0;
            } else if(disjuntor == 64) {
                fio = 0;
            } else {
                fio = Math.max(queda, capacidade);
            }

            //ENvio de Dados para a outra activity
                this.mSecurityPreferences.storeLampada("rlamp", potlamp);
                this.mSecurityPreferences.storeDisjuntor("rdisj", disjuntor);
                this.mSecurityPreferences.storeFio("rfio", fio);
                Intent intent = new Intent(this, ResultActivity.class);
                startActivity(intent);
            }
        }
    }

    private static class ViewHolder {
        TextView textTitle;
        TextView textTipoAmbiente;
        RadioButton radioAmbienteComum;
        RadioButton radioBancadas;
        RadioButton radioMenorCirculacao;
        RadioButton radioHomeOffice;
        RadioButton radioLerEscrever;
        TextView textComprimento;
        EditText editComprimento;
        TextView textLargura;
        EditText editLargura;
        TextView textQuantidade;
        EditText editQuantidade;
        TextView textDistancia;
        EditText editDistancia;
        RadioButton radio127v;
        RadioButton radio220v;
        Button buttonCalculate;
    }
}
