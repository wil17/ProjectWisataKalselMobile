package com.example.wisata;

public class WisataEdukasiModel {
    String key, nama, lokasi, deskripsi;

    public WisataEdukasiModel() {
        // dibutuhkan oleh Firebase
    }

    public WisataEdukasiModel(String key, String nama, String lokasi, String deskripsi) {
        this.key = key;
        this.nama = nama;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
    }

    public void setKey(String key){
        this.key = key;
    }

    public String getkey() {
        return key;
    }

    public String getNama() {
        return nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}

