package com.example.wisata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListWisataEdukasi extends AppCompatActivity {

    private DatabaseReference database;
    ListView listView;
    FloatingActionButton FAB;
    private ArrayList<WisataEdukasiModel> listWisataEdukasi;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wisata_edukasi);
        listView = findViewById(R.id.itemsList);
        FAB = findViewById(R.id.tambahData);

//        menginsiasi database Firebase
        database = FirebaseDatabase.getInstance().getReference();
        populateDataWisataEdukasi();
        FAB.setOnClickListener(v -> {
            Intent intent = new Intent(ListWisataEdukasi.this, inputFormWE.class);
            intent.putExtra("key", "0");
            startActivity(intent);
        });
    }

    public void populateListview() {
        try {
            ItemListAdapterWE itemsAdopter = new ItemListAdapterWE(this, listWisataEdukasi);
            listView.setAdapter(itemsAdopter);
            itemsAdopter.notifyDataSetChanged();
            registerForContextMenu(listView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateDataWisataEdukasi() {
        database.child("Wisata Edukasi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listWisataEdukasi = new ArrayList<>();
                for (DataSnapshot wisataEdukasiSnapshot : snapshot.getChildren()) {
                    WisataEdukasiModel wisataEdukasi = wisataEdukasiSnapshot.getValue(WisataEdukasiModel.class);
                    wisataEdukasi.setKey(wisataEdukasiSnapshot.getKey());
                    listWisataEdukasi.add(wisataEdukasi);
                }

                populateListview();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateDataWisataEdukasi();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

    }

    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        WisataEdukasiModel wisataEdukasi = listWisataEdukasi.get(info.position);
        switch (item.getItemId()) {
            case R.id.detail:
                Intent intent = new Intent(ListWisataEdukasi.this, DeskripsiWisataEdukasi.class);
                intent.putExtra("key", wisataEdukasi.getkey());
                startActivity(intent);
                return true;
            case R.id.peta:
                showConfirmationDialog(wisataEdukasi.getkey());
                populateDataWisataEdukasi();
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void showConfirmationDialog(String getkey) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin menghapus data?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    private String key;

                    public void onClick(DialogInterface dialog, int id) {
                        database.child("Wisata Kuliner").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ListWisataEdukasi.this, "Data dihapus!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Tindakan yang dilakukan ketika user menekan tombol Tidak
                        // Contoh: tutup dialog
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}