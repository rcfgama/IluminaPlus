package com.example.iluminaplus.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iluminaplus.R;
import com.example.iluminaplus.data.SecurityPreferences;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ResultActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.mSecurityPreferences = new SecurityPreferences(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        this.mViewHolder.textTitleSecond = findViewById(R.id.text_title_second);
        this.mViewHolder.textResult = findViewById(R.id.text_result);
        this.mViewHolder.textLampada = findViewById(R.id.text_lampada);
        this.mViewHolder.editLampada = findViewById(R.id.edit_lampada);
        this.mViewHolder.textDisjuntor = findViewById(R.id.text_disjuntor);
        this.mViewHolder.editDisjuntor = findViewById(R.id.edit_disjuntor);
        this.mViewHolder.textFio = findViewById(R.id.text_fio);
        this.mViewHolder.editFio = findViewById(R.id.edit_fio);

        this.getResult();
    }

    @SuppressLint("SetTextI18n")
    private void getResult() {

        String potlamp = this.mSecurityPreferences.getStoredLampada("rlamp");
        int disjuntor = this.mSecurityPreferences.getStoredDisjuntor("rdisj");
        String fio = this.mSecurityPreferences.getStoredFio("rfio");

        return (potlamp.equals("151.0")) ? this.mViewHolder.editLampada.setText("Erro") :
                this.mViewHolder.editLampada.setText(potlamp + "w");
        return (disjuntor == 64) ? this.mViewHolder.editDisjuntor.setText("Erro") :
                this.mViewHolder.editDisjuntor.setText(String.valueOf(disjuntor + "A"));
        return (fio.equals("26.0")) ? this.mViewHolder.editFio.setText("Erro") :
                this.mViewHolder.editFio.setText(fio + "mm²");
    }

    private static class  ViewHolder {
        TextView textTitleSecond;
        TextView textResult;
        TextView textLampada;
        EditText editLampada;
        TextView textDisjuntor;
        EditText editDisjuntor;
        TextView textFio;
        EditText editFio;
    }
}