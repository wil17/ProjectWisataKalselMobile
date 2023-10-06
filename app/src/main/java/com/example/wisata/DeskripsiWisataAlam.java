package com.example.wisata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DeskripsiWisataAlam extends AppCompatActivity {

    TextView TVnama, TVlokasi, TVdeskripsi;
    private DatabaseReference database;
    String nama, lokasi , deskripsi, maps;
    WisataAlamModel wisataAlam;
    WebView mapWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi_wisata_alam);

        //        mengambil textView
        TVnama = findViewById(R.id.nama);
        TVlokasi = findViewById(R.id.lokasi);
        TVdeskripsi = findViewById(R.id.deskripsi);
        mapWebView = findViewById(R.id.mapWebView);

        WebSettings webSettings = mapWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


//        menginsialisasi database
        database = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        if(!key.equals("0")){

//            mengambil 1 data berdasarkan key
            database.child("Wisata Alam").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String nama = snapshot.child("nama").getValue(String.class);
                    String lokasi = snapshot.child("lokasi").getValue(String.class);
                    String deskripsi = snapshot.child("deskripsi").getValue(String.class);
                    String mapUrl = snapshot.child("mapUrl").getValue(String.class);
                    loadMapIntoWebView(mapUrl);

                    getSupportActionBar().setTitle(nama);
                    TVnama.setText(nama);
                    TVlokasi.setText(lokasi);
                    TVdeskripsi.setText(deskripsi);
                    mapWebView.loadUrl(mapUrl);
                }

                private void loadMapIntoWebView(String mapUrl) {
                    if (mapUrl != null && !mapUrl.isEmpty()) {
                        mapWebView.getSettings().setJavaScriptEnabled(true);
                        mapWebView.loadUrl(mapUrl);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    finish();
                }
            });
            Toast.makeText(this, "key "+ key, Toast.LENGTH_SHORT).show();
        }
    }
}
