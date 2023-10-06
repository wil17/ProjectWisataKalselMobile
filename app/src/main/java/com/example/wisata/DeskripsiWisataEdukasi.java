package com.example.wisata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class DeskripsiWisataEdukasi extends AppCompatActivity {

    TextView TVnama, TVlokasi, TVdeskripsi;
    private DatabaseReference database;
    String nama, lokasi , deskripsi;
    WisataEdukasiModel wisataEdukasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi_wisata_edukasi);

        //        mengambil textView
        TVnama = findViewById(R.id.nama);
        TVlokasi = findViewById(R.id.lokasi);
        TVdeskripsi = findViewById(R.id.deskripsi);

//        menginsialisasi database
        database = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        if(!key.equals("0")){

//            mengambil 1 data berdasarkan key
            database.child("Wisata Edukasi").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String nama = snapshot.child("nama").getValue(String.class);
                    String lokasi = snapshot.child("lokasi").getValue(String.class);
                    String deskripsi = snapshot.child("deskripsi").getValue(String.class);

                    getSupportActionBar().setTitle(nama);
                    TVnama.setText(nama);
                    TVlokasi.setText(lokasi);
                    TVdeskripsi.setText(deskripsi);
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