package com.example.wisata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

public class InputForm extends AppCompatActivity {
    TextView TVnama, TVlokasi, TVdeskripsi;
    private DatabaseReference database;
    String nama, lokasi, deskripsi;
    Button simpan;
    WisataAlamModel wisataAlam;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_form);

        // Mengambil TextView
        TVnama = findViewById(R.id.nama);
        TVlokasi = findViewById(R.id.lokasi);
        TVdeskripsi = findViewById(R.id.deskripsi);

        // Menginisialisasi database
        database = FirebaseDatabase.getInstance().getReference();

        // Tombol simpan
        simpan = findViewById(R.id.buttonSimpan);
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        if (!key.equals("0")) {

            // Mengambil 1 data berdasarkan key
            database.child("Wisata Alam").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String nama = snapshot.child("nama").getValue(String.class);
                    String lokasi = snapshot.child("lokasi").getValue(String.class);
                    String deskripsi = snapshot.child("deskripsi").getValue(String.class);

                    getSupportActionBar().setTitle("Edit data " + nama);
                    TVnama.setText(nama);
                    TVlokasi.setText(lokasi);
                    TVdeskripsi.setText(deskripsi);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    finish();
                }
            });
            Toast.makeText(this, "key " + key, Toast.LENGTH_SHORT).show();

            simpan.setOnClickListener(v -> {
                nama = TVnama.getText().toString();
                lokasi = TVlokasi.getText().toString();
                deskripsi = TVdeskripsi.getText().toString();

                // Membuat hashMap updates yang menyimpan objek string
                Map<String, Object> updates = new HashMap<>();
                updates.put("nama", nama);
                updates.put("lokasi", lokasi);
                updates.put("deskripsi", deskripsi);

                // Mengupdate database berdasarkan key
                database.child("Wisata Alam").child(key).updateChildren(updates);
                finish();
            });

        } else {
            getSupportActionBar().setTitle("Tambah Data");
            simpan.setOnClickListener(v -> {
                nama = TVnama.getText().toString();
                lokasi = TVlokasi.getText().toString();
                deskripsi = TVdeskripsi.getText().toString();

                submitWisataAlam(new WisataAlamModel("", nama, lokasi, deskripsi));
                finish();
            });
        }
    }

    public void submitWisataAlam(WisataAlamModel wisataAlam) {
        database.child("Wisata Alam").push().setValue(wisataAlam).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(InputForm.this, "Berhasil tambah data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
