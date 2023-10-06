package com.example.wisata;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WisataAlam extends AppCompatActivity {

    Button buttonPantaiAngsana, buttonGunungHalau, buttonBajuin, buttonTamiang, buttonMandiAngin, buttonDanauBiru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_alam);
        buttonPantaiAngsana = findViewById(R.id.btn_pantaiangsana);
        buttonGunungHalau = findViewById(R.id.btn_gununghalau);
        buttonBajuin = findViewById(R.id.btn_bajuin);
        buttonTamiang = findViewById(R.id.btn_pantaitamiang);
        buttonMandiAngin = findViewById(R.id.btn_mandiangin);
        buttonDanauBiru= findViewById(R.id.btn_danaubiru);

        buttonPantaiAngsana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WisataAlam.this, AngsanaActivity.class));
            }
        });

        buttonGunungHalau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WisataAlam.this, HalauHalauActivity.class));
            }
        });

        buttonBajuin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WisataAlam.this, BajuinActivity.class));
            }
        });

        buttonTamiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WisataAlam.this, TamiangActivity.class));
            }
        });

        buttonMandiAngin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WisataAlam.this, TahuraActivity.class));
            }
        });

        buttonDanauBiru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WisataAlam.this, DanaubiruActivity.class));
            }
        });
    }
}