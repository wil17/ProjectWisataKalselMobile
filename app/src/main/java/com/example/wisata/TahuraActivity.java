package com.example.wisata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wisata.modelData.Artikel;

public class TahuraActivity extends AppCompatActivity {
    TextView Judul;
    TextView konten;
    ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahura);

        Artikel[] artikels = {
                new Artikel("Tahura Sultan Adam", R.drawable.mandiangin, R.string.tahura)
        };
        Judul = findViewById(R.id.judul);
        konten = findViewById(R.id.konten);
        gambar = findViewById(R.id.gambar);
    }
}